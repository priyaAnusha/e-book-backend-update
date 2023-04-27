package com.ebookExample.ebookExample;
 
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;

import static org.mockito.Mockito.when;
 

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
 
import com.ebook.dto.LoginDto;
import com.ebook.dto.RegisterDto;
import com.ebook.entity.AppUser;
import com.ebook.entity.Verification;
import com.ebook.enums.Role;
import com.ebook.exceptions.AlreadyExsistException;
import com.ebook.exceptions.InvalidInputException;
import com.ebook.exceptions.InvalidUserException;
import com.ebook.repo.UserRepo;
import com.ebook.repo.VerificationRepo;
import com.ebook.serviceImpl.AppUserServiceImpl;
 
@ExtendWith(MockitoExtension.class)
public class AppUserServiceTest {
    @Mock
    private VerificationRepo verificationRepo;
    @Mock
    private UserRepo appUserRepo;
    @Mock
    private AppUserServiceImpl appUserServiceImpl;
 
    @Test
    public void testRegisterUser() throws AlreadyExsistException {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUserName("testUser@gmail.com");
        registerDto.setPassword("P@assword123");
        registerDto.setConformPassword("P@assword123");
        registerDto.setFirstName("Test");
        registerDto.setLastname("User");
        registerDto.setGender("Male");
        registerDto.setDateOfBirth(null);
        registerDto.setMobileNumber("9010200645");
        registerDto.setRole(Role.ADMIN);
 
        when(appUserServiceImpl.register(registerDto)).thenReturn(MessageConstants.registerd_success);
        
        String result = appUserServiceImpl.register(registerDto);
        assertEquals(MessageConstants.registerd_success, result);


    }
 
 
    @Test
    public void testRegisterUserPasswordMismatch() throws AlreadyExsistException {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUserName("testUser@gmail.com");
        registerDto.setPassword("password123");
        registerDto.setConformPassword("password456");
        registerDto.setFirstName("Test");
        registerDto.setLastname("User");
        registerDto.setGender("Male");
        registerDto.setDateOfBirth(null);
        registerDto.setMobileNumber("1234567890");
        registerDto.setRole(Role.ADMIN);
 
        when(appUserServiceImpl.register(registerDto)).thenReturn(MessageConstants.password_confirm_donot_match);
 
        String result = appUserServiceImpl.register(registerDto);
 
        assertEquals(MessageConstants.password_confirm_donot_match, result);
    }

    @Test
    public void testValidLoginCredentials() throws InvalidInputException {
        String email = "testUser@gmail.com";
        String password = "password123";
        AppUser testUser = new AppUser();
        testUser.setUserName("testUser@gmail.com");
        testUser.setPassword("password123");
        testUser.setFirstName("Test");
        testUser.setLastName("User");
        testUser.setGender("Male");
        testUser.setDob(null);
        testUser.setMobileNumber("1234567890");
        testUser.setRole(Role.ADMIN);
        LoginDto value= new LoginDto();
        value.setUserName("testUser@gmail.com");
        value.setGender("Male");
        value.setMobileNumber("1234567890");
        value.setName("Test User");
        value.setDateOfBirth(null);
        value.setRole(Role.ADMIN);
        when(appUserServiceImpl.login(email,password)).thenReturn(value);
        LoginDto result = appUserServiceImpl.login(email, password);
        assertNotNull(result);
        assertEquals("testUser@gmail.com", result.getUserName());
        assertEquals("Test User", result.getName());
        assertEquals(Role.ADMIN, result.getRole());
        assertEquals("1234567890", result.getMobileNumber());
        assertEquals("Male", result.getGender());
        assertEquals(null, result.getDateOfBirth());
    }
 
    
    @Test
    public void testValidPasswordReset() throws InvalidInputException {
        String email = "testUser@gmail.com";
        String oldPassword = "password123";
        String newPassword = "newPassword123";
        AppUser testUser = new AppUser();
        testUser.setPassword(oldPassword);
        when(appUserServiceImpl.resetPassword(email,oldPassword,newPassword)).thenReturn(MessageConstants.password_reset_sucess);
        String result = appUserServiceImpl.resetPassword(email, oldPassword, newPassword);
        assertEquals(MessageConstants.password_reset_sucess, result);
 
    }
 
    @Test
    public void testCheckAvailability_userAvailable() throws InvalidUserException {
        String username = "testUser";
        when(appUserServiceImpl.checkAvailability(username)).thenReturn(MessageConstants.user_exists);
 
        String result = appUserServiceImpl.checkAvailability(username);
 
        assertEquals(MessageConstants.user_exists, result);
    }
 
    @Test
    public void testVerifyOtpWithValidInput() throws InvalidInputException {
        String email = "test@test.com";
        String otp = "123456";
        Verification verification = new Verification();
        verification.setUserName(email);
        verification.setOtp(otp);
        when(appUserServiceImpl.VerifyOtp(email, otp)).thenReturn(MessageConstants.otp_matched);

        String result = appUserServiceImpl.VerifyOtp(email, otp);

        assertEquals(MessageConstants.otp_matched, result);
    }
 
    @Test
    public void testChangePasswordWithValidInput() throws InvalidInputException {
        String email = "test@test.com";
        String password = "new_password";
        AppUser appUser = new AppUser();
        appUser.setUserName(email);
        appUser.setPassword("old_password");
        when(appUserServiceImpl.ChangePassword(email, password)).thenReturn(MessageConstants.password_reset_sucess);
 
        String result = appUserServiceImpl.ChangePassword(email, password);
 
        assertEquals(MessageConstants.password_reset_sucess, result);
    }
 
 

}
 

