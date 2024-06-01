package com.app.CRM.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.CRM.model.user;

public interface UserRepository extends JpaRepository<user, Long> {

	user findByUsername(String username);
	
	user getRolesByUsername(String username);
	
	@Query("SELECT u FROM user u WHERE u.supplier.supplierId = :supplierId")
    user findBySupplierId(@Param("supplierId") Long supplierId);

}
