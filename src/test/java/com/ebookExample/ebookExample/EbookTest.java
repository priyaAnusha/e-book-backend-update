package com.ebookExample.ebookExample;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebook.dto.BookDto;
import com.ebook.dto.EbookDto;
import com.ebook.entity.AppUser;
import com.ebook.entity.BookEntity;
import com.ebook.entity.Ebook;
import com.ebook.entity.EbookManagement;
import com.ebook.enums.RequestStatus;
import com.ebook.enums.Role;
import com.ebook.enums.StatusType;
import com.ebook.exceptions.DoesNotExistsException;
import com.ebook.exceptions.NoBookException;
import com.ebook.repo.BookRepo;
import com.ebook.repo.EbookManagementRepo;
import com.ebook.repo.EbookRepo;
import com.ebook.repo.UserRepo;
import com.ebook.service.AppUserService;
import com.ebook.service.EbookService;
import com.ebook.serviceImpl.BookServiceImpl;
import com.ebook.serviceImpl.EbookServiceImpl;
import com.ebook.serviceImpl.ExceptionsConstants;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EbookTest {
	
	@Mock
	private EbookRepo ebookRepo;
	
	@Mock
	private UserRepo userRepo;
	
	@Mock
	private BookRepo bookRepo;
	
	@Mock
	private EbookManagementRepo managmentrepo;
	 @InjectMocks
	private EbookServiceImpl ebookservice;
	
	@BeforeEach
	void setUp() {
	    MockitoAnnotations.openMocks(this);
	}
	
	@Test
    public void testCreateEbookForValidRequest() throws DoesNotExistsException {
 
        BookEntity book = new BookEntity();
        long bookId = 2L;
        book.setBookId(bookId);
        book.setTitle("book title");
        book.setContent("this is book content");

        EbookManagement mang = new EbookManagement();
        long requestId = 1L;
        mang.setRequestId(requestId);
        mang.setRequestStatus(RequestStatus.APPROVED);
        mang.setFormat("pdf");
        mang.setBoook(book);

        when(managmentrepo.findById(requestId)).thenReturn(Optional.of(mang));
        when(bookRepo.findById(bookId)).thenReturn(Optional.of(book));

        Ebook expectedEbook = new Ebook();
        expectedEbook.setEbookId(3);
        expectedEbook.seteBookName(book.getTitle());
        expectedEbook.setData(new byte[]{0x12, 0x34, 0x56});
        expectedEbook.setFormat(mang.getFormat());
        expectedEbook.setBook(book);

        when(ebookRepo.save(any(Ebook.class))).thenReturn(expectedEbook);

        // Call the service method and verify results
        Ebook result1 = new Ebook();
        Ebook result = ebookservice.createEbook(requestId);
        assertNotNull(result);
        assertEquals(expectedEbook.geteBookName(), result.geteBookName());
        assertEquals(expectedEbook.getFormat(), result.getFormat());
        assertEquals(expectedEbook.getEbookId(), result.getEbookId());
    }
	/*@Test
	public void testCreateEbookForInvalidRequest() {
	 
	    long invalidRequestId = 5L;
	    
	    when(managmentrepo.findById(invalidRequestId)).thenReturn(Optional.empty());
	    assertThrows(DoesNotExistsException.class, () -> {
	        ebookservice.createEbook(invalidRequestId);
	    });
	}

	@Test
	public void testCreateEbookForInvalidBookId() throws DoesNotExistsException {
	 
	    BookEntity book = new BookEntity();
	    long bookId = 2L;
	    book.setBookId(bookId);
	    book.setTitle("book title");
	    book.setContent("this is book content");

	    EbookManagement mang = new EbookManagement();
	    long requestId = 1L;
	    mang.setRequestId(requestId);
	    mang.setRequestStatus(RequestStatus.APPROVED);
	    mang.setFormat("pdf");
	    mang.setBoook(book);

	    when(managmentrepo.findById(requestId)).thenReturn(Optional.of(mang));
	    when(bookRepo.findById(bookId)).thenReturn(Optional.empty()); // Set the book to not exist

	    // Call the service method and verify that it throws the expected exception
	    assertThrows(DoesNotExistsException.class, () -> {
	        ebookservice.createEbook(requestId);
	    });
	}*/
	@Test
	public void testGetAllEbooks() {
	    
	    Ebook ebook = new Ebook();
	    ebook.setEbookId(1);
	    ebook.seteBookName("tenth");
	    ebook.setData(new byte[]{0x12, 0x34, 0x56});
	    ebook.setFormat("PDF");

	    AppUser author = new AppUser();
	    author.setUserName("author1");

	    BookEntity book = new BookEntity();
	    book.setBookId(1);
	    book.setAuthor(author);
	    ebook.setBook(book);

	    List<Ebook> ebooks = new ArrayList<>();
	    ebooks.add(ebook);
	    
	   
	    Mockito.when(ebookRepo.findAll()).thenReturn(ebooks);

	    List<EbookDto> result = ebookservice.getAllEbooks();

	    assertNotNull(result);
	    assertEquals(1, result.size());
	    assertNotEquals(3,result.size());
	    assertEquals("tenth", result.get(0).geteBookName());
	    assertEquals("PDF", result.get(0).getFormat());
	    assertEquals("author1", result.get(0).getUserName());
	}
	@Test
	public void testGetEbookById() throws DoesNotExistsException {
	    
	    Ebook ebook = new Ebook();
	    ebook.setEbookId(1);
	    ebook.seteBookName("tenth");
	    ebook.setData(new byte[]{0x12, 0x34, 0x56});
	    ebook.setFormat("PDF");

	    Mockito.when(ebookRepo.findById(1L)).thenReturn(Optional.of(ebook));
	    Mockito.when(ebookRepo.findById(2L)).thenReturn(Optional.empty());

	    Ebook result1 = ebookservice.getEbookById(1L);

	    assertEquals("tenth", result1.geteBookName());

	    try {
	        Ebook result2 = ebookservice.getEbookById(2L);
	        fail("Expected an exception to be thrown");
	    } catch (DoesNotExistsException e) {
	        assertEquals(MessageConstants.ebook_not_found_exception, e.getMessage());
	    }

	    try {
	        ebookservice.getEbookById((Long) null);
	        fail("Expected an exception to be thrown");
	    } catch (NullPointerException e) {
	        assertNotNull(e.getMessage());
	    }

	    assertThrows(DoesNotExistsException.class, () -> ebookservice.getEbookById(3L));
	}

	
	
	@Test
	public void testDeleteEbook() throws DoesNotExistsException {
	
	    Ebook ebook = new Ebook();
	    ebook.setEbookId(1);
	    ebook.seteBookName("tenth");
	    ebook.setData(new byte[]{0x12, 0x34, 0x56});
	    ebook.setFormat("PDF");

	    Mockito.when(ebookRepo.findById(1L)).thenReturn(Optional.of(ebook));
	    Mockito.when(ebookRepo.findById(2L)).thenReturn(Optional.empty());

	    String result1 = ebookservice.deleteEbook(1L);

	    verify(ebookRepo, times(1)).deleteById(1L);
	    assertEquals(MessageConstants.delete_success, result1);

	    try {
	        String result2 = ebookservice.deleteEbook(2L);
	        fail("Expected an exception to be thrown");
	    } catch (DoesNotExistsException e) {
	        assertEquals(MessageConstants.ebook_not_found_exception, e.getMessage());
	    }
	    assertThrows(DoesNotExistsException.class, () -> ebookservice.deleteEbook(3L));

	    verify(ebookRepo, never()).deleteById(null);
	}
	
	@Test
    public void testGetAllEbooksByAuthor() throws DoesNotExistsException {
        // Create a mock AppUser object
        AppUser author = new AppUser();
        author.setUserName("xxx@gmail.com");
        author.setRole(Role.AUTHOR);
        
        String username = "xxx@gmail.com";
 
        // Create a mock BookEntity object
        BookEntity book = new BookEntity();
        book.setBookId(2);
        book.setAuthor(author);
 
        // Create a list of mock Ebook objects
        Ebook ebook1 = new Ebook();
        ebook1.setEbookId(1);
        ebook1.seteBookName("tenth");
        ebook1.setData(new byte[]{0x12, 0x34, 0x56});
        ebook1.setFormat("PDF");
        ebook1.setBook(book);
 
        List<Ebook> ebooks = new ArrayList<>();
        ebooks.add(ebook1);
 
        // Set up mock repository methods
        Mockito.when(userRepo.findByuserName(Mockito.anyString())).thenReturn(Optional.of(author));
        Mockito.when(bookRepo.findByAuthorUserName(username)).thenReturn(Collections.singletonList(book));


        Mockito.when(ebookRepo.findByBook(book)).thenReturn(ebooks);
 
        // Call the service method
        List<Ebook> result = ebookservice.getAllEbooksByAuthor("xxx@gmail.com");
 
        
        assertEquals(1, result.size());
        assertEquals("tenth", result.get(0).geteBookName());
        assertEquals("PDF", result.get(0).getFormat());
        Mockito.verify(bookRepo, Mockito.times(1)).findByAuthorUserName(username);

        Mockito.verify(ebookRepo, Mockito.times(1)).findByBook(book);
    }
}
