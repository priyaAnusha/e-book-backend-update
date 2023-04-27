package com.ebookExample.ebookExample;
 
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.time.LocalDate;
import java.time.ZoneId;
 
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
 
import com.ebook.dto.BookDto;
import com.ebook.entity.AppUser;
import com.ebook.entity.BookEntity;
import com.ebook.enums.Role;
import com.ebook.enums.StatusType;
import com.ebook.exceptions.DataViolationException;
import com.ebook.exceptions.InvalidUserException;
import com.ebook.exceptions.NoBookException;
import com.ebook.exceptions.TitleExistsException;
import com.ebook.repo.BookRepo;
import com.ebook.repo.UserRepo;
import com.ebook.serviceImpl.BookServiceImpl;
 
@ExtendWith(MockitoExtension.class)
public class BookTest {
    @InjectMocks
    private BookServiceImpl bookServiceImpl;
 
    @Mock
    private UserRepo userRepo;
    @Mock
    private BookRepo bookRepo;
    private BookEntity existingBookEntity;
    private BookDto updatedBookDto;
 
    @Test
    public void testCreateBook() throws InvalidUserException, TitleExistsException {
 
        AppUser user = new AppUser();
        user.setUserName("Alexa@gmail.com");
        user.setRole(Role.AUTHOR);
 
        BookDto bookDto = new BookDto();
        bookDto.setBookId(1);
        bookDto.setContent("HelloHelloHelloHelloHelloHello");
        bookDto.setTitle("New book");
 
        bookDto.setAuthorId(user.getUserName());
        bookDto.setEndDate(null);
        bookDto.setStartDate(null);
        bookDto.setStatus(StatusType.PAUSE);
 
        BookDto bookDto2 = new BookDto();
        bookDto2.setBookId(1);
        bookDto2.setContent("HelloHelloHelloHelloHelloHello");
        bookDto2.setTitle("New1 book");
 
        bookDto2.setAuthorId("Alexaa@gmail.com");
        bookDto2.setEndDate(null);
        bookDto2.setStartDate(null);
        bookDto2.setStatus(StatusType.PAUSE);
 
        BookEntity book = new BookEntity();
        book.setBookId(bookDto.getBookId());
        book.setContent(bookDto.getContent());
        book.setEndDate(bookDto.getEndDate());
        book.setStartDate(bookDto.getStartDate());
        book.setStatus(bookDto.getStatus());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(user);
 
        BookDto res = null;
        try {
            Mockito.when(bookRepo.findByTitle(bookDto.getTitle())).thenReturn(null);
            Mockito.when(userRepo.findById(user.getUserName())).thenReturn(Optional.of(user));
            res = bookServiceImpl.createBook(bookDto);
            assertEquals(res, bookDto);
            res = null;
            Mockito.when(bookRepo.findByTitle(bookDto2.getTitle())).thenReturn(book);
            Mockito.lenient().when(userRepo.findById(bookDto2.getAuthorId())).thenReturn(Optional.empty());
            res = bookServiceImpl.createBook(bookDto2);
            assertNull(res);
            fail("NoBookException should have been thrown");
        } catch (InvalidUserException ex) {
            assertNull(res);
            assertEquals(MessageConstants.invalid_user_exception, ex.getMessage());
        } catch (TitleExistsException ex) {
            assertNull(res);
            assertEquals(MessageConstants.title_exist_exception, ex.getMessage());
        }
 
    }
 
