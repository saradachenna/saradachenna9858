package com.example.fullStackProject.services;

import java.util.List;


import com.example.fullStackProject.model.Student;

public interface Studentservice {
	Student saveStudent(Student stdobj);
	List<Student>readAllStudents();
	Student fetchById(int studentId) throws Exception ;
	Student updatestudent(int id,Student value);
	void deleteStudent(int id);

}

 
