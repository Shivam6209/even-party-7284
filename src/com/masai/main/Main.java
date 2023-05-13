package com.masai.main;

import java.util.*;
import com.masai.service.*;
import com.masai.entity.Admin;
import com.masai.entity.Batch;
import com.masai.entity.Course;
import com.masai.entity.Student;
import com.masai.exception.BatchNotFoundException;
import com.masai.exception.CourseNotFoundException;
import com.masai.exception.DuplicateEmailException;
import com.masai.exception.InvalidDateException;

public class Main {
  static final Student STUDENT=new Student();
  
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_PURPLE = "\u001B[34m";
  public static final String ANSI_RED = "\u001B[31m";
  
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			Admin admin = new Admin();
//			System.out.println();
			System.out.println(ANSI_RED+"##########################################################################"+ANSI_RESET);
			System.out.println();
			System.out.println( ANSI_YELLOW+" *************Welcome to Student Registration System**************** "+ANSI_RESET);
			System.out.println();
			while (true) {
				
				System.out.println(ANSI_GREEN+"***Please Select an option:-");
				System.out.println("***Enter:-1 for Admin login  ");
				System.out.println("***Enter:-2 for Student login   ");
				System.out.println("***Enter:-3 for Student SignUp      "+ANSI_RESET);
				

				int choice = scanner.nextInt();
				
				switch (choice) {
				case 1:
					System.out.println(ANSI_PURPLE+"Enter Admin userName:-");
					String adminUsername = scanner.next();
					System.out.println("Enter Admin password:-"+ANSI_RESET);
					String adminPassword = scanner.next();
					if (adminUsername.equals("admin") && adminPassword.equals("admin")) {
						// admin menu
						System.out.println("Login successful.");
						System.out.println(ANSI_YELLOW+"###############################"+ANSI_RESET);
						System.out.println(ANSI_GREEN+"Hello Admin :)"+ANSI_RESET);
						System.out.println(ANSI_YELLOW+"###############################"+ANSI_RESET);
						System.err.println();
						boolean isAdminLogin = true;
						while (isAdminLogin) {
							AdminServiceImpl asi=new AdminServiceImpl();
							try {
								asi.showOption(scanner);
							} catch (CourseNotFoundException | BatchNotFoundException | InvalidDateException e) {
								e.getMessage();
							}
					  }
					}
						else {
						System.out.println("Invalid Credentials!");
					}
					break;

				case 2:
					
					scanner.nextLine();
//					// STUDENT login
					System.out.println(ANSI_RED+"Enter Student email:-");
					String email = scanner.nextLine();
					System.out.println("Enter Student password:-");
					String password = scanner.nextLine();
					boolean foundStudent = false;
					for (Student stu : admin.getStudents()) {
						if (stu.getEmail().equals(email) && stu.getPassword().equals(password)) {
							// Student menu
							System.out.println("Student login successful."+ANSI_RESET);
							foundStudent = true;
							break;
						}
					}
					if (!foundStudent) {
						System.out.println("Invalid Student email or Student password.");
					}
				if (foundStudent) {
						boolean isStudentMenuActive = true;
						while (isStudentMenuActive) {
                            StudentServiceImpl ssi=new StudentServiceImpl();
                            
                            System.out.println(ANSI_RED+"                                ");
                    		System.out.println("*********************************");
                    		System.out.println(" Please select an option:     ");
                    		
                    		System.out.println(" 1:- Update Student details     ");
                    		System.out.println(" 2:- Change Student Password      ");
                    		System.out.println(" 3:- View All available courses     ");
                    		System.out.println(" 4:- View all available batches       ");
                    		System.out.println(" 5:- Register for a course       ");
                    		System.out.println(" 0:- Exit                      ");
                    		System.out.println("********************************");
                    		System.out.println("                                "+ANSI_RESET);
							choice = scanner.nextInt();
							scanner.nextLine();

							switch (choice) {
							case 1:
								// Update details
								System.out.println(ANSI_RED+"Please select a field to update:");
								System.out.println("Enter:-1 to update First name");
								System.out.println("Enter:-2 to update Last name");
								System.out.println("Enter:-3 to update Address");
								System.out.println("Enter:-4 to update Mobile number");
								System.out.println("Enter:-5 to update Email"+ANSI_RESET);
								int fieldOption = scanner.nextInt();
								scanner.nextLine();
								System.out.println("Please enter the new value of selected option your choice is:"+fieldOption);
								String newValue = scanner.nextLine();
								switch (fieldOption) {
								case 1:
									STUDENT.updatePersonalDetails("firstName", newValue);
									break;
								case 2:
									STUDENT.updatePersonalDetails("lastName", newValue);
									break;
								case 3:
									STUDENT.updatePersonalDetails("address", newValue);
									break;
								case 4:
									STUDENT.updatePersonalDetails("mobileNumber", newValue);
									break;
								case 5:
									STUDENT.updatePersonalDetails("email", newValue);
									break;
								default:
									System.out.println("Invalid field option. Please try again.");
									break;
								}
								System.out.println("Details updated successfully.");
								break;

							case 2:
								// Change password
								System.out.println("                                ");

								System.out.println("Enter old password:");
								String currentPassword = scanner.nextLine();

								System.out.println("Enter new password:");
								String newPass = scanner.nextLine();
								if (STUDENT == null) {
									System.out.println("Student not found.");
								} else {
									if (STUDENT.getPassword().equals(currentPassword)) {
										STUDENT.setPassword(newPass);
										System.out.println("Password changed successfully.");
									} else {
										System.out.println("Invalid password.");
									}
								}

								break;

							case 3:
								// View available courses
								System.out.println("                                ");
								System.out.println("List of courses:-"
										+ "");
								for (Course c : admin.getCourses()) {
									System.out.println("Course: " + c.getCourseName() + ", Duration: " + c.getDurationInDays()
											+ ", Fees: " + c.getFee());
								}
								break;

							case 4:
								// View all batches
								System.out.println("                                ");
								System.out.println("List of batches:");
								for (Batch b : admin.getBatches()) {
									System.out.println("Batch Name "+b.getBatchName() + "(" + b.getBatchName()
											+ ") -> Start Date: " + b.getStartDate() + ", End Date: " + b.getEndDate()
											+ ", Seats: " + b.getMaxCapacity());
								}
								break;

							case 5:
								// Register for course
								System.out.println("                                ");
								System.out.println("Enter course name:");
								String courseName = scanner.nextLine();

								List<Course> course = new ArrayList<>();
								for (Course c : admin.getCourses()) {
									if (c.getCourseName().equals(courseName)) {
										course.add(c);
										break;
									}
								}

								if (course.isEmpty()) {
									System.out.println("Course not found. Please try again");
								} else {
									STUDENT.registerCourse(course);
									System.out.println("Course successfully registered.");
								}
								break;
							case 0:
								// Exit
								System.out.println("                                ");
								System.out.println("Exiting program...");
								isStudentMenuActive = false;
								break;
							default:
								// Invalid choice
								System.out.println("                                ");
								System.out.println("Invalid choice.");
								break;

						}
						}
				}
					break;
				case 3:
					// SignUp
					scanner.nextLine();
					System.out.println(ANSI_GREEN+"                                ");
					System.out.println("Enter STUDENT first name for signup:");
					String firstName = scanner.nextLine();
					System.out.println("Enter STUDENT last name for signup:");
					String lastName = scanner.nextLine();
					System.out.println("Enter STUDENT address for signup:");
					String address = scanner.nextLine();
					System.out.println("Enter STUDENT mobile number for signup:");
					String mobile = scanner.nextLine();
					System.out.println("Enter STUDENT email for signup:");
					String email1 = scanner.nextLine();

					System.out.println("Enter STUDENT password for signup:"+ANSI_RESET);
					String password1 = scanner.nextLine();
					Student students = new Student(firstName, lastName, address, mobile, email1, password1);
					admin.getStudents().add(students);
					System.out.println("Student added successfully....");
					break;
				case 4:
					System.out.println("Successfully Exit!");
					break;
				default:
					System.out.println("Invalid choice.");
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
