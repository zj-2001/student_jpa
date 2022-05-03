package com.example.student_jpa.Dao;

import com.example.student_jpa.pojo.Gradeclass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GradeclassReporsitory extends JpaRepository<Gradeclass,Long>, JpaSpecificationExecutor<Gradeclass> {

	Gradeclass findByGradeClassName(String gradeClassName);
}
