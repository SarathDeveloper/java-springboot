package com.pys.course.admin.web.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pys.course.admin.web.dto.AppTokenDTO;
import com.pys.course.admin.web.dto.PrincipalDTO;
import com.pys.course.admin.web.service.impl.UserServiceImpl;

/**
 * User service Implementation refer to {@link UserServiceImpl}
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
public interface UserService extends UserDetailsService {
	
	@SuppressWarnings("rawtypes")
	public AppTokenDTO getTokenDetails(String appToken) ;
	
	public String getVendorIdForUser(long userId);
	
	public PrincipalDTO getPrincipal(long userId);

}
