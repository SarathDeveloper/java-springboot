package com.pys.course.admin.web.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;


	@Embeddable
	@Getter
	@Setter
	public class StudentCourseId implements Serializable {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Column(name = "student_id")
	    private Long studentId;

	    @Column(name = "course_id")
	    private Long courseId;

	    // Constructors, getters, and setters
	    
	    public StudentCourseId() {}
	    
	    public StudentCourseId(
	            Long courseId,
	            Long studentId) {
	            this.studentId = studentId;
	            this.courseId = courseId;
	        }
	    
	    @Override
	 		public boolean equals(Object o) {
	 			if (this == o)
	 				return true;
	 			if (o == null || getClass() != o.getClass()) {
	 				return false;
	 			}

	 			StudentCourseId entity = (StudentCourseId) o;

	 			return Objects.equals(studentId, entity.studentId) &&
	 	               Objects.equals(courseId, entity.courseId);
	 		}

	 		@Override
	 		public int hashCode() {
	 			return Objects.hash(studentId,courseId);
	 		}
	    
	}


