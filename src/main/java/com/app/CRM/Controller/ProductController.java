package com.app.CRM.Controller;

import com.app.CRM.Service.ProductService;
import com.app.CRM.model.product;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/products"})
public class ProductController {
   @Autowired
   private ProductService productService;

   @GetMapping({"/allproducts2"})
   public ResponseEntity getAllProduct() {
      List<product> product = this.productService.getAllProducts();
      return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
   }
   
   @GetMapping({"/allproducts/medical"})
   public ResponseEntity getMedicalProduct() {
      List<product> allProducts  = this.productService.getAllProducts();
      if (allProducts != null && !allProducts.isEmpty()) {
          List<product> medicalProducts = allProducts.stream()
                  .filter(product -> "Medical".equals(product.getCategoryType()))
                  .collect(Collectors.toList());
          return ResponseEntity.ok(medicalProducts);
      } else {
          return ResponseEntity.notFound().build();
      }
   }
   
   @GetMapping({"/allproducts/general"})
   public ResponseEntity getGeneralProduct() {
      List<product> allProducts  = this.productService.getAllProducts();
      if (allProducts != null && !allProducts.isEmpty()) {
          List<product> medicalProducts = allProducts.stream()
                  .filter(product -> "General".equals(product.getCategoryType()))
                  .collect(Collectors.toList());
          return ResponseEntity.ok(medicalProducts);
      } else {
          return ResponseEntity.notFound().build();
      }
   }
   
   
   @GetMapping({"/allproducts/medical2"})
   public ResponseEntity getMedicalProduct2() {
      List<product> product = this.productService.getProductsByCategoryType("Medical");
      return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
   }

   @GetMapping({"/allNames"})
   public List<String> getAllProductNames() {
      return this.productService.getAllProductNames();
   }

   @PostMapping({"/create"})
   public ResponseEntity createProduct(@RequestParam String productName, String CategoryType) {
      product product = new product();
      product.setProductName(productName);
      product.setCategoryType(CategoryType);
      this.productService.saveProduct(product);
      return ResponseEntity.ok(product);
   }

   @GetMapping({"/{id}"})
   public ResponseEntity<product> getProductById(@PathVariable Long id) {
      product product = this.productService.getProductById(id);
      return product == null ? new ResponseEntity(HttpStatus.NOT_FOUND) : new ResponseEntity(product, HttpStatus.OK);
   }

   @PutMapping({"/update/{id}"})
   public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody product product) {
      product updatedProduct = this.productService.updateProduct(id, product);
      return updatedProduct == null ? new ResponseEntity(HttpStatus.NOT_FOUND) : new ResponseEntity(HttpStatus.OK);
   }

   @DeleteMapping({"/delete/{id}"})
   public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
      this.productService.deleteProduct(id);
      return new ResponseEntity(HttpStatus.NO_CONTENT);
   }
}