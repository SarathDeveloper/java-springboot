package com.pys.course.admin.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.pys.course.admin.support.jpa.CustomJpaRepository;
import com.pys.course.admin.web.entity.PaymentCycle;

public interface PaymentCyleRepository extends CustomJpaRepository<PaymentCycle, Long>  {
	
	
	 @Query(value = "SELECT pc.* "
		 		+ "FROM payment_cycle pc  "
		 		+ "JOIN student_payment sp ON pc.id = sp.paymentCyle_id "
		 		+ "JOIN student s ON sp.student_id = s.id "
		 		+ "WHERE pc.vendor_id = ?1 "
		 		+ "ORDER BY c.name",nativeQuery = true)
	public List<PaymentCycle> findPaymentCyclesByVendorId(Long vendorId);
	 
	 @Query(value = "SELECT pc.* "
		 		+ "FROM payment_cycle pc  "
		 		+ "JOIN student_payment sp ON pc.id = sp.paymentCyle_id "
		 		+ "JOIN student s ON sp.student_id = s.id "
		 		+ "WHERE pc.course_id = ?1 "
		 		+ "ORDER BY c.name",nativeQuery = true)
	public List<PaymentCycle> findPaymentCyclesByCourseId(Long courseId);

}
