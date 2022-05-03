package com.example.student_jpa.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.student_jpa.pojo.Student;
import com.example.student_jpa.service.imp.StudentServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("jpa")
public class JpaController {
	@Autowired
	StudentServiceImple studentServiceImple;

	@RequestMapping("/findAll")
	public String findAll(){
		List<Student> all = studentServiceImple.findAll();
		if(all.size()==0){
			return "莫得学生";
		}
		return all.toString();

	}

	@RequestMapping("/addStudent")
	public String addStudent(@RequestBody String json){

		int result = studentServiceImple.addNewStudent(json);
		if(result==0)
			return "添加失败";
		return "添加成功";
	}
	@RequestMapping("/deleteStudent")
	public String deleteStudent(@RequestBody String json){
		JSONObject jsonObject = JSON.parseObject(json);
		Long stuId = jsonObject.getLong("stuId");
		int result=studentServiceImple.removeStudent(stuId);
		if(result==0)
			return "删除失败";
		return "删除成功";

	}
	@RequestMapping("/viewStudent")
	public String viewStudent(@RequestBody String json){
		JSONObject jsonObject = JSON.parseObject(json);
		Long stuId = jsonObject.getLong("stuId");
		Student student = studentServiceImple.viewOne(stuId);
		if(student==null)
			return "莫得学生";
		return student.toString();
	}
	@RequestMapping("/changeStudent")
	public String changeStudent(@RequestBody String json){

		int result=studentServiceImple.alterStudent(json);
		if(result==0)
			return "添加失败";
		return "添加成功";

	}


}
