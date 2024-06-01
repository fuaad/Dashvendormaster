 package com.app.CRM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class supplier {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private int supplierId;
   private String supplierName;
   private String supplierCode;
   private String location;
   @OneToMany(
      mappedBy = "supplier",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @JsonIgnore
   private List<ProductSupplier> products;
   @OneToMany(
      mappedBy = "supplier",
      cascade = {CascadeType.ALL}
   )
   @JsonIgnore
   private List<contact> contacts;

   public int getSupplierId() {
      return this.supplierId;
   }

   public void setSupplierId(int supplierId) {
      this.supplierId = supplierId;
   }

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

   public List<ProductSupplier> getProducts() {
      return this.products;
   }

   public void setProducts(List<ProductSupplier> products) {
      this.products = products;
   }

   public List<contact> getContacts() {
      return this.contacts;
   }

   public supplier(int supplierId, String supplierName, String supplierCode, String location, List<ProductSupplier> products, List<contact> contacts) {
      this.supplierId = supplierId;
      this.supplierName = supplierName;
      this.supplierCode = supplierCode;
      this.location = location;
      this.products = products;
      this.contacts = contacts;
   }

   public void setContacts(List<contact> contacts) {
      this.contacts = contacts;
   }

   public supplier() {
   }
}