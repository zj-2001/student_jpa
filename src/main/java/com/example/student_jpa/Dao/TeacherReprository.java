package com.example.student_jpa.Dao;

import com.example.student_jpa.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TeacherReprository extends JpaRepository<Teacher,Long>, JpaSpecificationExecutor<Teacher> {

	Teacher findByTeacherName(String teacherName);
}
