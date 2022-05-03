package com.example.student_jpa.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@Data//班级表
public class Gradeclass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gradeId;

	@Column(name = "gradeClassName",unique = true)
	private String gradeClassName;

	@OneToMany(mappedBy = "gradeClass",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	private Set<Student> students=new HashSet<>();

	@Override
	public String toString() {
		return "Gradeclass{" +
			    "gradeId=" + gradeId +
			    ", gradeClassName='" + gradeClassName +
			    '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Gradeclass)) return false;

		Gradeclass that = (Gradeclass) o;

		if (gradeId != null ? !gradeId.equals(that.gradeId) : that.gradeId != null) return false;
		return gradeClassName != null ? gradeClassName.equals(that.gradeClassName) : that.gradeClassName == null;
	}

	@Override
	public int hashCode() {
		int result = gradeId != null ? gradeId.hashCode() : 0;
		result = 31 * result + (gradeClassName != null ? gradeClassName.hashCode() : 0);
		return result;
	}
}
