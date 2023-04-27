package com.ebook.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ebook.enums.Role;

public class RegisterDto {


    @Email(message = "Please provide a valid email address")
    private String userName;
    @NotEmpty 
    @Size(min=3,message="firstName should be min of 4")
    private String firstName;
    @NotEmpty
    @Size(min=3,message="firstName should be min of 4")
    private String lastname;
    @NotEmpty
    private String gender;
 
    @Past(message = "Please provide a valid email address")
    private Date dateOfBirth;
   @Pattern (regexp="^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",message="enter valid mobileNumber")
    private String mobileNumber;
    @NotNull
    private Role role;
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$", message = "Password should contain at least 6 characters and maximum of 20 characters  including at least one uppercase letter, one lowercase letter, and one number")
    private String password;
    private String conformPassword;

    public String getConformPassword() {
        return conformPassword;
    }
    public void setConformPassword(String conformPassword) {
        this.conformPassword = conformPassword;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
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
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
	@Override
	public String toString() {
		return "RegisterDto [userName=" + userName + ", firstName=" + firstName + ", lastname=" + lastname + ", gender="
				+ gender + ", dateOfBirth=" + dateOfBirth + ", mobileNumber=" + mobileNumber + ", role=" + role
				+ ", password=" + password + ", conformPassword=" + conformPassword + "]";
	}
	public RegisterDto(@Email(message = "Please provide a valid email address") String userName,
			@NotEmpty @Size(min = 3, message = "firstName should be min of 4") String firstName,
			@NotEmpty @Size(min = 3, message = "firstName should be min of 4") String lastname, @NotEmpty String gender,
			@Past(message = "Please provide a valid email address") Date dateOfBirth,
			@Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "enter valid mobileNumber") String mobileNumber,
			@NotNull Role role,
			@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$", message = "Password should contain at least 6 characters and maximum of 20 characters  including at least one uppercase letter, one lowercase letter, and one number") String password,
			String conformPassword) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastname = lastname;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.role = role;
		this.password = password;
		this.conformPassword = conformPassword;
	}
	public RegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}

