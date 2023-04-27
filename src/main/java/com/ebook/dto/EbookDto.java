package com.ebook.dto;

import java.util.Arrays;

public class EbookDto {
	
	private long ebookId;
	private String eBookName;
	private byte[] data;
	private String format;
	private String UserName;
	public EbookDto(long ebookId, String eBookName, byte[] data, String format, String userName) {
		super();
		this.ebookId = ebookId;
		this.eBookName = eBookName;
		this.data = data;
		this.format = format;
		UserName = userName;
	}
	public EbookDto() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	@Override
	public String toString() {
		return "EbookDto [ebookId=" + ebookId + ", eBookName=" + eBookName + ", data=" + Arrays.toString(data)
				+ ", format=" + format + ", UserName=" + UserName + "]";
	}
	
	

}
