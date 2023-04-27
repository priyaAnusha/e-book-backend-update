package com.ebookExample.ebookExample;
 
import java.io.File;
import java.util.Optional;
 
import org.apache.commons.mail.EmailException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

 
import com.ebook.entity.AppUser;
import com.ebook.exceptions.DoesNotExistsException;
import com.ebook.repo.UserRepo;

import com.ebook.serviceImpl.EmailServiceImpl;
 
@ExtendWith(MockitoExtension.class)
public class EmailTest {
        @Mock
        private JavaMailSender javaMailSender;


        @Mock
        private JavaMailSender javamailsender;

        @Mock
        private UserRepo userRepo;

        @InjectMocks
        private EmailServiceImpl emailService;
 
       
 
        
        @Test
        public void testSendEmailWithAttachments() throws EmailException, DoesNotExistsException {
    // Mock email details
            String to = "test@example.com";
            String subject = "Test Subject";
            String text = "Test Body";

    //// Mock attachment files
            File pdfFile = new File("The Outsiders.pdf");
            File wordFile = new File("test.docx");

    // Mock AppUser object
            AppUser user = new AppUser();
            user.setUserName("test@example.com");

    // Mock UserRepo method
            Mockito.lenient().when(userRepo.findByuserName(Mockito.anyString())).thenReturn(Optional.of(user));
        }
 
 

}

