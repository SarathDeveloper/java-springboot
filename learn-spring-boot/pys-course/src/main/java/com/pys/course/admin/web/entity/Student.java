package com.pys.course.admin.web.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
//@EntityListeners(AuditingEntityListener.class)
@Getter 
@Setter
@JsonIgnoreProperties(value={ "studentCourses","studentpayments"}, allowSetters= true)
/*
 * @Cache( usage = CacheConcurrencyStrategy.READ_WRITE )
 */
public class Student implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String mobileNo;
    private String age;
    private String gender;
    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = false
        )
    private List<StudentCourse> studentCourses = new ArrayList<>();
    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = false
        )
    private List<StudentPayment> studentpayments = new ArrayList<>();
	@ColumnDefault(value = "'N'")
	private String deleted;
    
    public Student() {
    	
    }
    
    @Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Student entity = (Student) o;

		return Objects.equals(id, entity.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
