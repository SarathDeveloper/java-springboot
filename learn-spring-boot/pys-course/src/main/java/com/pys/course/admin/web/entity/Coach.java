package com.pys.course.admin.web.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * We do not use the union primary key because of the extended demand. Actually,
 * spring data jpa not provider us a good support for union primary key
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
@Entity
//@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "mobileNo" }))
@JsonIgnoreProperties(value={ "courses"}, allowSetters= true)
public class Coach implements Serializable {
	private static final long serialVersionUID = 3330193491362488868L;

	private Long id;
	private String name;
	private String email;
	private String mobileNo;
	private String specialization;
	private List<Course> courses;
	@ColumnDefault(value = "'N'")
	private String deleted;
	/*
	 * private User createBy; private Date createDate; private User lastModifiedBy;
	 * private Date lastModifiedDate;
	 */



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany
	//@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.PERSIST })
	@JoinColumn(name = "course_id")
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> course) {
		this.courses = course;
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
	 * 
	 */ 
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Coach entity = (Coach) o;

		return Objects.equals(mobileNo, entity.mobileNo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
