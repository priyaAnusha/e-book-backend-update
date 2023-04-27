package com.ebook.entity;


import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Ebook {
	@Id
	@SequenceGenerator(name="EBOOK_SEQ_GEN", sequenceName="EBOOK_SEQ_GEN", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EBOOK_SEQ_GEN")
	private long ebookId;
	private String eBookName;
	private byte[] data;
	private String format;
	
	@ManyToOne
    private BookEntity book;

	public long getEbookId() {
		return ebookId;
	}

	public void setEbookId(long ebookId) {
		this.ebookId = ebookId;
	}

	public String geteBookName() {
		return eBookName;
	}

	public void seteBookName(String eBookName) {
		this.eBookName = eBookName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	@JsonBackReference
	public BookEntity getBook() {
		return book;
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}

	public Ebook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ebook(long ebookId, String eBookName, byte[] data, String format, BookEntity book) {
		super();
		this.ebookId = ebookId;
		this.eBookName = eBookName;
		this.data = data;
		this.format = format;
		this.book = book;
	}

	public void setDataAsString(String encodedData) {
		this.data = Base64.getDecoder().decode(encodedData);
		}

	

	
	
}
