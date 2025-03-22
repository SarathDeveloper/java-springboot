package com.pys.course.admin.web.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pys.course.admin.support.constant.MessageCode;
import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.support.util.PaymentUtil;
import com.pys.course.admin.support.util.PaymentUtil.Frequency;
import com.pys.course.admin.web.entity.Course;
import com.pys.course.admin.web.entity.Payment;
import com.pys.course.admin.web.entity.PaymentCycle;
import com.pys.course.admin.web.entity.StudentPayment;
import com.pys.course.admin.web.entity.StudentPaymentId;
import com.pys.course.admin.web.repository.PaymentCyleRepository;
import com.pys.course.admin.web.repository.PaymentRepository;
import com.pys.course.admin.web.repository.StudentPaymentRepository;
import com.pys.course.admin.web.service.CourseService;
import com.pys.course.admin.web.service.PaymentService;
import com.pys.course.admin.web.service.StudentService;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepository;
	private final PaymentCyleRepository paymentCycleRepository;
	private final StudentPaymentRepository studentPaymentRepository;

	private final StudentService studentService;
	private final CourseService courseService;

	@Autowired
	public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentCyleRepository paymentCycleRepository,
			StudentPaymentRepository studentPaymentRepository, StudentService studentService,
			CourseService courseService) {
		this.paymentRepository = paymentRepository;
		this.paymentCycleRepository = paymentCycleRepository;
		this.studentPaymentRepository = studentPaymentRepository;
		this.studentService = studentService;
		this.courseService = courseService;

	}

	@Override
	public void createPaymentCyclesByCourseId(Long courseId) throws NotFoundException,DuplicateException {
		// TODO Auto-generated method stub
		
		List<PaymentCycle> paymentCycles = null;
		
		 paymentCycles =paymentRepository.findPaymentCyclesByCourseId(courseId);
		 
		 if(null != paymentCycles ) {
			 throw new DuplicateException(MessageCode.PAYMENT_CYCLE_EXIST);
		 }

		Course course = courseService.getCourse(courseId);
		paymentCycles = PaymentUtil.generatePaymentCycles(course.getEndDate(),
				Frequency.valueOf(course.getFeeCycle()),course.getOthersInDays());

		Payment payment = new Payment();
		payment.setCourseId(courseId);
		payment.setFrequency(course.getFeeCycle());
		payment.setVendorId(course.getVendorId());
		payment.setEndDate(course.getEndDate());
		// Save the Payment entity and get the saved instance
		
		payment = paymentRepository.save(payment);
		// Associate the generated payment cycles with the saved Payment entity
		for (PaymentCycle cycle : paymentCycles) {
			cycle.setPayment(payment);
		}
		payment.setPaymentCycles(paymentCycles);

		payment = paymentRepository.save(payment);
		// return null;
	}

	@Override
	public Payment createPayment(Payment payment) throws DuplicateException {
		// TODO Auto-generated method stub

		List<PaymentCycle> paymentCycles = PaymentUtil.generatePaymentCycles(payment.getEndDate(),
				Frequency.valueOf(payment.getFrequency()),payment.getOthersInDays());

		// Save the Payment entity and get the saved instance
		payment = paymentRepository.save(payment);
		// Associate the generated payment cycles with the saved Payment entity
		for (PaymentCycle cycle : paymentCycles) {
			cycle.setPayment(payment);
		}
		payment.setPaymentCycles(paymentCycles);

		payment = paymentRepository.save(payment);

		return payment;
	}

	@Override
	public List<Payment> getPaymentsByCourseId(Long courseId) throws DuplicateException {
		// TODO Auto-generated method stub
		return paymentRepository.findPaymentByCourseId(courseId);
	}

	@Override
	public void deletePayment(Long paymentId) throws NotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Payment> getPaymentsByVendorId(Long vendorId) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createStudentPayment(StudentPayment studentPayment, Long courseId) throws DuplicateException {
		// TODO Auto-generated method stub
		// Student student = studentService.getStudent(payment.getStudent().getId());
		// payment.setStudent(student);
		List<Object[]> resultList = paymentRepository.findPaymentByCourseIdAndPaymentId(courseId,
				studentPayment.getPaymentCycle().getId());
		Payment payment = null;
		for (Object[] result : resultList) {
			payment = (Payment) result[0];
			PaymentCycle paymentCycle = (PaymentCycle) result[1];
			// Process the retrieved Payment and PaymentCycle objects

			// studentPayments.add(studentPayment);
			// payment.getPaymentCycles().add(paymentCycle);
			// payment.getPaymentCycles().get(0).setStudentPayments(studentPayments);
			if (paymentCycle.getStudentPayments().isEmpty()) {
				List<StudentPayment> studentPayments = new ArrayList<StudentPayment>();
				paymentCycle.setStudentPayments(studentPayments);
			}
			// paymentCycle.setPayment(payment);
			// studentPayment.setPaymentCycle(paymentCycle);
			studentPayment.setId(new StudentPaymentId(payment.getId(), paymentCycle.getId()));
			paymentCycle.getStudentPayments().add(studentPayment);

			// studentPaymentRepository.save(studentPayment);
			paymentCycleRepository.save(paymentCycle);
		}

		// paymentRepository.save(payment);

	}

	@Override
	public void updateStudentPayment(StudentPayment studentPayment) throws NotFoundException {

		studentPaymentRepository.updateStudentPayment(studentPayment);

	}

	@Override
	public void deleteStudentPayment(Long paymentCycleId) throws NotFoundException {
		// TODO Auto-generated method stub

		studentPaymentRepository.deleteStudentPayment(paymentCycleId);
	}

	@Override
	public void generatePaymentCycles(LocalDate endDate, Frequency frequency) throws NotFoundException {
		// TODO Auto-generated method stub
		List<PaymentCycle> dates = PaymentUtil.generatePaymentCycles(endDate, frequency,null);
	}

	@Override
	public void createPaymentCycle(List<PaymentCycle> paymentCycles,Long courseId) throws DuplicateException {
		// TODO Auto-generated method stub
		Course course = courseService.getCourse(courseId);
		Payment payment = new Payment();
		payment.setCourseId(courseId);
		payment.setFrequency(course.getFeeCycle());
		payment.setVendorId(course.getVendorId());
		payment.setEndDate(course.getEndDate());
		// Save the Payment entity and get the saved instance
		
		payment = paymentRepository.save(payment);
		// Associate the generated payment cycles with the saved Payment entity
		for (PaymentCycle cycle : paymentCycles) {
			cycle.setPayment(payment);
		}
		payment.setPaymentCycles(paymentCycles);

		payment = paymentRepository.save(payment);
	}

	@Override
	public List<PaymentCycle> generatePaymentCycleByCourseId(Long courseId) throws NotFoundException {
		// TODO Auto-generated method stub
		Course course = courseService.getCourse(courseId);
		if(course == null) {
			throw new NotFoundException(MessageCode.COURSE_NOT_FOUND);
		}
		List<PaymentCycle> paymentCycles = PaymentUtil.generatePaymentCycles(course.getEndDate(),
				Frequency.valueOf(course.getFeeCycle()),course.getOthersInDays());
		return paymentCycles;
	}

}
