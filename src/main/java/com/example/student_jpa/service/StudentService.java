package com.example.student_jpa.service;

import com.example.student_jpa.pojo.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface StudentService {

	default int addNewStudent(String stujson){
		return 0;
	}
	default Student viewOne(Long id){
		return null;
	}
	@Transactional
	default int removeStudent(Long id){
		return 0;
	}
	default int alterStudent(String stujson){
		return 0;
	}

	default List<Student> findAll(){
		return null;
	}
}
