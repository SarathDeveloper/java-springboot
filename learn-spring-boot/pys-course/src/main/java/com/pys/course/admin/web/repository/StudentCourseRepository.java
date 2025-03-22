package com.pys.course.admin.web.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pys.course.admin.web.entity.StudentCourse;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

	//public Student findStudentByVendorId(Long vendorId);
    @Query(value = "select sc "
    		+ "from StudentCourse sc "
    		+ "where sc.course.id=?1", nativeQuery = false)
	public Set<StudentCourse> findStudentCourseById(Long courseId);

	
}
