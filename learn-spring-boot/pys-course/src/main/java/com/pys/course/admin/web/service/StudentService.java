package com.pys.course.admin.web.service;

import java.util.Set;

import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.web.entity.Student;
import com.pys.course.admin.web.entity.StudentCourse;

public interface StudentService {
	
	/**
	 * Get a author by its ID
	 * 
	 * @param authorId
	 *            long required
	 * 
	 */
	public Student getStudent(Long studentId) throws NotFoundException;

	/**
	 * Create a author by its name
	 * 
	 * @param authorName
	 *            String required
	 * 
	 */
	public Student addStudent(Student student) throws DuplicateException;
	/**
	 * Create a author by its name
	 * 
	 * @param authorName
	 *            String required
	 * 
	 */
	public Student addStudentByCourseId(Long courseId, Student student) throws DuplicateException;

	/**
	 * Delete a author by its ID
	 * 
	 * @param id
	 *            long required
	 * 
	 */
	public void deleteStudent(Long studentId) throws NotFoundException;
	
	/**
	 * Create a author by its name
	 * 
	 * @param authorName
	 *            String required
	 * @return 
	 * 
	 */
	public Set<StudentCourse> getStudentsByCourseId(Long courseId) throws NotFoundException;
	
	
	public Student getStudentByMobileNo(String mobileNo) throws NotFoundException;
	
	public void enrollStudentToCourse(Long StudentId,Long courseId) throws NotFoundException;



}
