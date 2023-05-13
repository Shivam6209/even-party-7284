package com.masai.service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.masai.entity.Admin;
import com.masai.entity.Batch;
import com.masai.entity.Course;
import com.masai.exception.BatchNotFoundException;
import com.masai.exception.CourseNotFoundException;
import com.masai.exception.InvalidDateException;
import com.masai.main.Main;

public class AdminServiceImpl implements AdminService {
	
	static Admin ADMIN = new Admin();
	public static final String ANSI_RESET = "\u001B[0m";
	  public static final String ANSI_YELLOW = "\u001B[33m";
	  
	  public static final String ANSI_RED = "\u001B[32m";
	  public static final String ANSI_PURPLE = "\u001B[32m";
	  public static final String ANSI_RED_BACK = "\u001B[41m";
	@Override
	public void showOption(Scanner sc) throws CourseNotFoundException, BatchNotFoundException, InvalidDateException {
		     System.out.println();
	    	 System.out.println(ANSI_RED_BACK+"Enter 1:-for Add new Courses");
	    	 System.out.println("Enter 2:-for Delete course from List");
	    	 System.out.println("Enter 3:-for Update details of course");
	    	 System.out.println("Enter 4:-for Create a Batch under a course.");
	    	 System.out.println("Enter 5:-for Delete Batch From List");
	    	 System.out.println("Enter 6:-for Update details of batch");
	    	 System.out.println("Enter 7:-for View All The Courses");
	    	 System.out.println("Enter 8:-for View All The Batches");
	    	 System.out.println("Enter 9:-for Exit"+ANSI_RESET);
	    	 int adminChoice=sc.nextInt();
	    	 switch (adminChoice) {
	    	 
			case 1: {
				addCourse(sc);
				break;
			}
			case 2: {
				courseDelete(sc);
				break;
			}
			case 3: {
				updateCourseDetail(sc);
				break;
			}
			case 4: {
				addBatch(sc);
				break;
			}
			case 5:{
				deleteBatch(sc);
				break;
			}
			case 6:{
				updateBatchDetail(sc);
				break;
			}
			case 7:{
				viewAllCourse(sc);
				break;
			}
			case 8:{
				
				viewAllBatch(sc);
				break;
			}
			case 9:{
				viewBatchWiseReport(sc);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + adminChoice);
			}
		
	}
	
	

	@Override
	public void addCourse(Scanner scanner) throws CourseNotFoundException {
		System.out.println(ANSI_RED+"                                ");
		System.out.println("Enter course name:");
		scanner.nextLine();
		String courseName = scanner.nextLine();
		System.out.println("Enter course duration in weeks:");
		int courseDuration = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter course fees in rupees");
		double courseFees = scanner.nextDouble();
		System.out.println("Enter Unique Course ID");
		long courseId=scanner.nextLong();
		scanner.nextLine();
		Course course = new Course(courseName, courseDuration, courseFees,courseId);
		
		for (Course c : ADMIN.getCourses()) {
			if (c.getCourseName().equals(course.getCourseName())) {
				throw new CourseNotFoundException("Course with same name already exists"+ANSI_RESET);
			}
		}
		
		ADMIN.getCourses().add(course);
		System.out.println();
		System.out.println(ANSI_PURPLE+"***************************");
		System.out.println("Course added successfully.");
		System.out.println("***************************"+ANSI_RESET);
	}

	@Override
	public void courseDelete(Scanner sc) {
		System.out.println(ANSI_RED+"                                ");
		System.out.println("Enter course name to be deleted:");
		sc.nextLine();
		String courseNameToDelete = sc.nextLine();
		Course courseToDelete = ADMIN.getCourseByName(courseNameToDelete);
		if (courseToDelete == null) {
			System.out.println("Course not found.");
		} else {
			ADMIN.getCourses().remove(courseToDelete);
			System.out.println("Given Course Name deleted successfully.");
		}
		
	}

