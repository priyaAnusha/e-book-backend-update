package com.ebook.dto;


import java.time.LocalDate;

import javax.validation.constraints.NotNull;


import com.ebook.enums.RequestStatus;

public class EbookManageDTO {
	private long requestId;
	private RequestStatus requestStatus;
	@NotNull
	private String format;
	private LocalDate requestDate;
	private String autorName;
	private long bookId;
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public LocalDate getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}
	public String getAutorName() {
		return autorName;
	}
	public void setAutorName(String autorName) {
		this.autorName = autorName;
	}
	public EbookManageDTO () {
		super();
		// TODO Auto-generated constructor stub
	}

	public EbookManageDTO (long requestId, RequestStatus requestStatus, @NotNull String format, LocalDate requestDate,String autorName) {
		super();
		this.requestId = requestId;
		this.requestStatus = requestStatus;
		this.format = format;
		this.requestDate = requestDate;
		this.autorName = autorName;
	}

	@Override
	public String toString() {
		return "Ebook_manageDto [requestId=" + requestId + ", requestStatus=" + requestStatus + ", format=" + format
				+ ", requestDate=" + requestDate + ", autorName=" + autorName + "]";
	}
	
	
}
