package com.pys.course.admin.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pys.course.admin.support.util.AuthEntryPointJwt;
import com.pys.course.admin.support.util.JwtUtils;
import com.pys.course.admin.web.dto.Role;
import com.pys.course.admin.web.dto.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
	  @Autowired
	  private JwtUtils jwtUtils;

	  
	  private UserDetailsService userDetailsService;
	  
	  private RememberMeServices rememberMeServices = new NullRememberMeServices();
	  
	  @Autowired
	  private AuthEntryPointJwt authenticationEntryPoint;

	  @Autowired
	  public AuthTokenFilter(UserDetailsService userDetailsService,
			  AuthEntryPointJwt authenticationEntryPoint) {
			//super(authenticationManager, authenticationEntryPoint);
			this.userDetailsService = userDetailsService;
			this.authenticationEntryPoint = authenticationEntryPoint;
		}

	  @Override
	  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	      throws ServletException, IOException {
	   // try {
	      String jwt = jwtUtils.getJwtFromHeader(request);
	      List<Role> roles = new ArrayList<Role>();
	      if (jwt != null && jwtUtils.validateJwtToken(jwt,request,roles)) {
	        User user = new User();
			user.setAuthorities(roles);
			Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(jwt,
					"mobileAPPUser", authorities);
			SecurityContextHolder.getContext().setAuthentication(authRequest);
			this.rememberMeServices.loginSuccess(request, response, authRequest);
	
	      }else {
	    	  response.setStatus(403);
				AuthenticationException e = new BadCredentialsException("Invalid Token");
				this.authenticationEntryPoint.commence(request, response, e);
				return;
	      }
	   // }
	  /*catch (Exception e) {
	      log.error("Cannot set user authentication: {}", e);
	      throw e;
	    }*/

	    filterChain.doFilter(request, response);
	  }

	  
	
	}

