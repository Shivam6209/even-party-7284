package com.masai.service;

import java.util.Scanner;

import com.masai.exception.BatchNotFoundException;
import com.masai.exception.CourseNotFoundException;
import com.masai.exception.InvalidDateException;

public interface AdminService {
	
   public void showOption(Scanner sc) throws CourseNotFoundException, BatchNotFoundException, InvalidDateException;
   public void addCourse(Scanner sc) throws CourseNotFoundException;
   public void courseDelete(Scanner sc);
   public void updateCourseDetail(Scanner sc) throws CourseNotFoundException;
   public void addBatch(Scanner sc) throws InvalidDateException;
   public void deleteBatch(Scanner sc);
   public void updateBatchDetail(Scanner sc) throws BatchNotFoundException;
   public void viewAllCourse(Scanner sc);
   public void viewAllBatch(Scanner sc);
   public void viewBatchWiseReport(Scanner sc);
   
   
}