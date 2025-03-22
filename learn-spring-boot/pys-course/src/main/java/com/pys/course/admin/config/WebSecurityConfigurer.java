package com.pys.course.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.pys.course.admin.filter.AuthTokenFilter;
import com.pys.course.admin.support.authentication.CustomAuthenticationProvider;
import com.pys.course.admin.web.service.UserService;

/**
 * <b>Security configurations</b><br>
 * 
 * You can configure your application security features by this class when .yml
 * file configurations can not meet your requirements.
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	private @Autowired UserService userService;
	
	
	// @Bean
	  public AuthTokenFilter authenticationJwtTokenFilter() {
	    return new AuthTokenFilter();
	  }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/signin").permitAll()
				
		//http.authorizeRequests().antMatchers("/swagger-ui.html","/webjars/**",
			//	  "/swagger-resources/**","/v2/api-docs", "/signin").permitAll()
					.and().cors().and().csrf().ignoringAntMatchers("/api/**");
			//.and().httpBasic();
		//http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public AuthenticationProvider customAuthenticationProvider() {
		CustomAuthenticationProvider ssoAuthenticationProvider = new CustomAuthenticationProvider(userService);
		return ssoAuthenticationProvider;
	}
}
