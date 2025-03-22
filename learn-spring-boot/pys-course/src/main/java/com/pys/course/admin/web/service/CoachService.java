package com.pys.course.admin.web.service;

import java.util.List;

import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.web.entity.Coach;
import com.pys.course.admin.web.service.impl.CoachServiceImpl;

/**
 * Attendance service Implementation refer to {@link CoachServiceImpl}
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
public interface CoachService {

	/**
	 * Get a author by its ID
	 * 
	 * @param authorId
	 *            long required
	 * 
	 */
	public Coach getCoach(Long coachId) throws NotFoundException;

	/**
	 * Create a author by its name
	 * 
	 * @param authorName
	 *            String required
	 * 
	 */
	public Coach createCoach(Coach coach) throws DuplicateException;
	/**
	 * Create a author by its name
	 * 
	 * @param authorName
	 *            String required
	 * 
	 */
	public Coach addCoachByCourseId(Coach coach) throws DuplicateException;


	/**
	 * Delete a author by its ID
	 * 
	 * @param id
	 *            long required
	 * 
	 */
	public void deleteCoach(Long coachId) throws NotFoundException;

	/**
	 * Assign author a book
	 * 
	 * @param authorId
	 *            long required
	 * @param bookId
	 *            long required
	 * 
	 * 
	 */
	/**
	 * Create a author by its name
	 * 
	 * @param authorName
	 *            String required
	 * 
	 */
	public List<Coach> getCoachByCourseId(Long coachId) throws NotFoundException;

	/**
	 * Create a author by its name
	 * 
	 * @param authorName
	 *            String required
	 * 
	 */
	public List<Coach> getCoachByVendorId(Long coachId) throws NotFoundException;


	public Coach getCoachbyMobileNo(String mobileNo) throws NotFoundException;
}
