package com.pys.course.admin.web.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.web.entity.Attendance;
import com.pys.course.admin.web.repository.AttendanceRepository;
import com.pys.course.admin.web.service.AttendanceService;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {
	
	private final AttendanceRepository attendanceRepository;
	
	@Autowired
	public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
		this.attendanceRepository = attendanceRepository;
	//	this.coachRepository = coachRepository;
	}


	@Override
	public List<Attendance> getAttendanceByCourseId(Long courseId) throws NotFoundException {
		// TODO Auto-generated method stub
		return attendanceRepository.findAttendanceByCourseId(courseId);
	}

	@Override
	public List<Attendance> getAttendanceByStudentIdAndCourseId(Long studentId, Long courseId)
			throws NotFoundException {
		// TODO Auto-generated method stub
		return attendanceRepository.findtAttendanceByStudentIdAndCourseId(studentId, courseId);
	}

	@Override
	public List<Attendance> getAttendanceByAttendanceDateAndCourseId(LocalDate attendanceDate, Long courseId)
			throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void markStudentsAttendance(List<Attendance> attendanceList) throws DuplicateException {
		// TODO Auto-generated method stub
		attendanceRepository.save(attendanceList);
	}

	@Override
	public void markStudentAttendance(Attendance attendance) throws DuplicateException {
		// TODO Auto-generated method stub
		attendanceRepository.save(attendance);
	}

}
