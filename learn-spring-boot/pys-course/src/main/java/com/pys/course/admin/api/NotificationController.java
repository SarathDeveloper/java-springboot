package com.pys.course.admin.api;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.web.service.NotificationService;

import springfox.documentation.service.ResponseMessage;

@RestController
@RequestMapping(path="/api/notification")
public class NotificationController {

	
	private @Autowired NotificationService notificationService;
	private @Autowired MessageSource messageSource;
	
	/**
	 * Create a author
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/{courseId}/reminder/{paymentCycleId}")
	public ResponseEntity<ResponseMessage> sendRemainderToUnpaidCustomer(@PathVariable Long courseId,@PathVariable Long paymentCycleId) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		try {
			notificationService.sendRemainderToUnpaidCustomer(courseId,paymentCycleId);
		} catch (DuplicateException e) {
			throw new DuplicateException(
					messageSource.getMessage(e.getMessage(), new Object[] { paymentCycleId }, locale));
		}

		 ResponseMessage response = new ResponseMessage(HttpStatus.OK.value(), "Reminder has been sent to students", null, null, null);

			return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}


}
