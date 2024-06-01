 package com.app.CRM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductSupplier {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private int id;
   @ManyToOne
   @JoinColumn(
      name = "supplier_id"
   )
   private supplier supplier;
   @ManyToOne
   @JoinColumn(
      name = "product_id"
   )
   private product product;
   @ManyToOne
   @JoinColumn(
      name = "contact_id"
   )
   private contact contact;

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
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

   public contact getContact() {
      return this.contact;
   }

   public void setContact(contact contact) {
      this.contact = contact;
   }

   public ProductSupplier(int id, supplier supplier, product product, contact contact) {
      this.id = id;
      this.supplier = supplier;
      this.product = product;
      this.contact = contact;
   }

   public ProductSupplier() {
   }
}
    