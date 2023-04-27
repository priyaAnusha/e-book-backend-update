package com.ebook.exceptions;


import java.time.LocalDateTime;

public class ExceptionResponse {

	private Integer status;
    private String message;
    private LocalDateTime time; 
    
	/*
	 * Default constructor
	 */
    public ExceptionResponse() {
        super();
    }
    
	/*
	 * parameterized constructor
	 */
    public ExceptionResponse(Integer status, String message, LocalDateTime time) {
        super();
        this.status = status;
        this.message = message;
        this.time = time;
    }
    
	/*
	 * Getters and Setters for Exception Response
	 */
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
