 package com.app.CRM.Controller;

import com.app.CRM.Service.ContactService;
import com.app.CRM.Service.ProductSupplierService;
import com.app.CRM.VO.ProductDTO;
import com.app.CRM.VO.ProductSupplierVO;
import com.app.CRM.VO.ProductSupplierVO2;
import com.app.CRM.VO.contactVO;
import com.app.CRM.model.ProductSupplier;
import com.app.CRM.model.contact;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductSupplierController {
   @Autowired
   private ProductSupplierService productSupplierService;
   @Autowired
   private ContactService contactService;

   @PostMapping({"/create"})
   public ResponseEntity<ProductSupplier> createProductSupplier(@RequestBody ProductSupplier productSupplier) {
      this.contactService.save(productSupplier.getContact());
      ProductSupplier createdProductSupplier = this.productSupplierService.createProductSupplier(productSupplier);
      return new ResponseEntity(createdProductSupplier, HttpStatus.CREATED);
   }

   @GetMapping({"/allproductsuppliers"})
   public List<ProductSupplierVO2> getAllProductSuppliers() {
      List<ProductSupplier> productSuppliers = this.productSupplierService.getAllProductSupplier();
      List<ProductSupplierVO2> productSupplierVOs = new ArrayList();
      Iterator var3 = productSuppliers.iterator();

      while(var3.hasNext()) {
         ProductSupplier ps = (ProductSupplier)var3.next();
         ProductSupplierVO2 vo = new ProductSupplierVO2();
         vo.setSupplierid(ps.getSupplier().getSupplierId());
         vo.setProductId(ps.getProduct().getProductId());
         List<contactVO> contactVOs = new ArrayList();
         contact contact = ps.getContact();
         contactVO contactVO = new contactVO();
         contactVO.setContactId(contact.getContactId());
         contactVO.setContactName(contact.getContactName());
         contactVO.setPhoneNumber(contact.getPhoneNumber());
         contactVO.setEmail(contact.getEmail());
         contactVOs.add(contactVO);
         vo.setContacts(contactVOs);
         productSupplierVOs.add(vo);
      }

      return productSupplierVOs;
   }

   @GetMapping({"/allproductsuppliers/{supplierID}"})
   public List<ProductSupplierVO2> getAllProductSuppliersbysup(@PathVariable int supplierID) {
      List<ProductSupplier> productSuppliers = this.productSupplierService.getAllProductSupplier();
      List<ProductSupplierVO2> productSupplierVOs = new ArrayList();
      System.out.println("supplierID :" + supplierID);
      Iterator var4 = productSuppliers.iterator();

      while(var4.hasNext()) {
         ProductSupplier ps = (ProductSupplier)var4.next();
         if (ps.getSupplier().getSupplierId() == supplierID) {
            ProductSupplierVO2 vo = new ProductSupplierVO2();
            vo.setPsid(ps.getId());
            vo.setSupplierid(ps.getSupplier().getSupplierId());
            vo.setProductId(ps.getProduct().getProductId());
            List<contactVO> contactVOs = new ArrayList();
            contact contact = ps.getContact();
            contactVO contactVO = new contactVO();
            contactVO.setContactId(contact.getContactId());
            contactVO.setContactName(contact.getContactName());
            contactVO.setPhoneNumber(contact.getPhoneNumber());
            contactVO.setEmail(contact.getEmail());
            contactVOs.add(contactVO);
            vo.setContacts(contactVOs);
            productSupplierVOs.add(vo);
         }
      }

      return productSupplierVOs;
   }

   @DeleteMapping({"/deleteps/{id}"})
   public ResponseEntity<Void> deleteProductSupplier(@PathVariable Long id) {
      this.productSupplierService.deleteProductSupplier(id);
      this.contactService.deleteContact(id);
      return new ResponseEntity(HttpStatus.NO_CONTENT);
   }

   @GetMapping({"/{supplierId}"})
   public ResponseEntity<List<ProductSupplier>> getProductSuppliersBySupplierId(@PathVariable Long supplierId) {
      List<ProductSupplier> productSuppliers = this.productSupplierService.findAllProductSuppliersbySuppliers(supplierId);
      return productSuppliers.isEmpty() ? new ResponseEntity(HttpStatus.NO_CONTENT) : new ResponseEntity(productSuppliers, HttpStatus.OK);
   }

   @PostMapping({"/createps"})
   public ResponseEntity<ProductSupplierVO> createProductSupplier2(@RequestBody ProductSupplierVO ProductSupplierVO) {
      List<contact> contactsNode = ProductSupplierVO.getContacts();
      Iterator var3 = contactsNode.iterator();

      while(var3.hasNext()) {
         contact contactNode = (contact)var3.next();
         contactNode.setSupplier(ProductSupplierVO.getSupplier());
         contact contact = this.contactService.save(contactNode);
         ProductSupplier productSupplier = new ProductSupplier();
         productSupplier.setContact(contact);
         productSupplier.setProduct(ProductSupplierVO.getProduct());
         productSupplier.setSupplier(ProductSupplierVO.getSupplier());
         this.productSupplierService.createProductSupplier(productSupplier);
      }

      return new ResponseEntity(ProductSupplierVO, HttpStatus.CREATED);
   }

   @GetMapping({"/getAllProductsps"})
   public List<ProductDTO> getAllProductsps() {
      return this.productSupplierService.getAllProductsps();
   }
}