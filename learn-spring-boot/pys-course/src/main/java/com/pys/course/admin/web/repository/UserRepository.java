package com.pys.course.admin.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.pys.course.admin.support.jpa.CustomJpaRepository;
import com.pys.course.admin.web.entity.User;

/**
 * <b>User Repository</b><br>
 * You can use NamedQuery or Query annotation here.<br>
 * 
 * 
 * @author Wenbo Wang (jackie-1685@163.com)
 */
public interface UserRepository extends CustomJpaRepository<User, Long> {

	/**
	 * Find user by username
	 * 
	 */
	public User findByUsername(String username);
	
	@Query(value = "SELECT mat.tokenid as tokenId, mat.userid as userId, mat.username as userName "
			+ "FROM mobAppTokens mat WHERE mat.mobtoken = ?1",nativeQuery = true)
	public Optional<List<Object[]>> findUserByAppToken(String appToken);
	
	@Query(value = "SELECT vbm.vendorBranchId as vendorId FROM vendorBranchMapping vbm "
			+ "WHERE vbm.vendorUserId = ?1",nativeQuery = true)
	public Object getVendorIdForUser(long userId);
	
	@Query(value = "SELECT vbm.vendorBranchId as vendorId FROM vendorBranchMapping vbm "
			+ "WHERE vbm.vendorUserId = ?1",nativeQuery = true)
	public Optional<List<Object[]>> getPrincipal(long userId);
}
