package com.pys.course.admin.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Embeddable
@Data
public class CourseCoach implements Serializable {
	
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(name = "coach_id")
    private Long coachId;

    
    @Column(name = "course_id")
    private Long courseId;

}
