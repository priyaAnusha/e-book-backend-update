package com.ebook.service;

import java.util.List;

import com.ebook.dto.EbookDto;
import com.ebook.entity.Ebook;
import com.ebook.exceptions.DoesNotExistsException;

public interface EbookService {
	
	Ebook createEbook(long requestId) throws DoesNotExistsException;

	List<EbookDto> getAllEbooks();

	Ebook getEbookById(long ebookId) throws DoesNotExistsException;

	String deleteEbook(long ebookId) throws DoesNotExistsException;

	List<Ebook> getAllEbooksByAuthor(String userName) throws DoesNotExistsException;
	
}
