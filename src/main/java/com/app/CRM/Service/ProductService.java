  package com.app.CRM.Service;

import com.app.CRM.Repo.ProductRepo;
import com.app.CRM.model.product;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
   @Autowired
   private ProductRepo productRepo;

   public product saveProduct(product product) {
      return (product)this.productRepo.save(product);
   }
   

   public List<product> getAllProducts() {
      return this.productRepo.findAll();
   }
   
   
   public List<product> getProductsByCategoryType(String categoryType) {
       return this.getProductsByCategoryType(categoryType);
   }
   

   public product getProductById(Long id) {
      return (product)this.productRepo.findById(id).orElse(null);
   }

   public product updateProduct(Long id, product product) {
      product existingProduct = (product)this.productRepo.findById(id).orElse(null);
      if (existingProduct != null) {
         existingProduct.setProductName(product.getProductName());
         return (product)this.productRepo.save(existingProduct);
      } else {
         return null;
      }
   }

   public void deleteProduct(Long id) {
      this.productRepo.deleteById(id);
   }

   public List<String> getAllProductNames() {
      return (List)this.productRepo.findAll().stream().map(product::getProductName).collect(Collectors.toList());
   }
}