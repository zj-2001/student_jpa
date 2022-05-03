package com.example.student_jpa.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Table
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stuId;
	//学生姓名
	@Column(name = "studentName")
	private String name;

	//学生所在班级,多对一关系
	@ManyToOne(targetEntity = Gradeclass.class,cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	@JoinColumn(name ="gradeclassId",referencedColumnName = "gradeId")
	private Gradeclass gradeClass;

	//学生上的课程：多对多关系
	@ManyToMany(targetEntity = Clazz.class,cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	@JoinTable(name = "clazz_student",joinColumns = @JoinColumn( name="stuId",nullable = true,referencedColumnName = "stuId"),
					  inverseJoinColumns = @JoinColumn(name = "clazzId",nullable = true,referencedColumnName = "clazzId"))
	private Set<Clazz> clazzes=new HashSet<>();

	//学生的老师，多对多关系
	@ManyToMany(targetEntity = Teacher.class,cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	@JoinTable(name = "student_teacher",joinColumns = @JoinColumn( name="stuId",nullable = true,referencedColumnName = "stuId"),
		    inverseJoinColumns = @JoinColumn(name = "teacherId",nullable = true,referencedColumnName = "teacherId"))
	private Set<Teacher> teachers=new HashSet<>();

	public Student() {
	}

	public Student(String name, Gradeclass gradeClass, Set<Clazz> clazzes, Set<Teacher> teachers) {

		this.name = name;
		this.gradeClass = gradeClass;
		this.clazzes = clazzes;
		this.teachers = teachers;
	}

	@Override
	public String toString() {
		return "Student{" +
			    "stuId=" + stuId +
			    ", name='" + name + '\'' +
			    ", gradeClass=" + gradeClass +
			    ", clazzes=" + clazzes +
			    ", teachers=" + teachers +
			    '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Student)) return false;

		Student student = (Student) o;

		if (stuId != null ? !stuId.equals(student.stuId) : student.stuId != null) return false;
		return name != null ? name.equals(student.name) : student.name == null;
	}

	@Override
	public int hashCode() {
		int result = stuId != null ? stuId.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
