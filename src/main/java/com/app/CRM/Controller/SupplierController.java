package com.app.CRM.Controller;

import com.app.CRM.Service.SupplierService;
import com.app.CRM.Service.UserService;
import com.app.CRM.VO.SupplierVO;
import com.app.CRM.model.supplier;
import com.app.CRM.model.user;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/supplier"})
public class SupplierController {
   @Autowired
   private SupplierService supplierService;
   @Autowired
   private UserService userService;

   @PostMapping({"/create"})
   public ResponseEntity createSupplier(@RequestBody SupplierVO supplierVO) {
      String userNameValidation = this.userService.usernameValidation(supplierVO.getUsername());
      if (userNameValidation.equals("false")) {
         return ResponseEntity.badRequest().body("Username is not valid. Please choose a different username.");
      } else {
         supplier supplier = new supplier();
         supplier.setSupplierName(supplierVO.getSupplierName());
         supplier.setSupplierCode(supplierVO.getSupplierCode());
         supplier.setLocation(supplierVO.getLocation());
         this.supplierService.saveSupplier(supplier);
         user user = new user();
         user.setUsername(supplierVO.getUsername());
         user.setPassword(supplierVO.getPassword());
         user.setRole(supplierVO.getRole());
         user.setSupplier(supplier);
         this.userService.saveUser(user);
         return ResponseEntity.ok(supplierVO);
      }
   }

   @GetMapping({"/{id}"})
   public ResponseEntity getSupplierById(@PathVariable Long id) {
      supplier supplier = this.supplierService.getSupplierById(id);
      return supplier != null ? ResponseEntity.ok(supplier) : ResponseEntity.notFound().build();
   }

   @GetMapping({"/allsuppliers2"})
   public ResponseEntity getAllSupplier2() {
      List<supplier> supplier = this.supplierService.getAllSupplier2();
      return supplier != null ? ResponseEntity.ok(supplier) : ResponseEntity.notFound().build();
   }

   @GetMapping({"/allsuppliers"})
   public void getAllSupplier() {
      List<supplier> suppliers = this.supplierService.getAllSupplier2();
      System.out.println("suppliers " + suppliers);
   }

   @PutMapping({"/update/{id}"})
   public ResponseEntity<Void> updateSupplier(@PathVariable Long id, @RequestBody supplier supplier) {
      supplier updatedSupplier = this.supplierService.updateSupplier(id, supplier);
      return updatedSupplier == null ? new ResponseEntity(HttpStatus.NOT_FOUND) : new ResponseEntity(HttpStatus.OK);
   }

   @DeleteMapping({"/delete/{id}"})
   public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
      this.supplierService.deleteSupplier(id);
      return new ResponseEntity(HttpStatus.NO_CONTENT);
   }
   
   @GetMapping("/getUserCredentials")
   public ResponseEntity<?> getUserCredentials(@RequestParam Long supplierId) {
       user user = userService.getUserBySupplierId(supplierId);
       if (user != null) {
           return ResponseEntity.ok(user);
       } else {
           return ResponseEntity.notFound().build();
       }
   }
   
}