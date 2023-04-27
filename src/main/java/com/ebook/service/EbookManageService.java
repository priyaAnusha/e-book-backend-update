package com.ebook.service;

import java.util.List;


import com.ebook.dto.BookDto;
import com.ebook.dto.EbookManageDTO;
import com.ebook.enums.RequestStatus;
import com.ebook.exceptions.DoesNotExistsException;
import com.ebook.exceptions.NoBookException;

public interface EbookManageService {
	
	String createRequest(long bookId, String authorId, EbookManageDTO dto)throws DoesNotExistsException, NoBookException;

	String updateRequestStatus(long requestId, RequestStatus status) throws DoesNotExistsException;

	List<EbookManageDTO> getAllRequests();

	List<EbookManageDTO> getAllRequestsByStatus(RequestStatus requestStatus) throws DoesNotExistsException;

	List<BookDto> viewBookByAdmin(String userName, long id) throws DoesNotExistsException;

	String getRequestStatus(Long bookId);
}
