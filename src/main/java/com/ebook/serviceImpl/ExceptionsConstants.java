package com.ebook.serviceImpl;
 
public class ExceptionsConstants {
    protected static  String    ebook_not_found_exception="EBook not found";
    protected static  String    book_not_found_exception = "Book not found";
    protected static  String    pending_status_exception="The request status is pending";
    protected static  String    rejected_status_exception="The request is rejected";
    protected static  String    invalid_request_id_exception="Request ID not found";
    protected static  String    invalid_format_exception="Invalid format. Please specify either 'pdf' or 'docx'";
    protected static  String    invalid_author_exception="Author not found";
    protected static  String    author_book_not_found_exception="Author with book not found";
    protected static  String    generating_request_exception="Book is not yet completed to generate the request";
    protected static  String    invalid_user_exception="You are not authorized person to write the book... ";
    protected static  String    already_exist_exception="User already exists";
    protected static  String    incorrect_password_exception="Incorrect Password";
    protected static  String    incorrect_username_exception="Invalid Username";
    protected static  String    invalid_password_exception="Enter valid Password";
    protected static  String    invalid_username_exception="Enter Valid Username";
    protected static  String    error_generating_docx="Error generating DOCX document";
    protected static  String    error_generating_pdf="Error generating pdf document";  
    protected static  String    user_not_found_exception="username does not exist";
    protected static  String    no_book_exception="You have no Books to View";
    protected static  String 	book_not_found_user_exception="Book with ID  not found for user ";
    protected static  String    data_violation_exception="You cannot delete the  book because the request is already sent to admin";
    protected static  String    book_content_empty_exception="Book title or content is empty for book with ID ";
    protected static  String    email_invalidId_exception="This email is not registerd";
    protected static  String    invalId_email_exception="enter valid email";
    protected static  String    invalidId_otpmismatch_exception="otp did not match";
    protected static  String    invalid_newpassword_exception="new password cannot be previous password";
  
    protected static  String 	title_exist_exception="Title already exists";

}
