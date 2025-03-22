package com.pys.course.admin.web.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
@Entity
//@Table(uniqueConstraints = {@UniqueConstraint( columnNames = { "coach_id", "id" })})
//@EntityListeners(AuditingEntityListener.class)
@Where(clause = "deleted='N'")
@DynamicUpdate
public class Course implements Serializable {
	private static final long serialVersionUID = -405064709161586189L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long vendorId;
	private String courseName;
	private String courseDescription;
	private String gender;
	private Integer minAge;
	private Integer maxAge;
	private String feeCycle;
	private Long othersInDays;
	private String category;
	private Integer maxAllowed;
	private Long feeAmount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate endDate;
	private String startTime;
	private String endTime;
	private String courseType;
	private String classDays;
	private int daysMask; // Bitmask to store days
	@ColumnDefault(value = "'N'")
	private String deleted = "N";

	@ManyToMany()
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "coach_id")
	private List<Coach> coaches;

	  @OneToMany(
		        mappedBy = "course",
		        cascade = javax.persistence.CascadeType.ALL,
		        orphanRemoval = false
		    )
	
	private List<StudentCourse> studentCourses = new ArrayList<StudentCourse>();

	/*
	 * @EmbeddedId private CourseCoach coursecoach;
	 * 
	 * private User createBy; private Date createDate; private User lastModifiedBy;
	 * private Date lastModifiedDate;
	 * 
	 * 
	 * public CourseCoach getCoursecoach() { return coursecoach; }
	 * 
	 * public void setCoursecoach(CourseCoach coursecoach) { this.coursecoach =
	 * coursecoach; }
	 */
	  public Course() {
	    }
	  
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "COURSE_NAME", unique = true, nullable = false)
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Coach> getCoaches() {
		return coaches;
	}

	public void setCoaches(List<Coach> coaches) {
		this.coaches = coaches;
	}
	/*
	 * @CreatedBy
	 * 
	 * @ManyToOne public User getCreateBy() { return createBy; }
	 * 
	 * public void setCreateBy(User createBy) { this.createBy = createBy; }
	 * 
	 * @CreatedDate
	 * 
	 * @Column(name = "CREATE_DATE") public Date getCreateDate() { return
	 * createDate; }
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

		Course entity = (Course) o;

		return Objects.equals(id, entity.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public List<StudentCourse> getStudents() {
		return studentCourses;
	}

	public void setStudents(List<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public String getFeeCycle() {
		return feeCycle;
	}

	public void setFeeCycle(String feeCycle) {
		this.feeCycle = feeCycle;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getMaxAllowed() {
		return maxAllowed;
	}

	public void setMaxAllowed(Integer maxAllowed) {
		this.maxAllowed = maxAllowed;
	}

	public Long getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(Long feeAmount) {
		this.feeAmount = feeAmount;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getClassDays() {
		return classDays;
	}

	public void setClassDays(String classDays) {
		this.classDays = classDays;
	}
	
	 public void addStudent(Student student) {
	        StudentCourse studentCourse = new StudentCourse(this, student);
	      //  students = new ArrayList<>();
	        studentCourses.add(studentCourse);
	       student.getStudentCourses().add(studentCourse);
	    }
	 
	    public void removeStudent(Student student) {
	        for (Iterator<StudentCourse> iterator = studentCourses.iterator();
	             iterator.hasNext(); ) {
	        	StudentCourse studentCourse = iterator.next();
	             
	            if (studentCourse.getCourse().equals(this) &&
	            		studentCourse.getStudent().equals(student)) {
	                iterator.remove();
	                studentCourse.getStudent().getStudentCourses().remove(studentCourse);
	                studentCourse.setCourse(null);
	                studentCourse.setStudent(null);
	            }
	        }
	    }

	    
	    public void removeTag8(Student student) {
	    	studentCourses.removeIf(studentCourse ->
	                studentCourse.getCourse().equals(this) && studentCourse.getStudent().equals(student));

	    	studentCourses.stream()
	                .filter(studentCourse ->
	                        studentCourse.getStudent().equals(student))
	                .forEach(studentCourse -> {
	                    studentCourse.getStudent().getStudentCourses().remove(studentCourse);
	                    studentCourse.setCourse(null);
	                    studentCourse.setStudent(null);
	                });
	    }

		public String getDeleted() {
			return deleted;
		}

		public void setDeleted(String deleted) {
			this.deleted = deleted;
		}
		
		 public void setDays(Days... days) {
		        for (Days day : days) {
		            daysMask |= 1 << day.ordinal(); // Set the corresponding bit for each weekday
		        }
		    }
		 
		 public boolean hasDay(Days day) {
		        return (daysMask & (1 << day.ordinal())) != 0; // Check if the bit is set for the weekday
		    }
		 
			public int getDays() {
				return daysMask;
			}

			public Long getOthersInDays() {
				return othersInDays;
			}

			public void setOthersInDays(Long othersInDays) {
				this.othersInDays = othersInDays;
			}

}
