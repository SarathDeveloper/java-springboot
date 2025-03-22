package com.pys.course.admin.web.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class StudentPaymentId implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@Column(name = "student_id")
	private Long studentId;

	@Column(name = "payment_cycle_id")
	private Long paymentCycleId;

	// Constructors, getters, and setters

	public StudentPaymentId() {
	}

	public StudentPaymentId(Long paymentCycleId, Long studentId) {
		this.studentId = studentId;
		this.paymentCycleId = paymentCycleId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		StudentPaymentId entity = (StudentPaymentId) o;

		return Objects.equals(studentId, entity.studentId) && Objects.equals(paymentCycleId, entity.paymentCycleId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentId, paymentCycleId);
	}

}
