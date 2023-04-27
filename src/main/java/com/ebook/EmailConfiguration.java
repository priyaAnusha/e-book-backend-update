package com.ebook;
import java.util.Properties;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
public class EmailConfiguration {

     @Value("${spring.mail.host}")
        private String host;
 
        @Value("${spring.mail.port}")
        private int port;
 
        @Value("${spring.mail.username}")
        private String username;
 
        @Value("${spring.mail.password}")
        private String password;
 
        @Bean
        public JavaMailSender javaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(host);
            mailSender.setPort(port);
            mailSender.setUsername(username);
            mailSender.setPassword(password);
 
            Properties properties = new Properties();
            properties.setProperty("mail.transport.protocol", "smtp");
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.smtp.starttls.enable", "true");
 
            mailSender.setJavaMailProperties(properties);
 
            return mailSender;
        }
 
}

