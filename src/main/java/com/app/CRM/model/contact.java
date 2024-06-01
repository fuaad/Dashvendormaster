 package com.app.CRM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class contact {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long contactId;
   private String contactName;
   private String phoneNumber;
   private String email;
   @ManyToOne
   @JoinColumn(
      name = "supplier_id"
   )
   private supplier supplier;

   public Long getContactId() {
      return this.contactId;
   }

   public void setContactId(Long contactId) {
      this.contactId = contactId;
   }

   public String getPhoneNumber() {
      return this.phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public supplier getSupplier() {
      return this.supplier;
   }

   public void setSupplier(supplier supplier) {
      this.supplier = supplier;
   }

   public contact() {
   }

   public String getContactName() {
      return this.contactName;
   }

   public void setContactName(String contactName) {
      this.contactName = contactName;
   }

   public contact(Long contactId, String contactName, String phoneNumber, String email, supplier supplier) {
      this.contactId = contactId;
      this.contactName = contactName;
      this.phoneNumber = phoneNumber;
      this.email = email;
      this.supplier = supplier;
   }
}