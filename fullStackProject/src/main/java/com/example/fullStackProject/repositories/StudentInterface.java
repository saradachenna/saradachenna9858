package com.example.fullStackProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fullStackProject.model.Student;

public interface StudentInterface extends JpaRepository<Student,Integer> {

}
