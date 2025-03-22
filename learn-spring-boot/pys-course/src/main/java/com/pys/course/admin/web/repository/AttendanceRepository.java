package com.pys.course.admin.web.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.pys.course.admin.support.jpa.CustomJpaRepository;
import com.pys.course.admin.web.entity.Attendance;

/**
 * <b>Attendance Repository</b><br>
 * You can use NamedQuery or Query annotation here.
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
public interface AttendanceRepository extends CustomJpaRepository<Attendance, Long> {

	@Query(value = "SELECT a from Attendance a where a.student.id=?1 AND a.course.id=?2")
	public List<Attendance> findtAttendanceByStudentIdAndCourseId(Long studentId,Long courseId);

	@Query(value = "SELECT a from Attendance a where a.course.id=?1")
	public List<Attendance> findAttendanceByCourseId(Long courseId);
	
	@Query(value = "SELECT a from Attendance a where a.attendanceDate=?1 AND a.course.id=?2")
	public List<Attendance> findAttendanceByAttendanceDateAndCourseId(LocalDate attendanceDate,Long courseId);
}
