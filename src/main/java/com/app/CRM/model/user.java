 package com.app.CRM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class user {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long userId;
   private String username;
   private String password;
   private String role;
   @OneToOne
   @JoinColumn(
      name = "supplier_Id"
   )
   private supplier supplier;

   public Long getUserId() {
      return this.userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getRole() {
      return this.role;
   }

   public void setRole(String role) {
      this.role = role;
   }

   public supplier getSupplier() {
      return this.supplier;
   }

   public void setSupplier(supplier supplier) {
      this.supplier = supplier;
   }
}