package com.pys.course.admin.web.dto;

import java.util.List;

import lombok.Data;

@Data
public class PrincipalDTO {
	
	private Long userId;
	private Long vendorId;
	private List<Long> courseId;
	private Long vendorBranchId;
    private String role;

}
