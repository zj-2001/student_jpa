package com.example.student_jpa.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teacherId;

	@Column(name = "teacherName",unique = true)
	private String teacherName;

	//假设一名老师只教一门课，一门课能被多名老师教
	@ManyToOne(targetEntity = Clazz.class,cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name = "clazzId",referencedColumnName = "clazzId")
	private Clazz clazz;

	@ManyToMany(mappedBy = "teachers",cascade = {CascadeType.ALL})
	private Set<Student> students=new HashSet<>();

	@Override
	public String toString() {
		return "Teacher{" +
			    "teacherId=" + teacherId +
			    ", teacherName='" + teacherName + '\'' +
			    '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Teacher)) return false;

		Teacher teacher = (Teacher) o;

		if (teacherId != null ? !teacherId.equals(teacher.teacherId) : teacher.teacherId != null) return false;
		return teacherName != null ? teacherName.equals(teacher.teacherName) : teacher.teacherName == null;
	}

	@Override
	public int hashCode() {
		int result = teacherId != null ? teacherId.hashCode() : 0;
		result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
		return result;
	}
}
