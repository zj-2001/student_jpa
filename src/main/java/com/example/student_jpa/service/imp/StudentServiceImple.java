package com.example.student_jpa.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.student_jpa.Dao.ClazzReporsitory;
import com.example.student_jpa.Dao.GradeclassReporsitory;
import com.example.student_jpa.Dao.StudentReporsitory;
import com.example.student_jpa.Dao.TeacherReprository;
import com.example.student_jpa.pojo.Clazz;
import com.example.student_jpa.pojo.Gradeclass;
import com.example.student_jpa.pojo.Student;
import com.example.student_jpa.pojo.Teacher;
import com.example.student_jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImple implements StudentService {

	@Autowired
	StudentReporsitory studentReporsitory;

	@Autowired
	GradeclassReporsitory gradeclassReporsitory;

	@Autowired
	ClazzReporsitory clazzReporsitory;

	@Autowired
	TeacherReprository teacherReprository;

	@Override
	public int addNewStudent(String stujson) {
		JSONObject jsonObject = JSON.parseObject(stujson);
		String name = jsonObject.getString("name");
		String gradeClassName = jsonObject.getString("gradeClassName");
		JSONArray clazzName = jsonObject.getJSONArray("clazzName");
		JSONArray teacherName = jsonObject.getJSONArray("teacherName");
		Student student=new Student();
		student.setName(name);
		Gradeclass gradeclass=gradeclassReporsitory.findByGradeClassName(gradeClassName);
		if(gradeclass==null){
			gradeclass=new Gradeclass();
			gradeclass.setGradeClassName(gradeClassName);
		}
		student.setGradeClass(gradeclass);
		for(Object temp:clazzName){
			Clazz clazz=new Clazz();
			clazz.setName((String)temp);
			student.getClazzes().add(clazz);
		}
		for(Object temp:teacherName) {
			Teacher teacher = new Teacher();
			teacher.setTeacherName((String)temp);
			student.getTeachers().add(teacher);
		}
		Student save = studentReporsitory.save(student);
		if(save!=null){
			return 1;
		}
		return 0;

	}

	@Override
	public Student viewOne(Long id) {

		Optional<Student> byId = studentReporsitory.findById(id);

		if(byId.isPresent()) {
			return byId.get();
		}
		return null;

	}

	@Override
	public int removeStudent(Long id) {

		//判断id是否存在再删除,否则会报错
			if(studentReporsitory.existsById(id)) {
				studentReporsitory.deleteById(id);
				return 1;
			}
			return 0;

	}

	@Override
	@Transactional
	public int alterStudent(String stujson) {
		JSONObject jsonObject = JSON.parseObject(stujson);
		Long stuId = jsonObject.getLong("stuId");
		String name = jsonObject.getString("name");
		String gradeClassName = jsonObject.getString("gradeClassName");
		JSONArray clazzName = jsonObject.getJSONArray("clazzName");
		JSONArray teacherName = jsonObject.getJSONArray("teacherName");
		Student student=new Student();
		student.setName(name);
		student.setStuId(stuId);

		Gradeclass gradeclass=new Gradeclass();
		gradeclass.setGradeClassName(gradeClassName);
		student.setGradeClass(gradeclass);
		for(Object temp:clazzName){
			Clazz clazz=new Clazz();
			clazz.setName((String)temp);
			student.getClazzes().add(clazz);
		}
		for(Object temp:teacherName) {
			Teacher teacher = new Teacher();
			teacher.setTeacherName((String)temp);
			student.getTeachers().add(teacher);
		}
		 studentReporsitory.deleteById(student.getStuId());
		 Student changedStudent=new Student(student.getName(),student.getGradeClass(),student.getClazzes(),student.getTeachers());
		Student save = studentReporsitory.save(changedStudent);
		if(save==null)
			return 0;
		return 1;


	}

	@Override
	public List<Student> findAll() {
		List<Student> all = studentReporsitory.findAll();
		return all;
	}
}
