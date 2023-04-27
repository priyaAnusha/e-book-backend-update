package com.ebook.entity;


import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ebook.enums.Role;

@Entity
public class AppUser {
	@Id
private String userName;
private String firstName;
private String lastName;
private Date dob;
//private String country;
private String gender;
private String password;
private String mobileNumber;
private Role role;
@OneToMany(mappedBy = "author")
private List<BookEntity> books;
@OneToMany(mappedBy = "requestedAuthor" )
private List<EbookManagement> requests;
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
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
//public String getCountry() {
//	return country;
//}
//public void setCountry(String country) {
//	this.country = country;
//}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
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
public List<BookEntity> getBooks() {
	return books;
}
public void setBooks(List<BookEntity> books) {
	this.books = books;
}
public List<EbookManagement> getRequests() {
	return requests;
}
public void setRequests(List<EbookManagement> requests) {
	this.requests = requests;
}
//@Override
//public String toString() {
//	return "AppUser [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
//			+ ", country=" + country + ", gender=" + gender + ", password=" + password + ", mobileNumber="
//			+ mobileNumber + ", role=" + role + ", books=" + books + ", requests=" + requests + "]";
//}

public AppUser(String userName, String firstName, String lastName, Date dob, String country, String gender,
		String password, String mobileNumber, Role role, List<BookEntity> books, List<EbookManagement> requests) {
	super();
	this.userName = userName;
	this.firstName = firstName;
	this.lastName = lastName;
	this.dob = dob;
//	this.country = country;
	this.gender = gender;
	this.password = password;
	this.mobileNumber = mobileNumber;
	this.role = role;
	this.books = books;
	this.requests = requests;
}

@Override
public String toString() {
	return "AppUser [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
			+ ", gender=" + gender + ", password=" + password + ", mobileNumber="
			+ mobileNumber + ", role=" + role + ", books.size()=" + books.size() + ", requests.size()=" + requests.size() + "]";
}
public AppUser() {
	super();
	// TODO Auto-generated constructor stub
}

}
