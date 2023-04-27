package com.ebook.serviceImpl;
 
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
 
import com.ebook.dto.LoginDto;
import com.ebook.dto.RegisterDto;
import com.ebook.entity.AppUser;
import com.ebook.entity.Verification;
import com.ebook.exceptions.AlreadyExsistException;
import com.ebook.exceptions.InvalidInputException;
import com.ebook.exceptions.InvalidUserException;
import com.ebook.repo.UserRepo;
import com.ebook.repo.VerificationRepo;
import com.ebook.service.AppUserService;
@Service
public  class AppUserServiceImpl implements AppUserService{
 
    @Autowired
    private UserRepo appUserRepo;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VerificationRepo verificationrepo;

    
    @Override
    public String register(  RegisterDto registerDTO) throws AlreadyExsistException  {
        if(appUserRepo.findById(registerDTO.getUserName()).isEmpty()) {
            System.out.println(registerDTO);
            if(registerDTO.getPassword().equals(registerDTO.getConformPassword())){
 
                AppUser appUser=new AppUser();
                appUser.setUserName(registerDTO.getUserName());
                appUser.setFirstName(registerDTO.getFirstName());
                appUser.setLastName(registerDTO.getLastname());
                appUser.setGender(registerDTO.getGender());
                appUser.setDob(registerDTO.getDateOfBirth());
                appUser.setMobileNumber(registerDTO.getMobileNumber());
                appUser.setPassword(registerDTO.getPassword());
                appUser.setRole(registerDTO.getRole());    
                appUserRepo.save(appUser);
            }
            else {
                return SucessConstants.password_confirm_donot_match;
            }
        }
        else {
            throw new AlreadyExsistException(ExceptionsConstants.already_exist_exception);
 
        }
        return SucessConstants.registerd_success;
    }

    @Override
    public LoginDto login(String email, String password) throws InvalidInputException {
    Optional<AppUser> loginObj= appUserRepo.findById(email);
    //String s="";
    if(loginObj.isPresent()) {
        if(loginObj.get().getPassword().equals(password)) {

        LoginDto obj=new LoginDto();
        obj.setUserName(loginObj.get().getUserName());
        String name=loginObj.get().getFirstName()+" "+loginObj.get().getLastName();
        obj.setName(name);
        obj.setRole(loginObj.get().getRole());
        obj.setMobileNumber(loginObj.get().getMobileNumber());
        obj.setGender(loginObj.get().getGender());
 
        obj.setDateOfBirth(loginObj.get().getDob());
        return obj;
        }
        else {
            throw new InvalidInputException(ExceptionsConstants.incorrect_password_exception);
        }
 
    }
    else {
        throw new InvalidInputException(ExceptionsConstants.incorrect_username_exception);
    }
 
    }

    @Override
    public String resetPassword(String email, String password, String newPassword) throws InvalidInputException {
        Optional<AppUser> loginObj= appUserRepo.findById(email);
        //String s="";
        if(loginObj.isPresent()) {
            if(loginObj.get().getPassword().equals(password)) {

            //set the password with new Password and save it
            AppUser user = loginObj.get();
            user.setPassword(newPassword);
            appUserRepo.save(user);
            return SucessConstants.password_reset_sucess;
        }
            else {
                throw new InvalidInputException(ExceptionsConstants.invalid_password_exception);
            }
        }
        else {
            throw new InvalidInputException(ExceptionsConstants.invalid_username_exception);

        }
    }
 
    @Override
    public String checkAvailability(String username) throws InvalidUserException {
        // TODO Auto-generated method stub
        Optional<AppUser> user=appUserRepo.findByuserName(username);
        if(user.isPresent()) {
            return SucessConstants.user_exists;
        }
        else
        {
            throw new InvalidUserException(ExceptionsConstants.user_not_found_exception);
        }
    }
  
    @Override
     public String sendEmail(String toEmail) throws InvalidInputException {
         Optional<AppUser> appUser=appUserRepo.findById(toEmail);
         if(appUser.isPresent()) {
             SimpleMailMessage message = new SimpleMailMessage();
             message.setTo(toEmail);
             message.setSubject(SucessConstants.email_verification_subject);
             String code = RandomStringUtils.randomNumeric(4);
             message.setText(code);
             mailSender.send(message);
             Verification verification= new Verification();
             verification.setUserName(toEmail);
             verification.setOtp(code);
             verificationrepo.save(verification);
             return SucessConstants.email_success;
         }
         else {
             throw new InvalidInputException (ExceptionsConstants.email_invalidId_exception);
             }
     }

    @Override
    public String VerifyOtp(String email, String otp) throws InvalidInputException {
        Optional<Verification> obj= verificationrepo.findById(email);
        if(obj.isPresent() ) {
           if(obj.get().getOtp().equals(otp)) {
            verificationrepo.deleteById(email);
            return SucessConstants.otp_matched;
           }
           else {
               throw new InvalidInputException(ExceptionsConstants.invalidId_otpmismatch_exception);
           }

        }
        else {
            throw new InvalidInputException(ExceptionsConstants.invalId_email_exception);
        }    
    }


     @Override
     public String ChangePassword(String email, String password) throws InvalidInputException {
         Optional<AppUser> appUser=appUserRepo.findById(email);
         if(appUser.isPresent() ) {
             System.out.println(appUser.get().getPassword());
             if(!appUser.get().getPassword().equals(password)) {
             AppUser user=appUser.get();
             user.setPassword(password);
             appUserRepo.save(user);
             return SucessConstants.password_reset_sucess;
             }
             else {
             return ExceptionsConstants.invalid_newpassword_exception;
             }
         }
         throw new InvalidInputException(ExceptionsConstants.invalId_email_exception);
  
     }

     @Override
     public String Cancel(String email) throws InvalidInputException {
         Optional<Verification> obj= verificationrepo.findById(email);
         if(obj.isPresent() ) {
             verificationrepo.deleteById(email);
             return SucessConstants.reset_request_canceled;
            }
            else {
                throw new InvalidInputException(ExceptionsConstants.invalId_email_exception);
            }
     } 
}

