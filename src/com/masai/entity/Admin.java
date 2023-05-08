package com.masai.entity;

import java.util.ArrayList;
import java.util.List;


public class Admin {
	
		private List<Course> courses;

		private List<Batch> batches;

		private List<Student> students;
		
		
		
		public Admin() {
			courses = new ArrayList<Course>();
			batches = new ArrayList<Batch>();
			students = new ArrayList<Student>();
		}
		
		public Course getCourseByName(String name) {
			for (Course course : courses) {
				if (course.getCourseName().equals(name)) {
					return course;
				}
			}
			return null; 
		}
		public Course getCourseByID(long id) {
			for (Course course : courses) {
				if (course.getCourseID()==id) {
					return course;
				}
			}
			return null; 
		}
		
		public Batch getBatchByName(String name) {
			for (Batch batch : batches) {
				if (batch.getBatchName().equals(name)) {
					return batch;
				}
			}
			return null; 
		}

		public List<Course> getCourses() {
			return courses;
		}

		public void setCourses(List<Course> courses) {
			this.courses = courses;
		}

		public List<Batch> getBatches() {
			return batches;
		}

		public void setBatches(List<Batch> batches) {
			this.batches = batches;
		}

		public List<Student> getStudents() {
			return students;
		}

		public void setStudents(List<Student> students) {
			this.students = students;
		}
		
		
}
