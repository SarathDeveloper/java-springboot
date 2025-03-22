package com.pys.course.admin.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pys.course.admin.support.jpa.CustomJpaRepository;
import com.pys.course.admin.web.entity.StudentPayment;

public interface StudentPaymentRepository  extends CustomJpaRepository<StudentPayment, Long>{


	/*
	 * @Query(value = "SELECT pc.* " + "FROM payment_cycle pc  " +
	 * "JOIN student_payment sp ON pc.id = sp.paymentCyle_id " +
	 * "JOIN student s ON sp.student_id = s.id " + "WHERE pc.vendor_id = ?1 " +
	 * "ORDER BY c.name",nativeQuery = true) public List<StudentPayment>
	 * findStudentPaymentsByVendorId(Long vendorId);
	 * 
	 * @Query(value = "SELECT pc.* " + "FROM payment_cycle pc  " +
	 * "JOIN student_payment sp ON pc.id = sp.paymentCyle_id " +
	 * "JOIN student s ON sp.student_id = s.id " + "WHERE pc.course_id = ?1 " +
	 * "ORDER BY c.name",nativeQuery = true) public List<StudentPayment>
	 * findStudentPaymentsByCourseId(Long vendorId);
	 */
	@Modifying
	@Query(value = "UPDATE StudentPayment SET amount=:#{#studentPayment.amount},"
			+ "paymentStatus=:#{#studentPayment.paymentStatus},paymentMode=:#{#studentPayment.paymentMode} "
			+ "WHERE paymentCycle.id=:#{#studentPayment.paymentCycle.id} AND student.id = :#{#studentPayment.student.id}")
	public void updateStudentPayment(@Param("studentPayment") StudentPayment studentPayment);

	
	@Modifying
	@Query(value="UPDATE student_payments SET deleted='Y' where payment_cycle_id=?1", nativeQuery = true)
	public void deleteStudentPayment(Long paymentCycleId);
	
	 @Query(value = "select sc "
	    		+ "from StudentPayment sc "
	    		+ "where sc.paymentCycle.id=?1", nativeQuery = false)
	public List<StudentPayment> findStudentPaymentByPaymentCycleId(Long paymentCycleId);
}
