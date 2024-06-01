package com.app.CRM.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.CRM.model.product;

public interface ProductRepo extends JpaRepository<product, Long>{

	
}
