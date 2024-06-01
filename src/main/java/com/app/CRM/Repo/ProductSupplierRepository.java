    package com.app.CRM.Repo;

import com.app.CRM.model.ProductSupplier;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, Long> {
   List<ProductSupplier> findAllBySupplierSupplierId(Long supplierId);
}