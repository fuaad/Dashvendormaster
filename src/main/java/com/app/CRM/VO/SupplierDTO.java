 package com.app.CRM.VO;

import java.util.List;

public class SupplierDTO {
   private String supplierName;
   private List<ContactDTO> contacts;

   public String getName() {
      return this.supplierName;
   }

   public void setName(String supplierName) {
      this.supplierName = supplierName;
   }

   public List<ContactDTO> getContacts() {
      return this.contacts;
   }

   public void setContacts(List<ContactDTO> contacts) {
      this.contacts = contacts;
   }
}