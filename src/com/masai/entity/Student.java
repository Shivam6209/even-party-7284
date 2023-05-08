package com.masai.entity;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String firstName;
	private String lastName;
	private String address;
	private String mobileNumber;
	private String email;
	private String password;
	private List<Course> registeredCourses;

	public Student(String firstName, String lastName, String address, String mobileNumber, String email,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.registeredCourses = new ArrayList<>();
	}

	public Student() {

	}

	public void registerCourse(List<Course> course) {
		this.registeredCourses = course;
	}

	public void updatePersonalDetails(String fieldName, String newValue) {
		switch (fieldName) {
		case "firstName":
			this.firstName = newValue;
			System.out.println("First name updated successfully.");
			break;
		case "lastName":
			this.lastName = newValue;
			System.out.println("Last name updated successfully.");
			break;
		case "address":
			this.address = newValue;
			System.out.println("Address updated successfully.");
			break;
		case "mobileNumber":
			this.mobileNumber = newValue;
			System.out.println("Mobile number updated successfully.");
			break;
		case "email":
			this.email = newValue;
			System.out.println("Email updated successfully.");
			break;
		default:
			System.out.println("Invalid field name. Please try again.");
			break;
		}
	}

	public void updatePersonalDetails(String firstName, String lastName, String address, String mobileNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		System.out.println("Personal details updated successfully!");
	}

	public void changePassword(String oldPassword, String newPassword) {
		if (this.password.equals(oldPassword)) {
			this.password = newPassword;
			System.out.println("Password changed successfully!");
		} else {
			System.out.println("Old password is incorrect!");
		}
	}

	public void viewAvailableCourses(List<Course> courses) {
		System.out.println("List of available courses:");
		for (Course course : courses) {
			System.out.println(course.getCourseName());
		}
	}

	public void viewCourseBatches(List<Course> courses) {
		System.out.println("Available Batches:");
		for (Course course : courses) {
			System.out.println(course.getCourseName() + ":");
			// List<Batch> batches = course.getBatches();
			// for (Batch batch : batches) {
			// System.out.println("Batch " + batch);
			// }
		}
	}

	// getter and setter
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Course> getRegisteredCourses() {
		return registeredCourses;
	}

	public void setRegisteredCourses(List<Course> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}

}
