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

import com.pys.course.admin.support.constant.MessageCode;
import com.pys.course.admin.support.exception.BadParamException;
import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.web.entity.Attendance;
import com.pys.course.admin.web.service.AttendanceService;

/**
 * <b>Attendance API</b><br>
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
@RestController
@RequestMapping("/api/attendance")
public class AttendenceController {

	private @Autowired MessageSource messageSource;
	private @Autowired AttendanceService attendanceService;

	/**
	 * Create a book
	 * 
	 */
	
	  @RequestMapping(method = RequestMethod.POST) public
	  ResponseEntity<String> markStudentsAttendance(@RequestBody List<Attendance> attendance) { 
		  
		   Locale locale = LocaleContextHolder.getLocale();
	  
	  // Verify 
		  
	if (null == attendance) {
	  throw new BadParamException(messageSource.getMessage(MessageCode.COACH_NAME_REQUIRED,
	  null, locale)); }

	 
	try { 
		if(attendance.size() > 1) {
		attendanceService.markStudentsAttendance(attendance);
	}else {
		attendanceService.markStudentAttendance(attendance.get(0));	
	}
	}
	catch(DuplicateException e)
	{
		throw new DuplicateException(
				messageSource.getMessage(e.getMessage(), new Object[] {  }, locale));
	}

	return new ResponseEntity<String>("Attendance has been saved successfully",HttpStatus.OK);
}

	/**
	 * Get a book
	 * 
	 */
	
	  @RequestMapping(path = "/{courseId}", method = RequestMethod.GET) public
	  ResponseEntity<List<Attendance>> getBook(@PathVariable Long courseId) { 
		  
		  Locale locale = LocaleContextHolder.getLocale();
	  
		  List<Attendance> attendanceList = null; 
	  try { 
		  attendanceList = attendanceService.getAttendanceByCourseId(courseId); 
		  } 
	  catch (NotFoundException e) { 
		  throw new NotFoundException(messageSource.getMessage(e.getMessage(), new Object[] { }, locale)); }
	  
	  return new ResponseEntity<List<Attendance>>(attendanceList, HttpStatus.OK); }
	  
	 /**
		 * Delete a book
		 * 
		 */
	/*
	 * @RequestMapping(path = "/{bookId}", method = RequestMethod.DELETE) public
	 * ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
	 * 
	 * courseService.deleteBook(bookId);
	 * 
	 * return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); }
	 * 
	 *//**
		 * Assign book a author
		 * 
		 */
	/*
	 * @RequestMapping(path = "/{bookId}/authors", method = RequestMethod.PUT)
	 * public ResponseEntity<BookResource> assignAuthor(@PathVariable Long
	 * bookId, @RequestBody Attendance author) { // Get request locale Locale locale
	 * = LocaleContextHolder.getLocale();
	 * 
	 * if (null == author || null == author.getId()) { throw new
	 * BadParamException(messageSource.getMessage(MessageCode.
	 * ASSIGN_AUTHOR_REQUIRE_AUTHOR_ID, new Object[] { bookId }, locale)); }
	 * 
	 * Book book = null; try { book = courseService.assignAuthor(bookId,
	 * author.getId()); } catch (NotFoundException e) { if
	 * (e.getMessage().equalsIgnoreCase(MessageCode.BOOK_NOT_FOUND)) { throw new
	 * NotFoundException(messageSource.getMessage(e.getMessage(), new Object[] {
	 * bookId }, locale)); }
	 * 
	 * if (e.getMessage().equalsIgnoreCase(MessageCode.AUTHOR_NOT_FOUND)) { throw
	 * new NotFoundException( messageSource.getMessage(e.getMessage(), new Object[]
	 * { author.getId() }, locale)); } }
	 * 
	 * BookResourceAssembler assembler = new BookResourceAssembler(); BookResource
	 * resource = assembler.toResource(book); return new
	 * ResponseEntity<BookResource>(resource, HttpStatus.OK); }
	 */}
