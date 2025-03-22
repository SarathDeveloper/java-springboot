package com.pys.course.admin.web.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import com.pys.course.admin.support.jpa.CustomJpaRepository;
import com.pys.course.admin.web.entity.StudentCourse;
import com.pys.course.admin.web.entity.Coach;
import com.pys.course.admin.web.entity.Student;

public interface StudentRepository extends CustomJpaRepository<Student, Long> {

	 @Query(value = "SELECT c.* "
		 		+ "FROM student s  "
		 		+ "JOIN course_students cs ON s.id = cs.student_id "
		 		+ "JOIN course co ON cs.course_id = co.id "
		 		+ "WHERE co.vendor_id = ?1 "
		 		+ "ORDER BY c.name",nativeQuery = true)
	public Set<Student> findStudentByVendorId(Long vendorId);

	//public Set<Student> findStudentsByStudentCourses(StudentCourse studentCourses);
	 @Query(value = "SELECT s.* FROM student s WHERE s.mobile_no = ?1",nativeQuery = true)
		public Student findStudentByMobileNo(String mobileNo);
}
 


