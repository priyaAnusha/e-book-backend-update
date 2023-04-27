package com.ebook.dto;

import java.sql.Date;

import com.ebook.enums.Role;

public class LoginDto {



    private String userName;
    private String name;
    private Role role;
    private String mobileNumber;
    private String gender;
    private Date dateOfBirth;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
	@Override
	public String toString() {
		return "LoginDto [userName=" + userName + ", name=" + name + ", role=" + role + ", mobileNumber=" + mobileNumber
				+ ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + "]";
	}
	public LoginDto(String userName, String name, Role role, String mobileNumber, String gender, Date dateOfBirth) {
		super();
		this.userName = userName;
		this.name = name;
		this.role = role;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}
	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}
   
    
 
}

