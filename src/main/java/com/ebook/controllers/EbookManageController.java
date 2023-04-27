package com.ebook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ebook.dto.BookDto;
import com.ebook.dto.EbookManageDTO;
import com.ebook.enums.RequestStatus;
import com.ebook.exceptions.DoesNotExistsException;
import com.ebook.exceptions.NoBookException;
import com.ebook.service.EbookManageService;



@RestController
@RequestMapping("/manageRequest")
public class EbookManageController {

	@Autowired
	private EbookManageService reqService;
	
	@PostMapping("/addRequest/{bookId}/{authorId}")
	public String createRequest(@PathVariable long bookId,@PathVariable String authorId,@RequestBody EbookManageDTO dto)throws DoesNotExistsException, NoBookException {
		return reqService.createRequest(bookId,authorId,dto);
	}
	
	@PutMapping("/updateRequest/{requestId}/{status}")
	public String updateRequest(@PathVariable long requestId,@PathVariable RequestStatus status)throws DoesNotExistsException {
		return reqService.updateRequestStatus(requestId,status);
		
	}
	
	@GetMapping("/getRequests")
	public List<EbookManageDTO> getAllRequests(){
		return reqService.getAllRequests();
		
	}
	 
	@GetMapping("/getrequestsbyStatus/{status}")
	public List<EbookManageDTO> getAllRquestsByStatus(@PathVariable RequestStatus status)throws DoesNotExistsException{
		return reqService.getAllRequestsByStatus(status);
		
	}
	
//************************************************************************************************	
	@GetMapping("viewBookByAdmin/{userName}/{id}")
	public List<BookDto> viewBookByAdmin(@PathVariable String userName, @PathVariable Long id)throws DoesNotExistsException{
		List<BookDto> books = reqService.viewBookByAdmin(userName, id);
		return books;
	}
	
	@GetMapping("viewRequestStatusByBookId/{bookId}")
    public String getRequestStatus(@PathVariable Long bookId) {
        return reqService.getRequestStatus(bookId);
    }

}
