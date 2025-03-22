package com.pys.course.admin.api;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pys.course.admin.support.constant.MessageCode;
import com.pys.course.admin.support.exception.BadParamException;
import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.web.entity.Coach;
import com.pys.course.admin.web.service.CoachService;
import com.pys.course.admin.web.service.CourseService;

@RestController
@RequestMapping(path = "/api/coach")
public class CoachController {

	private @Autowired CourseService courseService;
	private @Autowired CoachService coachService;
	private @Autowired MessageSource messageSource;

	/**
	 * Create a author
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addCoach(@RequestBody Coach coach) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		// Verify

		if (null == coach || !StringUtils.hasText(coach.getMobileNo())) {
			throw new BadParamException(messageSource.getMessage(MessageCode.COACH_NAME_REQUIRED, null, locale));
		}

		// Create
		Coach newCoach = null;
		try {
			if(coach.getCourses() != null && !coach.getCourses().isEmpty()) {
				newCoach = coachService.addCoachByCourseId(coach);
			}else {
			newCoach = coachService.createCoach(coach);
			}
		} catch (DuplicateException e) {
			throw new DuplicateException(
					messageSource.getMessage(e.getMessage(), new Object[] { newCoach.getMobileNo() }, locale));
		}

		// AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		// AuthorResource resource = assembler.toResource(newAuthor);
		return new ResponseEntity<String>("Coach added successfully", HttpStatus.OK);
	}
	
	/**
	 * Create a author
	 * 
	 */
	@RequestMapping(path = "/{courseId}",method = RequestMethod.POST)
	public ResponseEntity<String> addCoachByCourseId(@RequestBody Coach coach, @PathVariable Long courseId) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		// Verify

		if (null == coach || !StringUtils.hasText(coach.getMobileNo())) {
			throw new BadParamException(messageSource.getMessage(MessageCode.COACH_NAME_REQUIRED, null, locale));
		}

		// Create
		Coach newCoach = null;
		try {
			newCoach = coachService.addCoachByCourseId(coach);
		} catch (DuplicateException e) {
			throw new DuplicateException(
					messageSource.getMessage(e.getMessage(), new Object[] { newCoach.getMobileNo() }, locale));
		}

		// AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		// AuthorResource resource = assembler.toResource(newAuthor);
		return new ResponseEntity<String>("Coach added successfully", HttpStatus.OK);
	}


	/**
	 * Get a author
	 * 
	 */
	@RequestMapping(path = "/{coachId}", method = RequestMethod.GET)
	public ResponseEntity<Coach> getCoach(@PathVariable Long coachId) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		Coach coach =null;

		try {
			coach = coachService.getCoach(coachId);
		} catch (NotFoundException e) {
			throw new NotFoundException(messageSource.getMessage(e.getMessage(), new Object[] { coachId }, locale));
		}

		/*
		 * AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		 * AuthorResource resource = assembler.toResource(author);
		 */
		return new ResponseEntity<Coach>(coach, HttpStatus.OK);
	}
	
	/**
	 * Get a author
	 * 
	 */
	@RequestMapping(path = "/course/{coachId}", method = RequestMethod.GET)
	public ResponseEntity<List<Coach>> getCoachByCourseId(@PathVariable Long coachId) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		List<Coach> coach =null;

		try {
			coach = coachService.getCoachByCourseId(coachId);
		} catch (NotFoundException e) {
			throw new NotFoundException(messageSource.getMessage(e.getMessage(), new Object[] { coachId }, locale));
		}

		/*
		 * AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		 * AuthorResource resource = assembler.toResource(author);
		 */
		return new ResponseEntity<List<Coach>>(coach, HttpStatus.OK);
	}
	
	/**
	 * Get a author
	 * 
	 */
	@RequestMapping(path = "/mobile/{mobileNo}", method = RequestMethod.GET)
	public ResponseEntity<Coach> getCoachByMobileNo(@PathVariable String mobileNo) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		Coach coach =null;

		try {
			coach = coachService.getCoachbyMobileNo(mobileNo);
		} catch (NotFoundException e) {
			throw new NotFoundException(messageSource.getMessage(e.getMessage(), new Object[] { mobileNo }, locale));
		}

		/*
		 * AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		 * AuthorResource resource = assembler.toResource(author);
		 */
		return new ResponseEntity<Coach>(coach, HttpStatus.OK);
	}
	
	/**
	 * Get a author
	 * 
	 */
	@RequestMapping(path = "/vendor/{vendorId}", method = RequestMethod.GET)
	public ResponseEntity<List<Coach>> getCoachbyVendorId(@PathVariable Long vendorId) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		List<Coach> coach =null;

		try {
			coach = coachService.getCoachByVendorId(vendorId);
		} catch (NotFoundException e) {
			throw new NotFoundException(messageSource.getMessage(e.getMessage(), new Object[] { vendorId }, locale));
		}

		/*
		 * AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		 * AuthorResource resource = assembler.toResource(author);
		 */
		return new ResponseEntity<List<Coach>>(coach, HttpStatus.OK);
	}

	/**
	 * Delete a author
	 * 
	 */
	@RequestMapping(path = "/{coachId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCoach(@PathVariable Long coachId) {

		coachService.deleteCoach(coachId);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
