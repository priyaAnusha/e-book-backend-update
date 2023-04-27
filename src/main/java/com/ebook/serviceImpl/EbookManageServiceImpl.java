package com.ebook.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ebook.dto.BookDto;
import com.ebook.dto.EbookManageDTO;
import com.ebook.entity.AppUser;
import com.ebook.entity.BookEntity;
import com.ebook.entity.EbookManagement;
import com.ebook.enums.RequestStatus;
import com.ebook.enums.StatusType;
import com.ebook.exceptions.DoesNotExistsException;
import com.ebook.exceptions.NoBookException;
import com.ebook.repo.BookRepo;
import com.ebook.repo.EbookManagementRepo;
import com.ebook.repo.UserRepo;
import com.ebook.service.EbookManageService;

@Service
public class EbookManageServiceImpl implements EbookManageService {

	@Autowired
	private UserRepo userrepo;
	@Autowired
	private BookRepo bookrepo;
	@Autowired
	private EbookManagementRepo requestrepo;

	@Override
	public String createRequest(long bookId, String authorId, EbookManageDTO dto)
			throws DoesNotExistsException, NoBookException {
		// TODO Auto-generated method stub
		Optional<BookEntity> bookOptional = bookrepo.findById(bookId);
		Optional<AppUser> authorOptional = userrepo.findByuserName(authorId);

		if (bookOptional.isEmpty()) {
			throw new DoesNotExistsException(ExceptionsConstants.book_not_found_exception);
		}

		if (authorOptional.isEmpty()) {
			throw new DoesNotExistsException(ExceptionsConstants.invalid_author_exception);
		}
		BookEntity book = bookOptional.get();
		AppUser author = authorOptional.get();
		if (!book.getAuthor().getUserName().equals(authorId)) {
			throw new NoBookException(ExceptionsConstants.author_book_not_found_exception);
		}
		if (book.getStatus() != StatusType.COMPLETED) {
			throw new DoesNotExistsException(ExceptionsConstants.generating_request_exception);
		}
		dto.setRequestStatus(RequestStatus.PENDING);
		EbookManagement eBookManage = new EbookManagement();
		eBookManage.setBoook(book);
		eBookManage.setFormat(dto.getFormat());
		eBookManage.setRequestDate(dto.getRequestDate());
		eBookManage.setRequestedAuthor(author);
		eBookManage.setRequestStatus(dto.getRequestStatus());
		requestrepo.save(eBookManage);
		return SucessConstants.request_success;
	}

	@Override
	public String updateRequestStatus(long requestId, RequestStatus status) throws DoesNotExistsException {
		Optional<EbookManagement> requests = requestrepo.findById(requestId);
		if (requests.isEmpty()) {
			throw new DoesNotExistsException(ExceptionsConstants.invalid_request_id_exception);
		}
		EbookManagement request = requests.get();
		request.setRequestStatus(status);
		requestrepo.save(request);
		return SucessConstants.update_success;
	}

	@Override
	public List<EbookManageDTO> getAllRequests() {
		List<EbookManageDTO> dtos = new ArrayList<>();
		List<EbookManagement> books = requestrepo.findAll();

		for (EbookManagement book : books) {
			EbookManageDTO dto = new EbookManageDTO();
			dto.setRequestId(book.getRequestId());
			if (book.getRequestedAuthor() != null) {
				dto.setAutorName(book.getRequestedAuthor().getUserName());
			}
			dto.setBookId(book.getBoook().getBookId());
			dto.setRequestDate(book.getRequestDate());
			dto.setFormat(book.getFormat());
			dto.setRequestStatus(book.getRequestStatus());
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public List<EbookManageDTO> getAllRequestsByStatus(RequestStatus requestStatus) throws DoesNotExistsException {
		List<EbookManageDTO> dtos = new ArrayList<>();
		List<EbookManagement> books = requestrepo.findByRequestStatus(requestStatus); 
		if (books.isEmpty()) {
			throw new DoesNotExistsException(ExceptionsConstants.book_not_found_exception);
		}
		for (EbookManagement book : books) {
			EbookManageDTO dto = new EbookManageDTO();
			dto.setRequestId(book.getRequestId());
			if (book.getRequestedAuthor() != null) {
				dto.setAutorName(book.getRequestedAuthor().getUserName());
			}
			dto.setBookId(book.getBoook().getBookId());
			dto.setRequestDate(book.getRequestDate());
			dto.setFormat(book.getFormat());
			dto.setRequestStatus(book.getRequestStatus());
			dtos.add(dto);
		}

		return dtos;
	}

//***************************************************************************************************
	@Override
	public List<BookDto> viewBookByAdmin(String userName, long id) throws DoesNotExistsException {

		List<BookDto> authorList = new ArrayList<>();

		List<BookEntity> books = bookrepo.findByAuthorUserName(userName);

		// Loop through each book and check whether its ID matches the given ID
		boolean bookFoundForUser = false;
		for (BookEntity book : books) {
			if (book.getBookId() == id) {
				bookFoundForUser = true;
				if (book.getTitle() == null || book.getContent() == null) {
					// If either the book title or content is null, throw an exception
					throw new DoesNotExistsException(ExceptionsConstants.book_content_empty_exception + id);
				} else {
					// Create a Bookdto object and add it to the authorList
					BookDto bookdto = new BookDto();
					bookdto.setBookId(book.getBookId());
					// bookdto.setAuthorName(book.getAuthor().getUserName());
					bookdto.setTitle(book.getTitle());
					bookdto.setContent(book.getContent());
					// bookdto.setBookId(book.getBookId());
					authorList.add(bookdto);
				}
			}
		}

		// If the book ID was not found for the specified user, throw an exception
		if (!bookFoundForUser) {
			throw new DoesNotExistsException(ExceptionsConstants.book_not_found_user_exception + userName);
		}

		// If no exception was thrown, return the list of books
		return authorList;
	}

//*******************************************************************************************************

	@Override
	public String getRequestStatus(Long bookId) {
		// TODO Auto-generated method stub
		RequestStatus stat = requestrepo.findRequestStatusByBookId(bookId);
		if (stat != null)
			return stat.name();
		return SucessConstants.not_requested;
	}

}
