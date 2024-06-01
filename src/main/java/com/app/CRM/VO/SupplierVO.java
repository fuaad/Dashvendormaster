package com.app.CRM.VO;

import com.app.CRM.model.contact;
import java.util.List;

public class SupplierVO {
   private String supplierName;
   private String supplierCode;
   private String location;
   private String username;
   private String password;
   private String role;
   private List<contact> contacts;

   public String getSupplierName() {
      return this.supplierName;
   }

   public void setSupplierName(String supplierName) {
      this.supplierName = supplierName;
   }

   public String getSupplierCode() {
      return this.supplierCode;
   }

   public void setSupplierCode(String supplierCode) {
      this.supplierCode = supplierCode;
   }

   public String getLocation() {
      return this.location;
   }

   public void setLocation(String location) {
      this.location = location;
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

   public List<contact> getContacts() {
      return this.contacts;
   }

   public void setContacts(List<contact> contacts) {
      this.contacts = contacts;
   }
}