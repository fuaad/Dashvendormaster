 package com.app.CRM.Service;

import com.app.CRM.Repo.SupplierRepository;
import com.app.CRM.Repo.UserRepository;
import com.app.CRM.model.supplier;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
   @Autowired
   private SupplierRepository supplierRepository;
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private UserService userService;

   public supplier saveSupplier(supplier supplier) {
      return (supplier)this.supplierRepository.save(supplier);
   }

   public supplier getSupplierById(long id) {
      return (supplier)this.supplierRepository.findById(id).orElse(null);
   }

   public List<String> getAllSupplier() {
      return (List)this.supplierRepository.findAll().stream().map(supplier::getSupplierName).collect(Collectors.toList());
   }

   public List<supplier> getAllSupplier2() {
      return this.supplierRepository.findAll();
   }

   public supplier updateSupplier(Long id, supplier supplier) {
      supplier existingSupplier = (supplier)this.supplierRepository.findById(id).orElse(null);
      if (existingSupplier != null) {
         existingSupplier.setSupplierName(supplier.getSupplierName());
         existingSupplier.setSupplierCode(supplier.getSupplierCode());
         existingSupplier.setLocation(supplier.getLocation());
         return (supplier)this.supplierRepository.save(existingSupplier);
      } else {
         return null;
      }
   }

   public void deleteSupplier(Long id) {
      this.supplierRepository.deleteById(id);
   }
   
   

}