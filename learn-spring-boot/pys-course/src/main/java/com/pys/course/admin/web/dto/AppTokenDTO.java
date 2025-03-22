package com.pys.course.admin.web.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AppTokenDTO {
	
	
		private String userId;
		private String tokenId;
		private String userName;
		private String mobToken;
		private String notificationId;
		private Date createdTime;
		private Date expiryTime;

}
