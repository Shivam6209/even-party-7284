package com.masai.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private String courseName;
	private int durationInDays;
	private double fee;
	private long courseID;
	
	
	public Course( String courseName, int durationInDays, double fee, long courseID ) {
		this.courseName = courseName;
		this.durationInDays = durationInDays;
		this.fee = fee;
		this.courseID=courseID;
	}

	
   
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getDurationInDays() {
		return durationInDays;
	}

	public void setDurationInDays(int durationInDays) {
		this.durationInDays = durationInDays;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}




	public long getCourseID() {
		return courseID;
	}




	public void setCourseID(long courseID) {
		this.courseID = courseID;
	}
	
}
