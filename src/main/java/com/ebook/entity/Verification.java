package com.ebook.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Verification {
	@Id
    private String UserName;
    private String Otp;
    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }
    public String getOtp() {
        return Otp;
    }
    public void setOtp(String otp) {
        Otp = otp;
    }
    public Verification(String userName, String otp) {
        super();
        UserName = userName;
        Otp = otp;
    }
    public Verification() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        return "Verification [UserName=" + UserName + ", Otp=" + Otp + "]";
    }

}
