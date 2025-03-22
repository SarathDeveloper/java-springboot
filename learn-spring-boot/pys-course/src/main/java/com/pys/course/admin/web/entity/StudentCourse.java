package com.pys.course.admin.web.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "course_students")
@Getter
@Setter
@JsonIgnoreProperties(value={ "course"}, allowSetters= true)
//@JsonFilter("userFilter")
public class StudentCourse {
	
	 @EmbeddedId
	    private StudentCourseId id;

	    @ManyToOne
	    @MapsId("studentId")
	    @JoinColumn(name = "student_id")
	    private Student student;

	    @ManyToOne
	    @MapsId("courseId")
	    @JoinColumn(name = "course_id")
	
	    private Course course;

	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "enrollment_date")
	    private Date enrollmentDate = new Date();
		@ColumnDefault(value = "'N'")
		private String deleted;
	    
	    public StudentCourse() {}
	    
	    public StudentCourse(Course course, Student student) {
	    	this.student = student;
	    	this.course = course;
	        this.id = new StudentCourseId(course.getId(), student.getId());
	    }
	 
	    
	    @Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			StudentCourse entity = (StudentCourse) o;

			return Objects.equals(student, entity.student) &&
		               Objects.equals(course, entity.course);
		}

		@Override
		public int hashCode() {
			return Objects.hash(course,student);
		}


}
