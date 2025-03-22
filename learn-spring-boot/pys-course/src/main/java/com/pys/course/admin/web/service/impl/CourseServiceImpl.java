package com.pys.course.admin.web.service.impl;

import java.util.ArrayList;
import java.util.EnumSet;
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
import com.pys.course.admin.web.entity.Days;
import com.pys.course.admin.web.entity.converter.DaysConverter;
import com.pys.course.admin.web.repository.CoachRepository;
import com.pys.course.admin.web.repository.CourseRepository;
import com.pys.course.admin.web.service.CourseService;

/**
 * Book service implementation
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;
	private final CoachRepository coachRepository;

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository, CoachRepository coachRepository) {
		this.courseRepository = courseRepository;
		this.coachRepository = coachRepository;
	}

	@Override
	public Course getCourse(Long courseId) throws NotFoundException {
		Assert.notNull(courseId, "Course ID must not be null");

		Course course = courseRepository.findOne(courseId);
		List<Days> days = DaysConverter.convertDayMaskToEnums(course.getDays());
		String classDays =  DaysConverter.getClassDaysFromEnum(days);
		course.setClassDays(classDays);
		
		
		return course;
	}

	@Override
	@Transactional
	public Course createCourse(Course course) throws DuplicateException {
		Assert.hasText(course.getCourseName(), "Course name must not be empty");

		// Course newCourse = courseRepository.findOne(course.getId());

		if (course.getClassDays().equals("ALL")) {

			course.setDays(Days.SUNDAY, Days.MONDAY, Days.TUESDAY, Days.WEDNESDAY, Days.THURSDAY, Days.FRIDAY,
					Days.SATURDAY);
		} else if (course.getClassDays().equals("WEEKDAYS")) {
			course.setDays(Days.MONDAY, Days.TUESDAY, Days.WEDNESDAY, Days.THURSDAY, Days.FRIDAY);
		} else if (course.getClassDays().equals("WEEKEND")) {
			course.setDays(Days.SUNDAY, Days.SATURDAY);
		}else {
			Set<Days> weekdays = EnumSet.noneOf(Days.class);
			String[] dayNames = course.getClassDays().split(",");
			for (String dayName : dayNames) {
	            dayName = dayName.trim(); 
	           Days day =  Days.valueOf(dayName);
	           weekdays.add(day);
	           course.setDays(weekdays.toArray(new Days[weekdays.size()]));
			}
		}
		
		return courseRepository.save(course);
	}

	@Override
	@Transactional
	public void updateCourse(Course course) throws NotFoundException {
		Assert.hasText(course.getCourseName(), "Course name must not be empty");

		// Course newCourse = courseRepository.findOne(course.getId());

		courseRepository.save(course);
	}

	@Override
	@Transactional
	public void deleteCourse(Long bookId) throws NotFoundException {
		Assert.notNull(bookId, "Book ID must not be null");

		Course course = courseRepository.findOne(bookId);
		if (null == course) {
			throw new NotFoundException(MessageCode.COACH_NOT_FOUND);
		}

		// bookAuthorRepository.deleteByBook(book);
		courseRepository.delete(course);
	}

	@Override
	@Transactional
	public void assignCoach(Long courseId, Long coachId) throws NotFoundException {
		Assert.notNull(coachId, "Coach ID must not be null");
		Assert.notNull(courseId, "Course ID must not be null");

		Coach coach = coachRepository.findOne(coachId);
		if (null == coach) {
			throw new NotFoundException(MessageCode.COACH_NOT_FOUND);
		}

		Course course = courseRepository.findOne(courseId);
		if (null == course) {
			throw new NotFoundException(MessageCode.COURSE_NOT_FOUND);
		}

		/*
		 * Coach coach1 = courseRepository.findCoachesById(coach, course.getId()); if
		 * (null == coach1) {
		 */
		if (null == course.getCoaches() || course.getCoaches().isEmpty()) {
			List<Coach> newCoach = new ArrayList<Coach>();
			newCoach.add(coach);
			course.setCoaches(newCoach);
		} else {
			course.getCoaches().add(coach);
		}
		courseRepository.save(course);
		/*
		 * }else { throw new NotFoundException(MessageCode.COACH_DUPLICATE); }
		 */

	}

	@Override
	public Set<Course> getCourseByVendorId(Long vendorId) throws NotFoundException {

		Set<Course> courses = courseRepository.findByVendorId(vendorId);
		if (null == courses) {
			throw new NotFoundException(MessageCode.COURSE_NOT_FOUND);
		}
		// TODO Auto-generated method stub
		return courses;
	}
	
	
}
