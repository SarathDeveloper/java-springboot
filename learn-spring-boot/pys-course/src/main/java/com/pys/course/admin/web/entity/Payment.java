package com.pys.course.admin.web.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "course_payments")
@Setter
public class Payment implements Serializable {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String frequency;
	    private Long othersInDays;
	    private Long courseId;
	    private Long vendorId;
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    private LocalDate endDate;
	    
	    @OneToMany(mappedBy="payment", cascade = javax.persistence.CascadeType.ALL, orphanRemoval = false)
	    private List<PaymentCycle> paymentCycles;
		@ColumnDefault(value = "'N'")
		private String deleted = "N";
	    
	    @Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			Payment entity = (Payment) o;

			return Objects.equals(id, entity.id);
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
}
