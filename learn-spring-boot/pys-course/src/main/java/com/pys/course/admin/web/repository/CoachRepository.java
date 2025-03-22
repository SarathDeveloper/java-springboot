package com.pys.course.admin.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pys.course.admin.support.jpa.CustomJpaRepository;
import com.pys.course.admin.web.entity.Coach;

/**
 * <b>BookAuthor Repository</b><br>
 * You can use NamedQuery or Query annotation here.<br>
 * 
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
public interface CoachRepository extends CustomJpaRepository<Coach, Long> {

	//public Coach findByCoursesAndId(Course courses, Long id);

	//public void deleteByCoach(Coach author);

//	public void deleteByCourse(Course book);
	/*
	 * @Query(value =
	 * "select coach1_.id as id, coach1_.email as email, coach1_.mobile_no as mobileNo, coach1_.name as name, coach1_.specialization as specialization "
	 * +
	 * "from course_coaches coaches0_  inner join coach coach1_ on coaches0_.coaches_id=coach1_.id "
	 * + " where coaches0_.course_id=?1", nativeQuery = true)
	 */
	 @Query(value = "SELECT * FROM coach c WHERE c.id IN (SELECT coaches_id FROM course_coaches WHERE course_id = :courseId) ORDER BY c.name",nativeQuery = true)
	public List<Coach> getCoachByCourseId(@Param("courseId")Long courseId);
	
	 @Query(value = "SELECT c.* "
	 		+ "FROM coach c  "
	 		+ "JOIN course_coaches cc ON c.id = cc.coaches_id "
	 		+ "JOIN course co ON cc.course_id = co.id "
	 		+ "WHERE co.vendor_id = ?1 "
	 		+ "ORDER BY c.name",nativeQuery = true)
	 public List<Coach> getCoachByVendorId(Long vendorId);
	 
	 @Query(value = "SELECT c.* FROM coach c WHERE c.mobile_no = ?1",nativeQuery = true)
		public Coach findCoachByMobileNo(String mobileNo);
}
