package com.ebook.dto;

import java.time.LocalDate;


import com.ebook.enums.RequestStatus;

public class AuthorDto {

	private long request_id;
	private String userName;
	private RequestStatus status;
	private String format;
	private LocalDate requestDate;
	private LocalDate acceptedDate;
	private String booktitle;
	private String content;
	private long book_id;

	public AuthorDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthorDto(long request_id, String userName, RequestStatus status, String format, LocalDate requestDate,
			LocalDate acceptedDate, String booktitle, String content, long book_id) {
		super();
		this.request_id = request_id;
		this.userName = userName;
		this.status = status;
		this.format = format;
		this.requestDate = requestDate;
		this.acceptedDate = acceptedDate;
		this.booktitle = booktitle;
		this.content = content;
		this.book_id = book_id;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public LocalDate getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(LocalDate acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getRequest_id() {
		return request_id;
	}

	public void setRequest_id(long request_id) {
		this.request_id = request_id;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	@Override
	public String toString() {
		return "Authordto [request_id=" + request_id + ", status=" + status + ", format=" + format + ", requestDate="
				+ requestDate + ", acceptedDate=" + acceptedDate + ", booktitle=" + booktitle + "]";
	}

}
