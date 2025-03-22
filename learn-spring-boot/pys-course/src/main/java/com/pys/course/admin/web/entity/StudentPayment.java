package com.pys.course.admin.web.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student_payments")
@Getter
@Setter
@JsonIgnoreProperties(value={ "paymentCycle"}, allowSetters= true)
public class StudentPayment implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	    private StudentPaymentId id;

	    @ManyToOne
	    @MapsId("studentId")
	    @JoinColumn(name = "student_id")
	    private Student student;

	    @ManyToOne
	    @MapsId("paymentCycleId")
	    @JoinColumn(name = "paymentCycle_id")
	    private PaymentCycle paymentCycle;
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    private LocalDate paymentDate =  LocalDate.now();
	    private double amount;
	    private String paymentStatus;
	    private String paymentMode;
		@ColumnDefault(value = "'N'")
		private String deleted = "N";
	    
	    
	    @Override
	  		public boolean equals(Object o) {
	  			if (this == o)
	  				return true;
	  			if (o == null || getClass() != o.getClass()) {
	  				return false;
	  			}

	  			StudentPayment entity = (StudentPayment) o;

	  			return Objects.equals(student, entity.paymentCycle) &&
	  		               Objects.equals(paymentCycle, entity.paymentCycle);
	  		}

	  		@Override
	  		public int hashCode() {
	  			return Objects.hash(paymentCycle,student);
	  		}
}
