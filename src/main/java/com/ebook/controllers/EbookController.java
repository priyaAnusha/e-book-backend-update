package com.ebook.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ebook.dto.EbookDto;
import com.ebook.entity.Ebook;
import com.ebook.exceptions.DoesNotExistsException;

import com.ebook.serviceImpl.EbookServiceImpl;

@RestController
@RequestMapping("/ebook")
public class EbookController{
	 @Autowired
	   private EbookServiceImpl  ebookserviceimpl;
	    
	    @PostMapping("/{requestId}")
	    public ResponseEntity<?> createEbook(@PathVariable long requestId) throws DoesNotExistsException{
	            Ebook ebook = ebookserviceimpl.createEbook(requestId);
	            return new ResponseEntity<>(ebook, HttpStatus.CREATED); 
	    }
	    @GetMapping("/allEbooks")
	    public List<EbookDto> getAllEbooks(){
	        return ebookserviceimpl.getAllEbooks();

	    }
	    @GetMapping("/requestedBook/{ebookId}")
	    public ResponseEntity<?> getEbookById(@PathVariable long ebookId)throws DoesNotExistsException {
	            Ebook ebook = ebookserviceimpl.getEbookById(ebookId);
	            return ResponseEntity.ok(ebook);
	        
	    }
	    @DeleteMapping("/{ebookId}")
	    public ResponseEntity<?> deleteEbook(@PathVariable("ebookId") Long ebookId)throws DoesNotExistsException {
	            String message = ebookserviceimpl.deleteEbook(ebookId);
	            return ResponseEntity.ok().body(message);
	      }
	    
//*******************************************************************************************************************	    
	    @GetMapping("/getAllEbooksByAuthor/{userName}")
	    public List<Ebook> getAllEbooksByAuthor(@PathVariable String userName)throws DoesNotExistsException {
			return  ebookserviceimpl.getAllEbooksByAuthor(userName);
	    	
	    }
	   



}