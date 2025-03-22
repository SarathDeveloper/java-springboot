package com.pys.course.admin.web.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.pys.course.admin.web.dto.AppTokenDTO;
import com.pys.course.admin.web.dto.PrincipalDTO;
import com.pys.course.admin.web.entity.User;
import com.pys.course.admin.web.repository.UserRepository;
import com.pys.course.admin.web.service.UserService;

/**
 * User service implementation
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	private @Autowired UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Assert.hasText(username, "User name must not be empty");

		User user = userRepository.findByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("User is not found");
		} else {
			return user;
		}
	}

	@Override
	public AppTokenDTO getTokenDetails(String appToken) {
		// TODO Auto-generated method stub
	
		
		Optional<List<Object[]>> resultList = userRepository.findUserByAppToken(appToken);

		AppTokenDTO appTokenDTO = new AppTokenDTO();
		resultList.ifPresent(results -> {
		    if (!results.isEmpty()) {
		    	
		        Object[] result = results.get(0);
		        appTokenDTO.setTokenId(result[0].toString());
		        appTokenDTO.setUserId(result[1].toString());
		        appTokenDTO.setUserName(result[2].toString());
		    }
		});
		
		return appTokenDTO;
		
		
	}

	@Override
	public String getVendorIdForUser(long userId) {
		// TODO Auto-generated method stub
		 Object vendorId = userRepository.getVendorIdForUser(userId);
		if(vendorId == null) {
			vendorId = "0";
		}
		return vendorId.toString();
	}

	@Override
	public PrincipalDTO getPrincipal(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
