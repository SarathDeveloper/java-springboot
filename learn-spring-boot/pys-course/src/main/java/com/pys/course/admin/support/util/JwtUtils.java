package com.pys.course.admin.support.util;

import java.security.Key;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pys.course.admin.web.dto.AppTokenDTO;
import com.pys.course.admin.web.dto.Role;
import com.pys.course.admin.web.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class JwtUtils {

	

	  @Value("${pys.course.jwtSecret}")
	  private String jwtSecret;

	  @Value("${pys.course.jwtExpirationMs}")
	  private int jwtExpirationMs;

	  @Value("${pys.course.jwtHeader}")
	  private String jwtHeader;
	  
	  @Autowired
	  private UserService userService;
	  
	//  private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	  public String getJwtFromHeader(HttpServletRequest request) {
		  String mobAuthToken = request.getHeader(jwtHeader);
	    if (mobAuthToken != null) {
	      return mobAuthToken;
	    } else {
	      return null;
	    }
	  }

	  public String getUserNameFromJwtToken(String token) {
	    return Jwts.parser().setSigningKey(key())
	        .parseClaimsJws(token).getBody().getSubject();
	  }
	  
	  public String getUserNameFromJwtToken(String token,HttpServletRequest request) {
		    return Jwts.parser().setSigningKey(key())
		        .parseClaimsJws(token).getBody().getSubject();
		  }
	  
	  private Key key() {
	    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	  }

	  public boolean validateJwtToken(String authToken) {
	    try {
	      Jwts.parser().setSigningKey(key()).parse(authToken);
	      return true;
	    } catch (MalformedJwtException e) {
	    	log.error("Invalid JWT token: {}", e.getMessage());
	    } catch (ExpiredJwtException e) {
	    	log.error("JWT token is expired: {}", e.getMessage());
	    } catch (UnsupportedJwtException e) {
	    	log.error("JWT token is unsupported: {}", e.getMessage());
	    } catch (IllegalArgumentException e) {
	    	log.error("JWT claims string is empty: {}", e.getMessage());
	    }

	    return false;
	  }
	  
		/*
		 * public String generateTokenFromUsername(String username) { return
		 * Jwts.builder() .setSubject(username) .setIssuedAt(new Date())
		 * .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
		 * .signWith(key(), SignatureAlgorithm.HS256) .compact(); }
		 */
	  
	  public boolean validateJwtToken(String jwt, HttpServletRequest request) {
			// This line will throw an exception if it is not a signed JWS (as
			// expected)
			AppTokenDTO mobAppToken = userService.getTokenDetails(jwt);
			if (mobAppToken != null) {
				String signature = "COEUZ" + mobAppToken.getUserName() + "ARSMVS_CRICKET";
				byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(signature);
				Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
				Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwt).getBody();
				if (claims.getId().equals(mobAppToken.getTokenId())
						&& claims.getSubject().equals(mobAppToken.getUserName())) {
					request.setAttribute("LoggedInUser", mobAppToken.getUserId());
					request.setAttribute("LoggedInUserName", mobAppToken.getUserName());
					/*
					 * request.setAttribute("vendorId",
					 * userDAO.getVendorIdForUser(Long.parseLong(mobAppToken.getUserId()))); for
					 * (String s : claims.keySet()) { MobileRole r = new MobileRole(); r.setName(s);
					 * roles.add(r); } roles.forEach(x -> { if
					 * (x.getAuthority().equals("ROLE_CHAIN")) {
					 * request.getSession().setAttribute("groupId",
					 * userDAO.getGroupIdForUser(Long.parseLong(mobAppToken.getUserId()))); } });
					 */
				
					return true;
				}
			}
			return false;
		}

	  
	  public boolean validateJwtToken(String jwt, HttpServletRequest request,List<Role> roles) {
			// This line will throw an exception if it is not a signed JWS (as
			// expected)
			AppTokenDTO mobAppToken = userService.getTokenDetails(jwt);
			if (mobAppToken != null && null != mobAppToken.getUserName()) {
				String signature = "COEUZ" + mobAppToken.getUserName() + "ARSMVS_CRICKET";
				byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(signature);
				Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
				Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwt).getBody();
				if (claims.getId().equals(mobAppToken.getTokenId())
						&& claims.getSubject().equals(mobAppToken.getUserName())) {
					request.setAttribute("LoggedInUser", mobAppToken.getUserId());
					request.setAttribute("LoggedInUserName", mobAppToken.getUserName());
					System.out.println("3333"+Long.parseLong(mobAppToken.getUserId()));
					String vendorId = userService.getVendorIdForUser(Long.parseLong(mobAppToken.getUserId()) );
					request.setAttribute("vendorId", vendorId);
					for (String s : claims.keySet()) {
						Role r = new Role();
						r.setName(s);
						roles.add(r);
					}
					 
				
					return true;
				}
			}
			return false;
		}
	}