    @Test
    public void testDeleteBook() throws NoBookException, DataViolationException {
        long bookId = 1L;
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId(bookId);
 
       // Mockito.when(bookRepo.findById(bookId)).thenReturn(Optional.of(bookEntity));
        Mockito.doNothing().when(bookRepo).deleteById(bookId);
 
        String result = bookServiceImpl.deleteBook(bookId);
        assertEquals("Deleted Successfully", result);
    }
 
//    @Test
//    public void testDeleteBookWhenBookNotFound() {
//        long bookId = 1L;
//        Mockito.when(bookRepo.findById(bookId)).thenReturn(Optional.empty());
// 
//        Assertions.assertThrows(NoBookException.class, () -> {
//            bookServiceImpl.deleteBook(bookId);
//        });
//    }
 
//    @Test
//    public void testDeleteBookWhenDataViolationExceptionOccurs() {
//        long bookId = 1L;
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBookId(bookId);
// 
//        Mockito.when(bookRepo.findById(bookId)).thenReturn(Optional.of(bookEntity));
//        Mockito.doThrow(DataIntegrityViolationException.class).when(bookRepo).deleteById(bookId);
// 
//        Assertions.assertThrows(DataViolationException.class, () -> {
//            bookServiceImpl.deleteBook(bookId);
//        });
//    }
 
    @BeforeEach
    public void setUp() {
        existingBookEntity = new BookEntity();
        existingBookEntity.setBookId(1L);
        existingBookEntity.setTitle("Existing Title");
        existingBookEntity.setContent("Existing Content");
        existingBookEntity.setStatus(StatusType.PAUSE);
        existingBookEntity.setStartDate(null);
        existingBookEntity.setEndDate(null);
 
        updatedBookDto = new BookDto();
        updatedBookDto.setTitle("Updated Title");
        updatedBookDto.setContent("Updated Content");
        updatedBookDto.setStatus(StatusType.RESUME);
        updatedBookDto.setStartDate(null);
        updatedBookDto.setEndDate(null);
 
    }
 
    @Test
    public void testUpdateBook_Success() throws NoBookException, TitleExistsException {
        // arrange
        Long bookId = 1L;
        when(bookRepo.findById(bookId)).thenReturn(Optional.of(existingBookEntity));
        when(bookRepo.save(existingBookEntity)).thenReturn(existingBookEntity);
 
        // act
        String result = bookServiceImpl.updateBook(updatedBookDto, bookId);
 
        // assert
        verify(bookRepo).findById(bookId);
        verify(bookRepo).save(existingBookEntity);
        assertEquals(MessageConstants.update_success, result);

    }
 
    @Test
    public void testUpdateBook_BookNotFound() throws NoBookException, TitleExistsException {
        // arrange
        Long bookId = 1L;
        when(bookRepo.findById(bookId)).thenReturn(Optional.of(existingBookEntity));
 
        // act
        bookServiceImpl.updateBook(updatedBookDto, bookId);
 
        // assert - exception
    }
 
