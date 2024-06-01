 package com.app.CRM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class product {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long productId;
   private String productName;
   private String CategoryType;

   public Long getProductId() {
      return this.productId;
   }

   public void setProductId(Long productId) {
      this.productId = productId;
   }

   public String getProductName() {
      return this.productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }
   

   public String getCategoryType() {
	return CategoryType;
}

public void setCategoryType(String categoryType) {
	CategoryType = categoryType;
}



   public product(Long productId, String productName, String categoryType) {
	super();
	this.productId = productId;
	this.productName = productName;
	CategoryType = categoryType;
}

public product() {
   }
}