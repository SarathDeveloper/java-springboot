package com.pys.course.admin.api;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.web.entity.Payment;
import com.pys.course.admin.web.entity.PaymentCycle;
import com.pys.course.admin.web.entity.StudentPayment;
import com.pys.course.admin.web.service.PaymentService;

import springfox.documentation.service.ResponseMessage;

@RestController
@RequestMapping(path="/api/payment")
public class PaymentController {
	
	private @Autowired PaymentService payService;
	private @Autowired MessageSource messageSource;
	
	/**
	 * Create a author
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST ,path = "/students/update")
	public ResponseEntity<String> updateStudentPayment(@RequestBody StudentPayment payment) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		// Verify
		/*
		 * if (null == course || !StringUtils.hasText(course.getCourseName())) { throw
		 * new
		 * BadParamException(messageSource.getMessage(MessageCode.COACH_NAME_REQUIRED,
		 * null, locale)); }
		 */

		try {
			payService.updateStudentPayment(payment);
		} catch (NotFoundException e) {
			throw new DuplicateException(
					messageSource.getMessage(e.getMessage(), new Object[] { payment.getId() }, locale));
		}

		//AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		//AuthorResource resource = assembler.toResource(newAuthor);
		return new ResponseEntity<String>("Course created successfully", HttpStatus.OK);
	}
	
	/**
	 * Create a author
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/create")
	public ResponseEntity<String> generatePaymentCycle(@RequestBody Payment payment) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		// Verify
		/*
		 * if (null == course || !StringUtils.hasText(course.getCourseName())) { throw
		 * new
		 * BadParamException(messageSource.getMessage(MessageCode.COACH_NAME_REQUIRED,
		 * null, locale)); }
		 */

		// Create
		Payment newCourse = null;
		try {
			newCourse = payService.createPayment(payment);
		} catch (DuplicateException e) {
			throw new DuplicateException(
					messageSource.getMessage(e.getMessage(), new Object[] { payment.getId() }, locale));
		}

		//AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		//AuthorResource resource = assembler.toResource(newAuthor);
		return new ResponseEntity<String>("Course created successfully", HttpStatus.OK);
	}


	/**
	 * Delete a Student
	 * 
	 */
	@RequestMapping(path = "/course/{courseId}", method = RequestMethod.GET)
	public ResponseEntity<List<Payment>> getStudentsByCourseId(@PathVariable Long courseId) {

		List<Payment> students = payService.getPaymentsByCourseId(courseId);

		return new ResponseEntity<List<Payment>>(students, HttpStatus.OK);
	}
	
	/**
	 * Generate Payment Cycle By Course ID
	 * 
	 */
	@RequestMapping(path = "/cycle/{courseId}", method = RequestMethod.GET)
	public ResponseEntity<List<PaymentCycle>> generatePaymentCyclesByCourseId(@PathVariable Long courseId) {

		Locale locale = LocaleContextHolder.getLocale();
		List<PaymentCycle> payCycles = null;
		try { 
		 payCycles = payService.generatePaymentCycleByCourseId(courseId);
		}
		 catch (DuplicateException e) {
				throw new DuplicateException(
						messageSource.getMessage(e.getMessage(), new Object[] { courseId }, locale));
			}
		 

		return new ResponseEntity<List<PaymentCycle>>(payCycles, HttpStatus.OK);
	}
	
	
	/**
	 * Create Payment Cycle By Course ID
	 * 
	 */
	@RequestMapping(path = "/cycle/{courseId}/create", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> createPaymentCyclesByCourseId(@PathVariable Long courseId) {

		Locale locale = LocaleContextHolder.getLocale();
		try { 
		payService.createPaymentCyclesByCourseId(courseId);
		}
		 catch (DuplicateException e) {
				throw new DuplicateException(
						messageSource.getMessage(e.getMessage(), new Object[] { courseId }, locale));
			}
		 ResponseMessage response = new ResponseMessage(HttpStatus.OK.value(), "Payment Cycle has been created Successfully", null, null, null);

		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}
	
	/**
	 * Create Payment Cycle 
	 * 
	 */
	@RequestMapping(path = "/cycle/{courseId}", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> createPaymentCycle(@RequestBody List<PaymentCycle> paymentCycles,@PathVariable Long courseId) {

		Locale locale = LocaleContextHolder.getLocale();
		try { 
		payService.createPaymentCycle(paymentCycles,courseId);
		}
		 catch (DuplicateException e) {
				throw new DuplicateException(
						messageSource.getMessage(e.getMessage(), new Object[] { courseId }, locale));
			}
		 ResponseMessage response = new ResponseMessage(HttpStatus.OK.value(), "Payment Cycle has been created Successfully", null, null, null);

		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}

	/**
	 * create Student Payment
	 * 
	 */
	@RequestMapping(path = "/students/{courseId}", method = RequestMethod.POST)
	public ResponseEntity<String> addStudentPayment(@RequestBody StudentPayment studentPayment,@PathVariable Long courseId) {

		 payService.createStudentPayment(studentPayment,courseId);

		return new ResponseEntity<String>("Payment created successfully", HttpStatus.OK);
	}


	/**
	 * create Student Payment
	 * 
	 */
	@RequestMapping(path = "/students/update/{paymentCycleId}", method = RequestMethod.POST)
	public ResponseEntity<String> deleteStudentPayment(@RequestBody StudentPayment studentPayment,@PathVariable Long paymentCycleId) {

		 payService.createStudentPayment(studentPayment,paymentCycleId);

		return new ResponseEntity<String>("Payment created successfully", HttpStatus.OK);
	}

}
