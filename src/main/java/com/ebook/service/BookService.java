package com.ebook.service;

import java.util.List;



import com.ebook.dto.BookDto;

import com.ebook.exceptions.DataViolationException;
import com.ebook.exceptions.InvalidUserException;
import com.ebook.exceptions.NoBookException;
import com.ebook.exceptions.TitleExistsException;


public interface BookService {

	public BookDto createBook(BookDto book) throws InvalidUserException, TitleExistsException;

	public List<BookDto> getAllBooks(String username) throws NoBookException;// ,Integer pageNumber,Integer pageSize);

	public String deleteBook(long bookId) throws NoBookException, DataViolationException;

	public String updateBook(BookDto bookDto, long bookId) throws NoBookException, TitleExistsException;

	public BookDto getBookById(Long id) throws NoBookException;

	public long countIncompleteBooks();

	List<BookDto> getCompletedBooksByAuthorname(String authorname) throws InvalidUserException;

	List<BookDto> getIncompleteBooksByAuthorname(String authorname) throws InvalidUserException;

    List<BookDto> getAllBookIdsByAuthor(String authorId);
}
