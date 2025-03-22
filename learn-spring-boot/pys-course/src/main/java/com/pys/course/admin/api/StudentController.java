package com.pys.course.admin.api;

import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.pys.course.admin.support.constant.MessageCode;
import com.pys.course.admin.support.exception.BadParamException;
import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.web.entity.Student;
import com.pys.course.admin.web.entity.StudentCourse;
import com.pys.course.admin.web.service.StudentService;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {
	
	private @Autowired StudentService studentService;
		private @Autowired MessageSource messageSource;

	/**
	 * Create a author
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addStudent(@RequestBody Student student) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		// Verify

		if (null == student || !StringUtils.hasText(student.getMobileNo())) {
			throw new BadParamException(messageSource.getMessage(MessageCode.COACH_NAME_REQUIRED, null, locale));
		}

		// Create
		Student newStudent = null;
		try {
			newStudent = studentService.addStudent(student);
		} catch (DuplicateException e) {
			throw new DuplicateException(
					messageSource.getMessage(e.getMessage(), new Object[] { newStudent.getMobileNo() }, locale));
		}

		// AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		// AuthorResource resource = assembler.toResource(newAuthor);
		return new ResponseEntity<String>("Coach added successfully", HttpStatus.OK);
	}

	
	/**
	 * Create a author
	 * 
	 */
	@RequestMapping(path = "course/{courseId}" ,method = RequestMethod.POST)
	public ResponseEntity<String> addStudentByCourseId(@RequestBody Student student,@PathVariable Long courseId) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		// Verify

		if (null == student || !StringUtils.hasText(student.getMobileNo())) {
			throw new BadParamException(messageSource.getMessage(MessageCode.COACH_NAME_REQUIRED, null, locale));
		}

		// Create
		Student newStudent = null;
		try {
			newStudent = studentService.addStudentByCourseId(courseId,student);
		} catch (DuplicateException e) {
			throw new DuplicateException(
					messageSource.getMessage(e.getMessage(), new Object[] { newStudent.getMobileNo() }, locale));
		}

		// AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		// AuthorResource resource = assembler.toResource(newAuthor);
		return new ResponseEntity<String>("Student added successfully", HttpStatus.OK);
	}
	/**
	 * Get a author
	 * 
	 */
	@RequestMapping(path = "/{studentId}", method = RequestMethod.GET)
	public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		Student student =null;

		try {
			student = studentService.getStudent(studentId);
		} catch (NotFoundException e) {
			throw new NotFoundException(messageSource.getMessage(e.getMessage(), new Object[] { studentId }, locale));
		}

		/*
		 * AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		 * AuthorResource resource = assembler.toResource(author);
		 */
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	/**
	 * Get a Student by mobile no
	 * 
	 */
	@RequestMapping(path = "/by-mobile/{mobileNo}", method = RequestMethod.GET)
	public ResponseEntity<Student> getStudentByMobileNo(@PathVariable String mobileNo) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();

		Student student =null;

		try {
			student = studentService.getStudentByMobileNo(mobileNo);
		} catch (NotFoundException e) {
			throw new NotFoundException(messageSource.getMessage(e.getMessage(), new Object[] { mobileNo }, locale));
		}

		/*
		 * AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		 * AuthorResource resource = assembler.toResource(author);
		 */
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	
	/**
	 * Delete a Student
	 * 
	 */
	@RequestMapping(path = "course/{courseId}", method = RequestMethod.GET)
	//public MappingJacksonValue getStudentsByCourseId(@PathVariable Long courseId) {
	public ResponseEntity<Set<StudentCourse>> getStudentsByCourseId(@PathVariable Long courseId) {
		
	/*
	 * SimpleBeanPropertyFilter simpleBeanPropertyFilter =
	 * SimpleBeanPropertyFilter.serializeAllExcept("id",
	 * "deleted","student.deleted");
	 * 
	 * FilterProvider filterProvider = new SimpleFilterProvider()
	 * .addFilter("userFilter", simpleBeanPropertyFilter);
	 * 
	 * Set<StudentCourse> students = studentService.getStudentsByCourseId(courseId);
	 * 
	 * MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(students);
	 * mappingJacksonValue.setFilters(filterProvider);
	 * 
	 * return mappingJacksonValue;
	 * 
	 */

		Set<StudentCourse> students = studentService.getStudentsByCourseId(courseId);
		
		return new ResponseEntity<Set<StudentCourse>>(students, HttpStatus.OK);
	}


	/**
	 * Delete a Student
	 * 
	 */
	@RequestMapping(path = "/{studentId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {

		studentService.deleteStudent(studentId);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Assign Student to the course 
	 * 
	 */
	@RequestMapping(path = "/enroll/{studentId}/{courseId}" ,method = RequestMethod.PUT)
	public ResponseEntity<String> enrollStudentToCourse(@PathVariable Long studentId,@PathVariable Long courseId) {
		// Get request locale
		Locale locale = LocaleContextHolder.getLocale();


		try {
			studentService.enrollStudentToCourse(studentId,courseId);
		} catch (NotFoundException e) {
			if (e.getMessage().equalsIgnoreCase(MessageCode.STUDENT_NOT_FOUND)) {
				throw new NotFoundException(
						messageSource.getMessage(e.getMessage(), new Object[] { studentId }, locale));
			}

			if (e.getMessage().equalsIgnoreCase(MessageCode.COURSE_NOT_FOUND)) {
				throw new NotFoundException(
						messageSource.getMessage(e.getMessage(), new Object[] { courseId}, locale));
			}
		}

		// AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		// AuthorResource resource = assembler.toResource(newAuthor);
		return new ResponseEntity<String>("Student assigned to the Course successfully", HttpStatus.OK);
	}
}
