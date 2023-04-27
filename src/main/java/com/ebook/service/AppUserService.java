package com.ebook.service;

import com.ebook.dto.LoginDto;
import com.ebook.dto.RegisterDto;
import com.ebook.exceptions.AlreadyExsistException;
import com.ebook.exceptions.InvalidInputException;
import com.ebook.exceptions.InvalidUserException;

public interface AppUserService {
	
	public String register(RegisterDto registerDTO) throws AlreadyExsistException;

	public LoginDto login(String email, String password) throws InvalidInputException;

	public String resetPassword(String email, String password, String newPassword) throws InvalidInputException;

	public String checkAvailability(String username) throws InvalidUserException;

	public String sendEmail(String toEmail) throws InvalidInputException;

	public String VerifyOtp(String email, String otp) throws InvalidInputException;

	public String ChangePassword(String email, String password) throws InvalidInputException;

	public String Cancel(String email) throws InvalidInputException;

}
