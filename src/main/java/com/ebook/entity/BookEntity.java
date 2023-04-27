package com.ebook.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ebook.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class BookEntity {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookId;
	
	private String title;
	@Column(columnDefinition = "TEXT")
	private String content;
	private StatusType status;
	private Date startDate;
	private Date endDate;
	@ManyToOne
	private AppUser author;
	@OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Ebook> ebooks;
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
	public AppUser getAuthor() {
		return author;
	}
	public void setAuthor(AppUser author) {
		this.author = author;
	}
	public List<Ebook> getEbooks() {
		return ebooks;
	}
	public void setEbooks(List<Ebook> ebooks) {
		this.ebooks = ebooks;
	}
	
	@Override
	public String toString() {
		return "BookEntity [bookId=" + bookId + ", title=" + title + ", content=" + content + ", status=" + status
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", author=" + author + ", ebooks.size()=" + 
				ebooks.size()
				+ "]";
	}
	public BookEntity(long bookId, String title, String content, StatusType status, Date startDate, Date endDate,
			AppUser author, List<Ebook> ebooks) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.content = content;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.author = author;
		this.ebooks = ebooks;
	}
	public BookEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
