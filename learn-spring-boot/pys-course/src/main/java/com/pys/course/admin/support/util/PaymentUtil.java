package com.pys.course.admin.support.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.pys.course.admin.web.entity.PaymentCycle;

public class PaymentUtil {
	
	 public enum Frequency {
	        WEEKLY, BI_WEEKLY, MONTHLY, YEARLY,OTHERS
	    }
	 
	 public static List<PaymentCycle> generatePaymentCycles(LocalDate endDate, Frequency frequency,Long othersInDays) {
	        List<PaymentCycle> paymentCycles = new ArrayList<>();
	        LocalDate currentDate = LocalDate.now();
	        PaymentCycle paymentCycle = null;
	        switch (frequency) {
	            case WEEKLY:
	                while (currentDate.isBefore(endDate)) {
	                	paymentCycle = new PaymentCycle();
	                	paymentCycle.setPayCycleDate(currentDate);
	                    paymentCycles.add(paymentCycle);
	                    currentDate = currentDate.plusWeeks(1);
	                }
	                break;
	            case BI_WEEKLY:
	                while (currentDate.isBefore(endDate)) {
	                	paymentCycle = new PaymentCycle();
	                	paymentCycle.setPayCycleDate(currentDate);
	                    paymentCycles.add(paymentCycle);
	                    currentDate = currentDate.plusWeeks(2);
	                }
	                break;
	            case MONTHLY:
	                while (currentDate.isBefore(endDate)) {
	                	paymentCycle = new PaymentCycle();
	                	paymentCycle.setPayCycleDate(currentDate);
	                    paymentCycles.add(paymentCycle);
	                    currentDate = currentDate.plusMonths(1);
	                }
	                break;
	            case YEARLY:
	                while (currentDate.isBefore(endDate)) {
	                	paymentCycle = new PaymentCycle();
	                	paymentCycle.setPayCycleDate(currentDate);
	                    paymentCycles.add(paymentCycle);
	                    currentDate = currentDate.plusYears(1);
	                }
	                break;
	            case OTHERS:
	                while (currentDate.isBefore(endDate)) {
	                	paymentCycle = new PaymentCycle();
	                	paymentCycle.setPayCycleDate(currentDate);
	                    paymentCycles.add(paymentCycle);
	                    currentDate = currentDate.plusDays(1);
	                }
	                break;
	        }

	        return paymentCycles;
	    }


}
