package com.ebook.controllers;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

 
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ebook.exceptions.DoesNotExistsException;
import com.ebook.service.EmailService;
 
@RestController
@RequestMapping("/email")
public class EmailController {
 
    @Autowired
    private EmailService emailService;

   
 
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestParam("to") String to,
                                             @RequestParam("subject") String subject,
                                             @RequestParam("text") String text,
                                             @RequestParam(value = "pdfFile", required = false) MultipartFile pdfFile,
                                             @RequestParam(value = "wordFile", required = false) MultipartFile wordFile) throws DoesNotExistsException {
        try {
            File pdf = null;
            File word = null;
            if (pdfFile != null) {
                pdf = convertMultipartFileToFile(pdfFile);
            }
            if (wordFile != null) {
                word = convertMultipartFileToFile(wordFile);
            }
            emailService.sendEmailWithAttachments(to, subject, text, pdf, word);
            return ResponseEntity.ok("Email sent successfully!");
        } catch (EmailException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: " + e.getMessage());
        }
    }
 
    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


}
 


