package com.example.student_jpa.Dao;

import com.example.student_jpa.pojo.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClazzReporsitory extends JpaRepository<Clazz,Long>, JpaSpecificationExecutor<Clazz> {

	Clazz findByName(String Name);

}
