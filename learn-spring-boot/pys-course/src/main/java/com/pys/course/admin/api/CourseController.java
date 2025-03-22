package com.pys.course.admin.api;

import java.util.Locale;
import java.util.Set;

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
import com.pys.course.admin.web.entity.Course;
import com.pys.course.admin.web.entity.User;
import com.pys.course.admin.web.service.CoachService;
import com.pys.course.admin.web.service.CourseService;

/**
 * <b>Attendance API</b><br>
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
@RestController
@RequestMapping(path="/api/course")
public class CourseController {

	private @Autowired CourseService courseService;
	private @Autowired CoachService coachService;
	private @Autowired MessageSource messageSource;

	/**
	 * Create a author
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<String> createCourse(@RequestBody Course course) {
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
		Course newCourse = null;
		try {
			newCourse = courseService.createCourse(course);
		} catch (DuplicateException e) {
			throw new DuplicateException(
					messageSource.getMessage(e.getMessage(), new Object[] { course.getCourseName() }, locale));
		}

		//AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		//AuthorResource resource = assembler.toResource(newAuthor);
		return new ResponseEntity<String>("Course created successfully", HttpStatus.OK);
	}

	/**
	 * Get a author
	 * 
	 */
	@RequestMapping(path = "/{courseId}", method = RequestMethod.GET)
	public ResponseEntity<Course> getCourse(@PathVariable Long courseId) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		Course course = new Course();
		
		  try { 
			  course = courseService.getCourse(courseId);
			  } 
		  catch (NotFoundException
		  e) { throw new NotFoundException(messageSource.getMessage(e.getMessage(), new
		  Object[] { courseId }, locale)); }
		 

		/*
		 * AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		 * AuthorResource resource = assembler.toResource(author);
		 */
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	/**
	 * Get Course By VendorId
	 * 
	 */
	@RequestMapping(path = "/vendor/{vendorId}", method = RequestMethod.GET)
	public ResponseEntity<Set<Course>> getCourseByVendorId(@PathVariable Long vendorId) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		Set<Course> course = null;
		
		  try { 
			  course = courseService.getCourseByVendorId(vendorId);
			  } 
		  catch (NotFoundException
		  e) { throw new NotFoundException(messageSource.getMessage(e.getMessage(), new
		  Object[] { vendorId }, locale)); }
		 

		/*
		 * AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		 * AuthorResource resource = assembler.toResource(author);
		 */
		return new ResponseEntity<Set<Course>>(course, HttpStatus.OK);
	}

	/**
	 * Delete a author
	 * 
	 */
	@RequestMapping(path = "/{courseId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {

		courseService.deleteCourse(courseId);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Assign coach to a Course
	 * 
	 */
	@RequestMapping(path = "/{courseId}/{coachId}", method = RequestMethod.PUT)
	public ResponseEntity<String> assignCoach(@PathVariable Long courseId, @PathVariable Long coachId) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		if (null == courseId || null == coachId) {
			throw new BadParamException(messageSource.getMessage(MessageCode.ASSIGN_COURSE_REQUIRE_COURSE_ID,
					new Object[] { courseId }, locale));
		}

		
		try {
			courseService.assignCoach(courseId, coachId);
		} catch (NotFoundException e) {
			if (e.getMessage().equalsIgnoreCase(MessageCode.COACH_NOT_FOUND)) {
				throw new NotFoundException(
						messageSource.getMessage(e.getMessage(), new Object[] { coachId }, locale));
			}

			if (e.getMessage().equalsIgnoreCase(MessageCode.COURSE_NOT_FOUND)) {
				throw new NotFoundException(
						messageSource.getMessage(e.getMessage(), new Object[] { courseId}, locale));
			}
		}

		/*
		 * AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		 * AuthorResource resource = assembler.toResource(author);
		 */
		return new ResponseEntity<String>("coach Assigned successfully", HttpStatus.OK);
	}

}
