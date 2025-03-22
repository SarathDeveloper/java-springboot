package com.pys.course.admin.web.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pys.course.admin.support.jpa.CustomJpaRepository;
import com.pys.course.admin.web.entity.Coach;
import com.pys.course.admin.web.entity.Course;

/**
 * <b>Book Repository</b><br>
 * You can use NamedQuery or Query annotation here.<br>
 * 
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
public interface CourseRepository extends CustomJpaRepository<Course, Long> {

	public Course findByCourseName(String courseName);

	 @Query(value = "SELECT c.* "
		 		+ "FROM course c  "
		 		+ "WHERE c.vendor_id = ?1 "
		 		+ "ORDER BY c.course_name",nativeQuery = true)
	public Set<Course> findByVendorId(Long vendorId);
	
	public Coach findCoachesById(Coach coachId, Long id);
	
	/*
	 * @Query("UPDATE Course SET ") public void updateCourse(@Param(value =
	 * "course") Course course);
	 */
}
