package com.pys.course.admin.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pys.course.admin.support.jpa.CustomJpaRepository;
import com.pys.course.admin.web.entity.Payment;
import com.pys.course.admin.web.entity.PaymentCycle;

public interface PaymentRepository extends CustomJpaRepository<Payment, Long>  {
	
	
	/* @Query(value = "SELECT pc.* "
		 		+ "FROM payment_cycle pc  "
		 		+ "JOIN student_payment sp ON pc.id = sp.paymentCyle_id "
		 		+ "JOIN student s ON sp.student_id = s.id "
		 		+ "WHERE pc.vendor_id = ?1 "
		 		+ "ORDER BY c.name",nativeQuery = true) */
	 @Query(value = "SELECT pc.* "
		 		+ "FROM payment_cycle pc  "
		 		+ "JOIN course_payments cp ON pc.id = pc.payment_id "
		 		+ "WHERE cp.vendor_id = ?1 "
		 		+ "ORDER BY pc.id",nativeQuery = true)
	public List<PaymentCycle> findPaymentCyclesByVendorId(Long vendorId);
	 
	 @Query(value = "SELECT pc "
		 		+ "FROM PaymentCycle pc "
		 		+ "JOIN pc.payment cp "
		 		+ "WHERE cp.courseId = ?1 "
		 		+ "ORDER BY pc.id ",nativeQuery = false)
	public List<PaymentCycle> findPaymentCyclesByCourseId(Long courseId);
	 
	 @Query(value = "SELECT p "
		 		+ "FROM Payment p  "
		 		+ "WHERE p.courseId = ?1 "
		 		+ "ORDER BY p.endDate",nativeQuery = false)
	public List<Payment> findPaymentByCourseId(Long courseId);
	 
	 @Query(value = "SELECT p,pc "
		 		+ "FROM Payment p "
		 		+ "JOIN p.paymentCycles pc "
		 		+ "WHERE  p.courseId = :courseId AND pc.id = :paymentCycleId "
		 		+ "ORDER BY p.endDate",nativeQuery = false)
	public List<Object[]> findPaymentByCourseIdAndPaymentId(@Param(value = "courseId") Long courseId,@Param(value = "paymentCycleId") Long paymentCycleId);
	 
	 

}
