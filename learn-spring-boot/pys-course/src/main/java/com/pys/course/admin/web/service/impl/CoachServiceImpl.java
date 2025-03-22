package com.pys.course.admin.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.pys.course.admin.support.constant.MessageCode;
import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.web.entity.Coach;
import com.pys.course.admin.web.entity.Course;
import com.pys.course.admin.web.repository.AttendanceRepository;
import com.pys.course.admin.web.repository.CoachRepository;
import com.pys.course.admin.web.repository.CourseRepository;
import com.pys.course.admin.web.service.CoachService;

/**
 * Attendance service implementation
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */

@Service
@Transactional(readOnly = true)
public class CoachServiceImpl implements CoachService {
	private @Autowired AttendanceRepository attendanceRepository;
	private @Autowired CourseRepository courseRepository;
	private @Autowired CoachRepository coachRepository;

	@Override
	public Coach getCoach(Long coachId) throws NotFoundException {
		Assert.notNull(coachId, "Coach ID must not be null");

		Coach coach = coachRepository.findOne(coachId);
		if (null == coach) {
			throw new NotFoundException(MessageCode.COACH_NOT_FOUND);
		}

		return coach;
	}

	@Override
	@Transactional
	public Coach createCoach(Coach coach) throws DuplicateException {

		coachRepository.save(coach);
		
		
		return coach;
	}

	@Override
	@Transactional
	public void deleteCoach(Long coachId) throws NotFoundException {
		Assert.notNull(coachId, "Coach ID must not be null");

		Coach coach = coachRepository.findOne(coachId);
		if (null == coachId) {
			throw new NotFoundException(MessageCode.COACH_NOT_FOUND);
		}

		//coachRepository.deleteByCourse(author);
		coachRepository.delete(coach);
	}

	@Override
	public Coach addCoachByCourseId(Coach coach) throws DuplicateException {
		// TODO Auto-generated method stub
		Course course = courseRepository.findOne(coach.getCourses().get(0).getId());
		if (null == course) {
			throw new NotFoundException(MessageCode.COURSE_NOT_FOUND);
		}
		course.getCoaches().add(coach);
		courseRepository.save(course);
		
		
		return coach;
	}

	@Override
	public List<Coach> getCoachByCourseId(Long courseId) throws NotFoundException {
		// TODO Auto-generated method stub
		return coachRepository.getCoachByCourseId(courseId);
	}

	@Override
	public List<Coach> getCoachByVendorId(Long vendorId) throws NotFoundException {
		// TODO Auto-generated method stub
		return coachRepository.getCoachByVendorId(vendorId);
	}

	@Override
	public Coach getCoachbyMobileNo(String mobileNo) throws NotFoundException {
		// TODO Auto-generated method stub
		Coach coach =coachRepository.findCoachByMobileNo(mobileNo);
		if (null == coach) {
			throw new NotFoundException(MessageCode.COACH_NOT_FOUND);
		}
		return coach;
		
	}


}
