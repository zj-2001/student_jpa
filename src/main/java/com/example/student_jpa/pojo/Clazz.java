package com.example.student_jpa.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@Data//课程表
public class Clazz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clazzId;

	@Column(name ="clazzName",nullable = true,unique = true)
	private String name;

	@OneToMany(mappedBy = "clazz",cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	private Set<Teacher> teacher=new HashSet<>();

	@ManyToMany(mappedBy = "clazzes",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	@Column(name = "stuId")
	private Set<Student> students=new HashSet<>();

	@Override
	public String toString() {
		return "Clazz{" +
			    "clazzId=" + clazzId +
			    ", name='" + name + '\'' +
			    '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Clazz)) return false;

		Clazz clazz = (Clazz) o;

		if (clazzId != null ? !clazzId.equals(clazz.clazzId) : clazz.clazzId != null) return false;
		return name != null ? name.equals(clazz.name) : clazz.name == null;
	}

	@Override
	public int hashCode() {
		int result = clazzId != null ? clazzId.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