    @Test
    public void testGetAllBooks() throws NoBookException {
        // Arrange
        String userName = "johndoe";
        AppUser user = new AppUser();
        user.setUserName(userName);
        when(userRepo.findById(userName)).thenReturn(Optional.of(user));
 
        List<BookEntity> books = new ArrayList<>();
        BookEntity book1 = new BookEntity();
        book1.setAuthor(user);
        book1.setTitle("Book 1");
        BookEntity book2 = new BookEntity();
        book2.setAuthor(user);
        book2.setTitle("Book 2");
        books.add(book1);
        books.add(book2);
        when(bookRepo.findAll()).thenReturn(books);
 
        // Act
        List<BookDto> result = bookServiceImpl.getAllBooks(userName);
 
        // Assert
        assertEquals(2, result.size());
        assertEquals(userName, result.get(0).getAuthorId());
        assertEquals("Book 1", result.get(0).getTitle());
        assertEquals(userName, result.get(1).getAuthorId());
        assertEquals("Book 2", result.get(1).getTitle());
 
        verify(userRepo, times(1)).findById(userName);
        verify(bookRepo, times(1)).findAll();
    }
 
    


@Test
    public void testGetBookById() throws NoBookException {
        // Given
        Long bookId = 1L;
        String authorUserName = "test_author";
        String content = "test_content";
        String title = "test_title";
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId(bookId);
        bookEntity.setContent(content);
        bookEntity.setStartDate(new Date(122, 0, 1));
        bookEntity.setEndDate(new Date(122, 0, 31));
        bookEntity.setTitle(title);
        bookEntity.setStatus(StatusType.COMPLETED);
 
        AppUser authorEntity = new AppUser();
        authorEntity.setUserName(authorUserName);
        bookEntity.setAuthor(authorEntity);
        BookDto book = new BookDto();
        BookDto result = null;
        try {
            Mockito.when(bookRepo.findById(1L)).thenReturn(Optional.of(bookEntity));
 
            result = bookServiceImpl.getBookById(1L);
            assertEquals(bookId, result.getBookId());
            assertEquals(authorUserName, result.getAuthorId());
            assertEquals(content, result.getContent());
            assertEquals(title, result.getTitle());
            assertEquals(LocalDate.of(2022, 1, 1),
                    result.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            assertEquals(LocalDate.of(2022, 1, 31),
                    result.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            // When
            result = null;
            Mockito.when(bookRepo.findById(anyLong())).thenReturn(Optional.empty());
            result = bookServiceImpl.getBookById(5L);
            assertNull(result);
            fail("NoBookException should have been thrown");
            // Then
 
        } catch (NoBookException e) {
            // Assert
 
            assertNull(result);
            assertEquals("Book not found", e.getMessage());
        }
    }
    @Test
    public void testGetCompletedBooksByAuthorname() {
        // Arrange
        String authorName = "john";
        List<BookDto> books = new ArrayList<>();
        BookDto book1 = new BookDto();
        book1.setStatus(StatusType.COMPLETED);
        book1.setAuthorId("john");
        book1.setTitle("Book1");
        book1.setBookId(1);
        book1.setContent("Book26666");
        books.add(book1);
        BookDto book2 = new BookDto();
        book2.setStatus(StatusType.COMPLETED);
        book2.setAuthorId("john");
        book2.setBookId(2);
        book2.setContent("Book2gg");
        book2.setTitle("Book2");
        books.add(book2);
        try {
            when(bookServiceImpl.getCompletedBooksByAuthorname(authorName)).thenReturn(books);
        } catch (InvalidUserException e1) {
            // TODO Auto-generated catch block
            assertEquals("Invalid Username",e1.getMessage());
        }

        try {
            // Act
            List<BookDto> result = bookServiceImpl.getCompletedBooksByAuthorname(authorName);
            // Assert
            assertEquals(2, result.size());
            assertEquals("Book1", result.get(0).getTitle());
        } catch (InvalidUserException e) {
            // handle the exception
        }
    }

    @Test
    public void testGetIncompleteBooksByAuthorname() {
        // Arrange
        String authorName = "john";
        List<BookDto> books = new ArrayList<>();
        BookDto book1 = new BookDto();
        book1.setStatus(StatusType.PAUSE);
        book1.setAuthorId("john");
        book1.setTitle("Book1");
        book1.setBookId(1);
        book1.setContent("Book26666");
        books.add(book1);
        BookDto book2 = new BookDto();
        book2.setStatus(StatusType.PAUSE);
        book2.setAuthorId("john");
        book2.setBookId(2);
        book2.setContent("Book2gg");
        book2.setTitle("Book2");
        books.add(book2);
        try {
            when(bookServiceImpl.getIncompleteBooksByAuthorname(authorName)).thenReturn(books);
        } catch (InvalidUserException e1) {
            // TODO Auto-generated catch block
            assertEquals("Invalid Username",e1.getMessage());
        }

        try {
            // Act
            List<BookDto> result = bookServiceImpl.getIncompleteBooksByAuthorname(authorName);
            // Assert
            assertEquals(2, result.size());
            assertEquals("Book1", result.get(0).getTitle());
        } catch (InvalidUserException e) {
            // handle the exception
        }
    }
 


}


