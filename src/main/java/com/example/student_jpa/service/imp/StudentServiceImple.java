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
		//1,转换JSON数据，获取相应的value值
		JSONObject jsonObject = JSON.parseObject(stujson);
		String name = jsonObject.getString("name");
		String gradeClassName = jsonObject.getString("gradeClassName");
		JSONArray clazzName = jsonObject.getJSONArray("clazzName");
		JSONArray teacherName = jsonObject.getJSONArray("teacherName");
		//2，新增学生
		Student student=new Student();
		student.setName(name);
		//2.1，通过名字查询该班级是否存在，如果不存在就自己建一个
		Gradeclass gradeclass=gradeclassReporsitory.findByGradeClassName(gradeClassName);
		if(gradeclass==null){
			gradeclass=new Gradeclass();
			gradeclass.setGradeClassName(gradeClassName);
		}
		student.setGradeClass(gradeclass);
		//2.2，通过名字查询课程是否存在，不存在就自己新建
		for(Object temp:clazzName){
			Clazz clazz=clazzReporsitory.findByName((String)temp);
			if(clazz==null) {
				clazz=new Clazz();
				clazz.setName((String) temp);
			}
			student.getClazzes().add(clazz);
		}
		//2.3，查询老师是否存在，不存在就新建一个
		for(Object temp:teacherName) {
			Teacher teacher = teacherReprository.findByTeacherName((String)temp);
			if(teacher==null){
				teacher=new Teacher();
				teacher.setTeacherName((String)temp);
			}
			student.getTeachers().add(teacher);
		}
		//3，保存学生
		Student save = studentReporsitory.save(student);
		if(save!=null){
			return 1;
		}
		return 0;

	}

	@Override
	public Student viewOne(Long id) {

		Optional<Student> byId = studentReporsitory.findById(id);
		//判断是否为空
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
		//1,转换JSON数据，获取相应的value值
		JSONObject jsonObject = JSON.parseObject(stujson);
		Long stuId=jsonObject.getLong("stuId");
		String name = jsonObject.getString("name");
		String gradeClassName = jsonObject.getString("gradeClassName");
		JSONArray clazzName = jsonObject.getJSONArray("clazzName");
		JSONArray teacherName = jsonObject.getJSONArray("teacherName");
		//2，搜索学生
		Optional<Student> byId = studentReporsitory.findById(stuId);
		if(!byId.isPresent())
			return 0;

		//lumda表达式
		byId.ifPresent((student)->{
			student.setName(name);
			//2.1，通过名字查询该班级是否存在，如果不存在就自己建一个
			Gradeclass gradeclass=gradeclassReporsitory.findByGradeClassName(gradeClassName);
			if(gradeclass==null){
				gradeclass=new Gradeclass();
				gradeclass.setGradeClassName(gradeClassName);
			}
			student.setGradeClass(gradeclass);
			//2.2，通过名字查询课程是否存在，不存在就自己新建
			for(Object temp:clazzName){
				Clazz clazz=clazzReporsitory.findByName((String)temp);
				if(clazz==null) {
					clazz=new Clazz();
					clazz.setName((String) temp);
				}
				student.getClazzes().add(clazz);
			}
			//2.3，查询老师是否存在，不存在就新建一个
			for(Object temp:teacherName) {
				Teacher teacher = teacherReprository.findByTeacherName((String)temp);
				if(teacher==null){
					teacher=new Teacher();
					teacher.setTeacherName((String)temp);
				}
				student.getTeachers().add(teacher);
			}
			//3，保存学生
			Student save = studentReporsitory.save(student);

		});
		return 1;


	}

	@Override
	public List<Student> findAll() {
		List<Student> all = studentReporsitory.findAll();
		return all;
	}
}
