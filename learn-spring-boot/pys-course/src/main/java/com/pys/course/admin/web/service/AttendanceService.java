package com.pys.course.admin.web.service;

import java.time.LocalDate;
import java.util.List;

import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.web.entity.Attendance;

public interface AttendanceService {
	
	public void markStudentsAttendance(List<Attendance> attendanceList) throws DuplicateException;
	
	public void markStudentAttendance(Attendance attendance) throws DuplicateException;
	
	public List<Attendance> getAttendanceByCourseId(Long courseId) throws NotFoundException;
	
	public List<Attendance> getAttendanceByStudentIdAndCourseId(Long studentId,Long courseId) throws NotFoundException;
	
	public List<Attendance> getAttendanceByAttendanceDateAndCourseId(LocalDate attendanceDate,Long courseId) throws NotFoundException;

}
