package com.ebook.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.ebook.enums.StatusType;

public class BookDto {
	@NotNull
	private long bookId;
	@NotBlank(message="Title should not be empty...")
	@Size(min=5,message ="Title should be atleast 5 characters...")
	private String title;
	@NotBlank(message="Content cannot be empty...")
	@Size(min=100, message="Size of content is short")
	private String content;
	@NotNull
	private StatusType status;
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;
	@NotNull
	private String authorId;
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public StatusType getStatus() {
		return status;
	}
	public void setStatus(StatusType status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	@Override
	public String toString() {
		return "BookDto [bookId=" + bookId + ", title=" + title + ", content=" + content + ", status=" + status
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", authorId=" + authorId + "]";
	}
	public BookDto(long bookId, String title, String content, StatusType status, Date startDate, Date endDate,
			String authorId) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.content = content;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.authorId = authorId;
	}
	public BookDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
//	@NotNull
//	(message="Book id cannot be null")
//	private int bookId;
//	@NotNull(message="Author id cannot be null")
//	private int authorId;
//	@NotBlank(message="Title cannot be blank")
//	@Size(min=1,max=155,message="Title must be between {min} and {max} characters")
//	private String title;
//	@NotBlank(message="Context cannot be blank")
//	@Size(min=5, max=1000,message="Context must be between {min} and {max} characters")
//	private String context;
//	@NotNull(message="number of pages cannot be null")
//	private int no_of_pages;
//	@NotNull(message="status cannot be null")
//	private StatusType status;
//	public int getBookId() {
//		return bookId;
//	}
//	public void setBookId(int bookId) {
//		this.bookId = bookId;
//	}
//	public int getAuthorId() {
//		return authorId;
//	}
//	public void setAuthorId(int authorId) {
//		this.authorId = authorId;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getContext() {
//		return context;
//	}
//	public void setContext(String context) {
//		this.context = context;
//	}
//	public int getNo_of_pages() {
//		return no_of_pages;
//	}
//	public void setNo_of_pages(int no_of_pages) {
//		this.no_of_pages = no_of_pages;
//	}
//	public StatusType getStatus() {
//		return status;
//	}
//	public void setStatus(StatusType status) {
//		this.status = status;
//	}
//	@Override
//	public String toString() {
//		return "BookDto [bookId=" + bookId + ", authorId=" + authorId + ", title=" + title + ", context=" + context
//				+ ", no_of_pages=" + no_of_pages + ", status=" + status + "]";
//	}
//	public BookDto(int bookId, int authorId, String title, String context, int no_of_pages, StatusType status) {
//		super();
//		this.bookId = bookId;
//		this.authorId = authorId;
//		this.title = title;
//		this.context = context;
//		this.no_of_pages = no_of_pages;
//		this.status = status;
//	}
//	public BookDto() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	
}

