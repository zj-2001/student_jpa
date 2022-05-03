package com.example.student_jpa.Dao;

import com.example.student_jpa.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentReporsitory extends JpaSpecificationExecutor<Student>, JpaRepository<Student,Long> {

		
}
