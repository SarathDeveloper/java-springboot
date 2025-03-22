package com.pys.course.admin.web.service;

import com.pys.course.admin.support.exception.NotFoundException;

public interface NotificationService {
	
	public void sendRemainderToUnpaidCustomer(Long courseId,Long paymentCycleId) throws NotFoundException;
	
	public void sendMessageByStudentId(Long studentId);
	

}
