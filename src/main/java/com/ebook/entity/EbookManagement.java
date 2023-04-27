package com.ebook.entity;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


import com.ebook.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class EbookManagement {
	@Id
	@SequenceGenerator(name="REQ_SEQ_GEN", sequenceName="REQ_SEQ_GEN", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="REQ_SEQ_GEN")
	private long requestId;
	private RequestStatus requestStatus;
	private String format;
	private LocalDate requestDate;
	
	@ManyToOne
    private AppUser requestedAuthor;
	@ManyToOne
	private BookEntity boook;
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
	@JsonBackReference
	public AppUser getRequestedAuthor() {
		return requestedAuthor;
	}
	public void setRequestedAuthor(AppUser requestedAuthor) {
		this.requestedAuthor = requestedAuthor;
	}
	@JsonBackReference
	public BookEntity getBoook() {
		return boook;
	}
	public void setBoook(BookEntity boook) {
		this.boook = boook;
	}
	public EbookManagement( RequestStatus requestStatus, String format, LocalDate requestDate, AppUser requestedAuthor, BookEntity boook) {
		super();
		this.requestStatus = requestStatus;
		this.format = format;
		this.requestDate = requestDate;
		this.requestedAuthor = requestedAuthor;
		this.boook = boook;
	}
	
	@Override
	public String toString() {
		return "EbookManagment [requestId=" + requestId + ", requestStatus=" + requestStatus + ", format=" + format
				+ ", requestDate=" + requestDate + ", requestedAuthor="
				+ requestedAuthor + ", boook=" + boook + "]";
	}
	public EbookManagement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
