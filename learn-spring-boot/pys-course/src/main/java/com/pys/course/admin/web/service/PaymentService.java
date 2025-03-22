package com.pys.course.admin.web.service;

import java.time.LocalDate;
import java.util.List;

import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.support.util.PaymentUtil.Frequency;
import com.pys.course.admin.web.entity.Payment;
import com.pys.course.admin.web.entity.PaymentCycle;
import com.pys.course.admin.web.entity.StudentPayment;

public interface PaymentService {
	
	/**
	 * Get a author by its ID
	 * 
	 * @param authorId
	 *            long required
	 * 
	 */
	public void generatePaymentCycles(LocalDate endDate, Frequency frequency) throws NotFoundException;

	/**
	 * Create addPayment
	 * 
	 * @param payment
	 *            CoursePayment required
	 * 
	 */
	public Payment createPayment(Payment payment) throws DuplicateException;
	/**
	 * Create a author by its name
	 * 
	 * @param authorName
	 *            String required
	 * 
	 */
	public List<Payment> getPaymentsByCourseId(Long courseId) throws NotFoundException;

	/**
	 * Delete a author by its ID
	 * 
	 * @param id
	 *            long required
	 * 
	 */
	public void deletePayment(Long paymentId) throws NotFoundException;
	
	/**
	 * Create a author by its name
	 * 
	 * @param authorName
	 *            String required
	 * @return 
	 * 
	 */
	public List<Payment> getPaymentsByVendorId(Long vendorId) throws NotFoundException;
	/**
	 * Create addPayment
	 * 
	 * @param payment
	 *            CoursePayment required
	 * 
	 */
	public void createStudentPayment(StudentPayment payment,Long courseId) throws DuplicateException;
	
	
	public void updateStudentPayment(StudentPayment studentPayment) throws NotFoundException ;
	
	public void deleteStudentPayment(Long paymentId) throws NotFoundException;
	
	public List<PaymentCycle> generatePaymentCycleByCourseId(Long courseId) throws NotFoundException;
	
	public void createPaymentCyclesByCourseId(Long courseId) throws NotFoundException,DuplicateException;
	
	public void createPaymentCycle(List<PaymentCycle> paymentCycles,Long courseId) throws DuplicateException;


}
