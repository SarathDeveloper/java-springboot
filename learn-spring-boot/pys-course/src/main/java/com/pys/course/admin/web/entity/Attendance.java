package com.pys.course.admin.web.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
@Entity
//@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@JsonIgnoreProperties(value={ "course"}, allowSetters= true)
//@Table(uniqueConstraints=
//@UniqueConstraint(name = "UniqueStudentIdCourseIdAndAttendanceDate",columnNames = {"student_id", "course_id","attendanceDate"})) 
public class Attendance implements Serializable {
	private static final long serialVersionUID = 7926660989998222681L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne

	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne

	@JoinColumn(name = "course_id")
	private Course course;

	// private Long studentId;
	// private Long courseId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date attendanceDate;
	@Enumerated(EnumType.STRING)
	private AttendanceStatus attendanceStatus;

	/*
	 * private User createBy; private Date createDate; private User lastModifiedBy;
	 * private Date lastModifiedDate;
	 */

	/*
	 * @CreatedBy
	 * 
	 * @ManyToOne public User getCreateBy() { return createBy; }
	 * 
	 * public void setCreateBy(User createBy) { this.createBy = createBy; }
	 * 
	 * @CreatedDate
	 * 
	 * @Column(name = "CREATE_DATE", nullable = false) public Date getCreateDate() {
	 * return createDate; }
	 * 
	 * public void setCreateDate(Date createDate) { this.createDate = createDate; }
	 * 
	 * @LastModifiedBy
	 * 
	 * @ManyToOne public User getLastModifiedBy() { return lastModifiedBy; }
	 * 
	 * public void setLastModifiedBy(User lastModifiedBy) { this.lastModifiedBy =
	 * lastModifiedBy; }
	 * 
	 * @LastModifiedDate
	 * 
	 * @Column(name = "LAST_MODIFIED_DATE") public Date getLastModifiedDate() {
	 * return lastModifiedDate; }
	 * 
	 * public void setLastModifiedDate(Date lastModifiedDate) {
	 * this.lastModifiedDate = lastModifiedDate; }
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Attendance entity = (Attendance) o;

		return Objects.equals(id, entity.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public enum AttendanceStatus {
		PRESENT, ABSENT, LATE,
		// Other possible statuses
	}
}
