package com.ebookExample.ebookExample;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
import com.ebook.serviceImpl.BookServiceImpl;
import com.ebook.serviceImpl.EbookManageServiceImpl;
import com.ebook.serviceImpl.SucessConstants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EbookManagementTest {

	@InjectMocks
	private EbookManageServiceImpl ebookManagementService;

	@Mock
	private EbookManagementRepo ebookManagementRepo;

	@Mock
	private BookRepo bookRepo;

	@Mock
	private UserRepo userRepo;

	@InjectMocks
	private BookServiceImpl bookService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateRequest() throws DoesNotExistsException, NoBookException {
		long bookId = 1;
		String authorId = "author";
		EbookManageDTO dto = new EbookManageDTO();
		dto.setFormat("PDF");
		dto.setRequestDate(LocalDate.now());
		dto.setRequestStatus(RequestStatus.PENDING);

		BookEntity book = new BookEntity();
		book.setBookId(bookId);
		book.setTitle("Test Book");
		AppUser author = new AppUser();
		author.setUserName(authorId);
		book.setAuthor(author);
		book.setStatus(StatusType.COMPLETED);

		EbookManagement expectedRequest = new EbookManagement();
		expectedRequest.setBoook(book);
		expectedRequest.setFormat(dto.getFormat());
		expectedRequest.setRequestDate(dto.getRequestDate());
		expectedRequest.setRequestedAuthor(author);
		expectedRequest.setRequestStatus(dto.getRequestStatus());
		try {
			when(bookRepo.findById(bookId)).thenReturn(Optional.of(book));
			when(userRepo.findByuserName(authorId)).thenReturn(Optional.of(author));
			when(ebookManagementRepo.save(any(EbookManagement.class))).thenReturn(expectedRequest);
			String result = ebookManagementService.createRequest(bookId, authorId, dto);
		} catch (DoesNotExistsException e) {
			assertEquals(MessageConstants.doesNotExistsException_msg, e.getMessage());
		} catch (NoBookException e) {
			assertEquals(MessageConstants.noBookException_msg, e.getMessage());
		}
		String result1 = ebookManagementService.createRequest(bookId, authorId, dto);
		assertNotNull(result1);
		assertEquals(MessageConstants.success, result1);
		assertDoesNotThrow(() -> ebookManagementService.createRequest(bookId, authorId, dto));
		assertTimeout(Duration.ofSeconds(5), () -> ebookManagementService.createRequest(bookId, authorId, dto));
	}

	@Test
	public void testGetAllRequests() {

		BookEntity book1 = new BookEntity();
		book1.setBookId(1);
		BookEntity book2 = new BookEntity();
		book2.setBookId(2);

		AppUser a1 = new AppUser();
		a1.setUserName("author1");
		AppUser a2 = new AppUser();
		a2.setUserName("author2");

		EbookManagement request1 = new EbookManagement();
		request1.setRequestId(1);
		request1.setBoook(book1);
		request1.setRequestedAuthor(a1);
		request1.setFormat("PDF");
		request1.setRequestStatus(RequestStatus.PENDING);

		EbookManagement request2 = new EbookManagement();
		request2.setRequestId(2);
		request2.setBoook(book2);
		request2.setRequestedAuthor(a2);
		request2.setFormat("DOCX");
		request2.setRequestStatus(RequestStatus.APPROVED);

		List<EbookManagement> requests = new ArrayList<>();
		requests.add(request1);
		requests.add(request2);

		when(ebookManagementRepo.findAll()).thenReturn(requests);

		List<EbookManageDTO> result = ebookManagementService.getAllRequests();
		assertNotNull(result);
		assertEquals(2, result.size());
		assertFalse(result.isEmpty());
		assertNotSame(request1, result);
	}

	@Test
	public void testUpdateRequestStatus() throws DoesNotExistsException {
		// Mock input data
		long requestId = 1;
		RequestStatus status = RequestStatus.APPROVED;
		EbookManagement ebookManagement = new EbookManagement();
		ebookManagement.setRequestId(requestId);
		ebookManagement.setRequestStatus(status);
		try {
			when(ebookManagementRepo.findById(requestId)).thenReturn(Optional.of(ebookManagement));
			when(ebookManagementRepo.save(any(EbookManagement.class))).thenReturn(ebookManagement);
			String result = ebookManagementService.updateRequestStatus(requestId, status);
			assertEquals(status, ebookManagement.getRequestStatus()); // Assert that the request status is updated
																		// correctly
			assertEquals(MessageConstants.update_success, result); // Assert that the result is "success"
			assertNotNull(ebookManagement); // Assert that ebookManagement is not null
			assertNotSame(RequestStatus.PENDING, ebookManagement.getRequestStatus()); // Assert that the request status
																						// is not equal to PENDING
			assertTrue(result.startsWith("U")); // Assert that the result starts with "s"
			assertFalse(result.isEmpty()); // Assert that the result is not empty
		} catch (DoesNotExistsException e) {
			assertEquals(MessageConstants.doesNotExistsException_msg, e.getMessage());
		}
	}

	@Test
	public void testGetRequestByStatus() throws DoesNotExistsException {
		BookEntity book1 = new BookEntity();
		book1.setBookId(1);

		AppUser author1 = new AppUser();
		author1.setUserName("author1");

		EbookManagement request1 = new EbookManagement();
		request1.setRequestId(1);
		request1.setBoook(book1);
		request1.setRequestedAuthor(author1);
		request1.setFormat("PDF");
		request1.setRequestStatus(RequestStatus.APPROVED);

		List<EbookManagement> requests = new ArrayList<>();
		requests.add(request1);
		try {
			when(ebookManagementRepo.findByRequestStatus(RequestStatus.APPROVED)).thenReturn(requests);
			List<EbookManageDTO> result = ebookManagementService.getAllRequestsByStatus(RequestStatus.APPROVED);

			assertNotNull(result); // Assert that the result is not null
			assertEquals(1, result.size()); // Assert that the result size is 1
			assertEquals(1, result.get(0).getRequestId()); // Assert that the request id in the result is 1
			assertEquals(RequestStatus.APPROVED, result.get(0).getRequestStatus());
		} catch (DoesNotExistsException e) {
			assertEquals(MessageConstants.doesNotExistsException_msg, e.getMessage());
		}

	}

	@Test
	public void testGetAllBookIdsByAuthor() {
		
		String name="author";
		BookEntity book1 = new BookEntity();
		book1.setBookId(1);
		book1.setTitle("title1");
		BookEntity book2 = new BookEntity();
		book2.setBookId(2);
		book2.setTitle("title2");
		List<BookEntity> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		when(bookRepo.findByAuthorUserName(name)).thenReturn(books);

		List<BookDto> bookInfos = bookService.getAllBookIdsByAuthor("author");

		assertNotNull(bookInfos);
		assertEquals(2, bookInfos.size());
		assertEquals(1, bookInfos.get(0).getBookId());
		assertEquals("title1", bookInfos.get(0).getTitle());
		assertEquals(2, bookInfos.get(1).getBookId());
		assertEquals("title2", bookInfos.get(1).getTitle());
	}

	@Test
	public void testViewBookByAdmin() throws DoesNotExistsException {
		String authorName = "authorId";
		AppUser a=new AppUser();
		a.setUserName(authorName);
		long bookId = 1;
		BookEntity bookEntity = new BookEntity();
		bookEntity.setBookId(bookId);
		bookEntity.setTitle("title1");
		bookEntity.setContent("content1");
		bookEntity.setStatus(StatusType.COMPLETED);
		AppUser userEntity = new AppUser();
		userEntity.setUserName(authorName);
		bookEntity.setAuthor(a);
		List<BookEntity> books = new ArrayList<>();
		books.add(bookEntity);

		when(bookRepo.findByAuthorUserName(authorName)).thenReturn(books);

		List<BookDto> expected = new ArrayList<>();
		BookDto bookDto = new BookDto();
		bookDto.setBookId(bookId);
		bookDto.setTitle("title1");
		bookDto.setContent("content1");
		expected.add(bookDto);

		List<BookDto> result = ebookManagementService.viewBookByAdmin(authorName, bookId);

		assertEquals(1, result.size());
	}
	

}
