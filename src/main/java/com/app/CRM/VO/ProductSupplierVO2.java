 package com.app.CRM.VO;

import java.util.List;

public class ProductSupplierVO2 {
   private int psid;
   private int supplierid;
   private Long productId;
   private List<contactVO> contacts;

   public int getSupplierid() {
      return this.supplierid;
   }

   public void setSupplierid(int supplierid) {
      this.supplierid = supplierid;
   }

   public Long getProductId() {
      return this.productId;
   }

   public void setProductId(Long productId) {
      this.productId = productId;
   }

   public List<contactVO> getContacts() {
      return this.contacts;
   }

   public void setContacts(List<contactVO> contacts) {
      this.contacts = contacts;
   }

   public int getPsid() {
      return this.psid;
   }

   public void setPsid(int psid) {
      this.psid = psid;
   }
}
    