package com.app.CRM.VO;

import com.app.CRM.model.contact;
import com.app.CRM.model.product;
import com.app.CRM.model.supplier;
import java.util.List;

public class ProductSupplierVO {
   private supplier supplier;
   private product product;
   private List<contact> contacts;

   public List<contact> getContacts() {
      return this.contacts;
   }

   public void setContacts(List<contact> contacts) {
      this.contacts = contacts;
   }

   public supplier getSupplier() {
      return this.supplier;
   }

   public void setSupplier(supplier supplier) {
      this.supplier = supplier;
   }

   public product getProduct() {
      return this.product;
   }

   public void setProduct(product product) {
      this.product = product;
   }
}
    