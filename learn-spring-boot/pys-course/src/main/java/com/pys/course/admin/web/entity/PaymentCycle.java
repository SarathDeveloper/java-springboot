package com.pys.course.admin.web.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value={ "payment"}, allowSetters= true)
public class PaymentCycle implements Serializable {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	//	@Temporal(TemporalType.TIMESTAMP)
	    private LocalDate payCycleDate;
	    @ManyToOne
	    @JoinColumn(name="payment_id")
	    private Payment payment;
		@ColumnDefault(value = "'N'")
		private String deleted = "N";
	 
	  @OneToMany(
		        mappedBy = "paymentCycle",
		        cascade = javax.persistence.CascadeType.ALL,
		        orphanRemoval = false
		    )
	
	private List<StudentPayment> studentPayments = new ArrayList<StudentPayment>();
	  
	    @Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			PaymentCycle entity = (PaymentCycle) o;

			return Objects.equals(id, entity.id);
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

}