	@Override
	public  void updateCourseDetail(Scanner sc) throws CourseNotFoundException {
		
		System.out.println(ANSI_PURPLE+"Enter the Course ID or name In Which you want update");
		String updateChoice=sc.next();
	    Course courseToUpdate=ADMIN.getCourseByName(updateChoice);
	    if(courseToUpdate==null) {
	    	throw new CourseNotFoundException("Course Not Found in The List");
	    }else {
			System.out.println("Enter the field in which you want update");
			System.out.println("Enter:-1 to update CourseID");
			System.out.println("Enter:-2 to update CourseName");
			System.out.println("Enter:-3 to update CourseDuration");
			System.out.println("Enter:-4 to update CourseFee");
			int choice=sc.nextInt();
			switch (choice) {
			case 1: {
				System.out.println("Enter Old Course ID");
				long courseId=sc.nextLong();
			    Course courseByidToUpdate=ADMIN.getCourseByID(courseId);
			    if(courseByidToUpdate==null) {
			    	throw new CourseNotFoundException("Course By Given ID not found!");
			    }else {
			    	courseByidToUpdate.setCourseID(courseId);
			    	System.out.println("Course ID Updated Sucessfully");
			    }
			    break;
			}
			
			case 2:{
				System.out.println("Enter Old Course NAME");
				String courseName=sc.next();
				Course courseByNameToUpdate=ADMIN.getCourseByName(courseName);
				if(courseByNameToUpdate==null) {
					throw new CourseNotFoundException("Course By Given Name not found!");
			    }else {
			    	courseByNameToUpdate.setCourseName(courseName);
			    	System.out.println("Course Name Updated Sucessfully");
			    }
			    break;
			}
			
			case 3: {
				
				System.out.println("Enter Old Course NAME to update duration");
				String courseName=sc.next();
				Course courseByNameToUpdate=ADMIN.getCourseByName(courseName);
				if(courseByNameToUpdate==null) {
					throw new CourseNotFoundException("Course By Given Name not found!");
			    }else {
			    	System.out.println("Enter new Course Duration");
			    	int courseDuration=sc.nextInt();
			    	courseByNameToUpdate.setDurationInDays(courseDuration);
			    	System.out.println("Course Duration Updated Sucessfully");
			    }
			    break;
			}
			
			
			case 4:{
				System.out.println("Enter Old Course NAME to update Fee");
				String courseName=sc.next();
				Course courseByNameToUpdate=ADMIN.getCourseByName(courseName);
				if(courseByNameToUpdate==null) {
					throw new CourseNotFoundException("Course By Given Name not found!");
			    }else {
			    	System.out.println("Enter new Course Fee");
			    	Double courseFee=sc.nextDouble();
			    	courseByNameToUpdate.setFee(courseFee);
			    	System.out.println("Course Fee Updated Sucessfully");
			    }
			    break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		}
		
	}

	@Override
	public void addBatch(Scanner scanner) throws InvalidDateException {
		System.out.println("                                ");
		System.out.println("Enter Batch unique ID:");
		long batchId=scanner.nextLong();
		System.out.println("Enter Batch name:");
		scanner.nextLine();
		String batchName = scanner.nextLine();
		System.out.println("Enter Batch Start date(dd/MM/yyyy):");
		String startDate=scanner.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate startdateLocalDate = LocalDate.parse(startDate, formatter);
		System.out.println("Enter Batch End date(dd/MM/yyyy):");
		String endDate=scanner.next();
		LocalDate enddateLocalDate = LocalDate.parse(endDate, formatter);
		System.out.println("Enter max Capcity of student in a batch");
		int maxCapacity=scanner.nextInt();
		Batch batch=new Batch(batchId, batchName, startdateLocalDate, enddateLocalDate, maxCapacity);
		ADMIN.getBatches().add(batch);
		System.out.println();
		System.out.println("***************************");
		System.out.println("Batch Added Sucessfully");
		System.out.println("***************************");
		System.out.println();
		
		
	}

	@Override
	public void deleteBatch(Scanner sc) {
		System.out.println("                                ");
		sc.nextLine();
		System.out.println("Enter Batch name to be deleted:");
		String batchNameToDelete = sc.nextLine();
		Batch batchToDelete = ADMIN.getBatchByName(batchNameToDelete);
		if (batchToDelete == null) {
			System.out.println("Batch not found.");
		} else {
			ADMIN.getBatches().remove(batchToDelete);
			System.out.println("******************************************");
			
			System.out.println("Given Batch Name deleted successfully.");

			System.out.println("*********************************************");
		}
		
	}

	@Override
	public void updateBatchDetail(Scanner sc) throws BatchNotFoundException {
		System.out.println("Enter BatchName In Which you want update");
		String updateChoice=sc.next();
	    Batch batchToUpdate=ADMIN.getBatchByName(updateChoice);
	    if(batchToUpdate==null) {
	    	throw new BatchNotFoundException("Batch Not Found in The List");
	    }else {
			System.out.println("Enter the field in which you want update");
			System.out.println("Enter:-1 to update BatchName");
			System.out.println("Enter:-2 to update batchStartDate");
			System.out.println("Enter:-3 to update batchEndDate");
			System.out.println("Enter:-4 to update Batch Capacity");
			int choice=sc.nextInt();
			switch (choice) {
			case 1: {
				System.out.println("Enter Old Batch Name");
				sc.nextLine();
				String batchName=sc.nextLine();
			    Batch batchByNameToUpdate=ADMIN.getBatchByName(batchName);
			    if(batchByNameToUpdate==null) {
			    	throw new BatchNotFoundException("Batch By Given name not found!");
			    }else {
			    	batchByNameToUpdate.setBatchName(batchName);
			    	System.out.println("Batch Updated Sucessfully");
			    }
			    break;
			}
			
			case 2:{
				System.out.println("Enter batch NAME in which you wnat to update start date");
				String batchName=sc.next();
				Batch batchByNameToUpdate=ADMIN.getBatchByName(batchName);
				if(batchByNameToUpdate==null) {
				throw new BatchNotFoundException("Batch By Given Name not found!");
			    }else {
			    	System.out.println("Enter new Start date");
			    	String dateString=sc.next();
			    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				    LocalDate startdateLocalDate = LocalDate.parse(dateString, formatter);
				    batchByNameToUpdate.setStartDate(startdateLocalDate);
			    	System.out.println("Batch start date Updated Sucessfully");
			    }
			    break;
			}
			
			case 3: {
				System.out.println("Enter batch NAME in which you wnat to update end date");
				String batchName=sc.next();
				Batch batchByNameToUpdate=ADMIN.getBatchByName(batchName);
				if(batchByNameToUpdate==null) {
				throw new BatchNotFoundException("Batch By Given Name not found!");
			    }else {
			    	System.out.println("Enter new end date");
			    	String dateString=sc.next();
			    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				    LocalDate enddateLocalDate = LocalDate.parse(dateString, formatter);
				    batchByNameToUpdate.setStartDate(enddateLocalDate);
			    	System.out.println("Batch end date Updated Sucessfully");
			    }
			    break;
			}
			
			
			case 4:{
				System.out.println("Enter Batch NAME to update Capacity");
				String batchName=sc.next();
				Batch batchByNameToUpdate=ADMIN.getBatchByName(batchName);
				if(batchByNameToUpdate==null) {
					throw new BatchNotFoundException("Batch By Given Name not found!");
			    }else {
			    	System.out.println("Enter new Batch Capacity");
			    	int capacity=sc.nextInt();
			    	batchByNameToUpdate.setMaxCapacity(capacity);
			    	System.out.println("Batch Capacity Updated Sucessfully");
			    }
			    break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		}
	}

	@Override
	public void viewAllCourse(Scanner sc) {
		
		for (Course c : ADMIN.getCourses()) {
		  System.out.println(ANSI_RED_BACK+"CourseID:- "+c.getCourseID()+", CourseName:- "+c.getCourseName()+", Duration:- "+c.getDurationInDays()+", Fees:- "+c.getFee()+ANSI_RESET);
		}
		System.out.println();
	}

	@Override
	public void viewAllBatch(Scanner sc) {
		for (Batch b : ADMIN.getBatches()) {
		System.out.println("BatchID:- "+b.getBatchId()+" BatchName:- "+b.getBatchName()+" BatchStartDate:- "+b.getStartDate()+" BatchEndDate:- "+b.getEndDate()+" BatchCapacity:- "+b.getMaxCapacity());	 
		}
			System.out.println();
	}

	@Override
	public void viewBatchWiseReport(Scanner sc) {
	    System.out.println("Exiting from sysytem..."+ANSI_RESET);
	    Main.main(null);
	    
	}
	
	public static Admin returnAdmin() {
		return ADMIN;
		
	}
}
