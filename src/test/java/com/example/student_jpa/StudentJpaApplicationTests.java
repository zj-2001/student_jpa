package com.example.student_jpa;

import com.example.student_jpa.Dao.StudentReporsitory;
import com.example.student_jpa.pojo.Clazz;
import com.example.student_jpa.pojo.Gradeclass;
import com.example.student_jpa.pojo.Student;
import com.example.student_jpa.pojo.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentJpaApplicationTests {

	@Autowired
	StudentReporsitory studentReporsitory;

	@Test
	void contextLoads() {

		Clazz operateSystem= new Clazz();
		operateSystem.setName("操作系统");


		Student xiaoming = new Student();
		xiaoming.setName("小明");
		xiaoming.getClazzes().add(operateSystem);


		Gradeclass softWare=new Gradeclass();
		softWare.setGradeClassName("软件工程1999班");
		xiaoming.setGradeClass(softWare);

		Teacher shixiangning =new Teacher();
		shixiangning.setTeacherName("史湘宁");
		shixiangning.setClazz(operateSystem);
		xiaoming.getTeachers().add(shixiangning);

		studentReporsitory.save(xiaoming);
	}

}
