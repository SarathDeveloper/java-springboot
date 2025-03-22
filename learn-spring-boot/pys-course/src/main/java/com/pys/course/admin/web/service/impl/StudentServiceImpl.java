package com.pys.course.admin.web.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.pys.course.admin.support.constant.MessageCode;
import com.pys.course.admin.support.exception.DuplicateException;
import com.pys.course.admin.support.exception.NotFoundException;
import com.pys.course.admin.web.entity.Course;
import com.pys.course.admin.web.entity.Student;
import com.pys.course.admin.web.entity.StudentCourse;
import com.pys.course.admin.web.entity.StudentCourseId;
import com.pys.course.admin.web.repository.CourseRepository;
import com.pys.course.admin.web.repository.StudentCourseRepository;
import com.pys.course.admin.web.repository.StudentRepository;
import com.pys.course.admin.web.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;
	private final StudentCourseRepository studentCourseRepository;

	@Autowired
	public StudentServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, StudentCourseRepository studentCourseRepository) {
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
		this.studentCourseRepository = studentCourseRepository;
	}

	@Override
	public Student getStudent(Long studentId) throws NotFoundException {
		Assert.notNull(studentId, "Student ID must not be null");

		Student student = studentRepository.findOne(studentId);
		if (null == student) {
			throw new NotFoundException(MessageCode.COACH_NOT_FOUND);
		}

		return student;
	}

	@Override
	@Transactional
	public Student addStudent(Student student) throws DuplicateException {
		Assert.hasText(student.getName(), "Course name must not be empty");

	//	Course newCourse = courseRepository.findOne(course.getId());

		return studentRepository.save(student);
	}

	@Override
	@Transactional
	public void deleteStudent(Long studentId) throws NotFoundException {
		Assert.notNull(studentId, "Book ID must not be null");

		Student student = studentRepository.findOne(studentId);
		if (null == student) {
			throw new NotFoundException(MessageCode.COACH_NOT_FOUND);
		}

		// bookAuthorRepository.deleteByBook(book);
		studentRepository.delete(student);
	}

		@Override
	public Set<StudentCourse> getStudentsByCourseId(Long courseId) throws NotFoundException {
		
		
	
			StudentCourseId studentCourseId = new StudentCourseId();
			studentCourseId.setCourseId(courseId);
			
			Set<StudentCourse> students = studentCourseRepository.findStudentCourseById(courseId);
		if (null == students) {
			throw new NotFoundException(MessageCode.COURSE_NOT_FOUND);
		}
		// TODO Auto-generated method stub
		return students;
	}

		@Override
		public Student addStudentByCourseId(Long courseId, Student student) throws DuplicateException {
			// TODO Auto-generated method stub
			
			Course course = courseRepository.findOne(courseId);
			if (null == course) {
				throw new NotFoundException(MessageCode.COURSE_NOT_FOUND);
			}
			// course.addStudent(student);
			// course = courseRepository.save(course);
			StudentCourse studCourse = new StudentCourse(course,student);
			student.getStudentCourses().add(studCourse);
			studentRepository.save(student);
		/*
		 * StudentCourse studCourse = new StudentCourse(course,student);
		 * student.getStudentCourses().add(studCourse);
		 * studentCourseRepository.save(studCourse);
		 */
			
			return student;
		}

		@Override
		public Student getStudentByMobileNo(String mobileNo) throws NotFoundException {
			// TODO Auto-generated method stub
			Assert.notNull(mobileNo, "Mobile No must not be null");

			Student student = studentRepository.findStudentByMobileNo(mobileNo);
			if (null == student) {
				throw new NotFoundException(MessageCode.STUDENT_NOT_FOUND);
			}

			return student;
		}

		@Override
		public void enrollStudentToCourse(Long StudentId, Long courseId) throws NotFoundException {
			// TODO Auto-generated method stub
			Assert.notNull(StudentId, "Coach ID must not be null");
			Assert.notNull(courseId, "Course ID must not be null");

			Student student = studentRepository.findOne(StudentId);
			if (null == student) {
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
			StudentCourse studentCourse = new StudentCourse(course,student);
			//studentCourse.setStudent(student);
			//studentCourse.setCourse(course);
			studentCourseRepository.save(studentCourse);
			
		/*	if (null == course.getStudents() || course.getStudents().isEmpty()) {
				List<StudentCourse> students = new ArrayList<StudentCourse>();
				
				studentCourse.setStudent(student);
				students.add(studentCourse);
				course.setStudents(students);
			} else {
				studentCourse.setStudent(student);
				course.getStudents().add(studentCourse);
			}
			courseRepository.save(course); */
		}

}
