
    package com.app.CRM.VO;

import java.util.List;

public class ProductDTO {
   private String productName;
   private List<SupplierDTO> suppliers;

   public String getProductName() {
      return this.productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }

   public List<SupplierDTO> getSuppliers() {
      return this.suppliers;
   }

   public void setSuppliers(List<SupplierDTO> suppliers) {
      this.suppliers = suppliers;
   }
}
    