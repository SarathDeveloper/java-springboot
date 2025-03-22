package com.pys.course.admin.web.service;

import java.awt.print.Book;
import java.util.Set;

import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.web.entity.Course;
import com.pys.course.admin.web.service.impl.CourseServiceImpl;

/**
 * Book service Implementation refer to {@link CourseServiceImpl}
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
public interface CourseService {

	/**
	 * Get a book by its ID
	 * 
	 * @param bookId
	 *            long required
	 * 
	 */
	public Course getCourse(Long courseId) throws NotFoundException;
	
	/**
	 * Get a book by its ID
	 * 
	 * @param bookId
	 *            long required
	 * 
	 */
	public Set<Course> getCourseByVendorId(Long vendorId) throws NotFoundException;

	/**
	 * Create a book by its name
	 * 
	 * @param bookName
	 *            String required
	 * 
	 */
	public Course createCourse(Course course) throws DuplicateException;

	/**
	 * Delete a book by its ID
	 * 
	 * @param bookId
	 *            long required
	 * 
	 */
	public void deleteCourse(Long courseId) throws NotFoundException;

	/**
	 * Assign book a author
	 * 
	 * 
	 * @param bookId
	 *            long required
	 * @param authorId
	 *            long required
	 * 
	 * 
	 */
	public void assignCoach(Long courseId, Long coachId) throws NotFoundException;
	
	public void updateCourse(Course course) throws NotFoundException;
}
