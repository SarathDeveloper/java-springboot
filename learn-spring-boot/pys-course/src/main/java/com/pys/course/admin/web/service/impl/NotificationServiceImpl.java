package com.pys.course.admin.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.support.util.MessageUtil;
import com.pys.course.admin.web.entity.PaymentCycle;
import com.pys.course.admin.web.entity.StudentCourse;
import com.pys.course.admin.web.entity.StudentPayment;
import com.pys.course.admin.web.repository.PaymentCyleRepository;
import com.pys.course.admin.web.repository.StudentCourseRepository;
import com.pys.course.admin.web.repository.StudentPaymentRepository;
import com.pys.course.admin.web.service.NotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	private final StudentCourseRepository studentCourseRepository;

	private final StudentPaymentRepository studentPaymentRepository;
	
	private final PaymentCyleRepository paymentCycleRepository;

	@Autowired
	public NotificationServiceImpl(StudentPaymentRepository studentPaymentRepository,
			StudentCourseRepository studentCourseRepository, PaymentCyleRepository paymentCycleRepository) {
		// this.courseRepository = courseRepository;
		this.studentPaymentRepository = studentPaymentRepository;
		this.studentCourseRepository = studentCourseRepository;
		this.paymentCycleRepository = paymentCycleRepository;
	}

	@Override
	public void sendRemainderToUnpaidCustomer(Long courseId, Long paymentCycleId) throws NotFoundException {
		// TODO Auto-generated method stub

		Set<StudentCourse> studentCourses = studentCourseRepository.findStudentCourseById(courseId);
		List<StudentPayment> studentPayments = studentPaymentRepository
				.findStudentPaymentByPaymentCycleId(paymentCycleId);

		// studentCourses.stream().filter(studentCourse -> studentPayments.forEach(
		// studentCourse.getStudent()));

		List<StudentCourse> studentsNotPaidFees = null;
		if(studentPayments == null) {
			
			studentsNotPaidFees = new ArrayList<StudentCourse>(studentCourses);
		}else {
		studentsNotPaidFees = studentCourses.stream().filter(student -> {
			studentPayments.forEach(name -> {
				if (name.getStudent().equals(student.getStudent())) {
					student.setStudent(null);
					return;
				}
			});
			return student.getStudent() != null;
		}).collect(Collectors.toList());
		// .forEach(studentsNotPaidFees::add);
		}
		
		
		PaymentCycle paymentCycle = paymentCycleRepository.findOne(paymentCycleId);
		String paymentCycleDate = paymentCycle.getPayCycleDate().toString();
		System.out.println("Payment Date-->"+paymentCycleDate);
		studentsNotPaidFees.forEach(student -> {
			MessageUtil.getJsonObjectToSendPaymentRemainder(student.getStudent().getMobileNo(),
					student.getStudent().getName(), student.getCourse().getCourseName(), paymentCycleDate);
			System.out.println(student.getStudent().getId());
		});
	}

	@Override
	public void sendMessageByStudentId(Long studentId) {
		// TODO Auto-generated method stub

	}

}
