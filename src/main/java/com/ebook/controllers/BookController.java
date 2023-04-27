package com.ebook.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ebook.dto.BookDto;

import com.ebook.exceptions.DataViolationException;
import com.ebook.exceptions.InvalidUserException;
import com.ebook.exceptions.NoBookException;
import com.ebook.exceptions.TitleExistsException;

import com.ebook.service.BookService;


@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;

	@PostMapping("/saveBook")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto b) throws InvalidUserException,TitleExistsException {
return new ResponseEntity<BookDto>(bookService.createBook(b), HttpStatus.CREATED);
    }



	@GetMapping("/getBook/{id}")
	public ResponseEntity<BookDto> getBookById(@Valid @PathVariable Long id) throws NoBookException {
		return ResponseEntity.ok(bookService.getBookById(id));
	}

	@GetMapping("/viewAllBooks/{username}")
	public ResponseEntity<List<BookDto>> getAllBooks(@Valid @PathVariable String username) throws NoBookException {																											//
		return ResponseEntity.ok(bookService.getAllBooks(username));
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<String> deleteBook(@Valid @PathVariable int bookId) throws NoBookException, DataViolationException {

		return ResponseEntity.ok(bookService.deleteBook(bookId));
	}

	@PutMapping("/updateBook/{bookId}")
	public ResponseEntity<String> updateBook(@Valid @RequestBody BookDto bookDto, @PathVariable int bookId)
			throws NoBookException, TitleExistsException {
		return ResponseEntity.ok(bookService.updateBook(bookDto, bookId));
	}

	@GetMapping("/getCountOfIncompleteBooks")
	public Long countIncompleteBooks() {
		return bookService.countIncompleteBooks();
	}

	@GetMapping("/getCompletedBooks/{authorname}")
	public ResponseEntity<List<BookDto>> getCompletedBooksByAuthorname(@PathVariable String authorname) throws InvalidUserException {
		List<BookDto> listOfCompletedBooks = bookService.getCompletedBooksByAuthorname(authorname);
		return ResponseEntity.ok(listOfCompletedBooks);
	}

	@GetMapping("/getIncompleteBooks/{authorname}")
	public ResponseEntity<List<BookDto>> getIncompleteBooksByAuthorname(@PathVariable String authorname) throws InvalidUserException {
		List<BookDto> listOfIncompleteBooks = bookService.getIncompleteBooksByAuthorname(authorname);
		return ResponseEntity.ok(listOfIncompleteBooks);
	}

	@GetMapping("/getBookIdsTitles/{authorId}")
    public List<BookDto> getAllBookIdsByAuthor(@PathVariable String authorId){
        return bookService.getAllBookIdsByAuthor(authorId);

    }

}
