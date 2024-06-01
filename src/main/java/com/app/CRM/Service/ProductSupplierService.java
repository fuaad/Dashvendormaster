  package com.app.CRM.Service;

import com.app.CRM.Repo.ProductSupplierRepository;
import com.app.CRM.VO.ContactDTO;
import com.app.CRM.VO.ProductDTO;
import com.app.CRM.VO.SupplierDTO;
import com.app.CRM.model.ProductSupplier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSupplierService {
   @Autowired
   private ProductSupplierRepository productSupplierRepository;

   public ProductSupplier createProductSupplier(ProductSupplier productSupplier) {
      return (ProductSupplier)this.productSupplierRepository.save(productSupplier);
   }

   public List<ProductSupplier> getAllProductSupplier() {
      return this.productSupplierRepository.findAll();
   }

   public void deleteProductSupplier(Long id) {
      this.productSupplierRepository.deleteById(id);
   }

   public List<ProductSupplier> findAllProductSuppliersbySuppliers(Long supplierId) {
      return this.productSupplierRepository.findAllBySupplierSupplierId(supplierId);
   }

   public ProductDTO convertToProductDTO(ProductSupplier productSupplier) {
      ProductDTO productDTO = new ProductDTO();
      productDTO.setProductName(productSupplier.getProduct().getProductName());
      List<SupplierDTO> supplierDTOs = new ArrayList();
      SupplierDTO supplierDTO = new SupplierDTO();
      supplierDTO.setName(productSupplier.getSupplier().getSupplierName());
      List<ContactDTO> contactDTOs = new ArrayList();
      ContactDTO contactDTO = new ContactDTO();
      contactDTO.setName(productSupplier.getContact().getContactName());
      contactDTO.setPhone(productSupplier.getContact().getPhoneNumber());
      contactDTO.setEmail(productSupplier.getContact().getEmail());
      contactDTOs.add(contactDTO);
      supplierDTO.setContacts(contactDTOs);
      supplierDTOs.add(supplierDTO);
      productDTO.setSuppliers(supplierDTOs);
      return productDTO;
   }

   public List<ProductDTO> getAllProductsps() {
      List<ProductSupplier> productSuppliers = this.productSupplierRepository.findAll();
      List<ProductDTO> productDTOs = new ArrayList();
      Iterator var3 = productSuppliers.iterator();

      while(var3.hasNext()) {
         ProductSupplier productSupplier = (ProductSupplier)var3.next();
         ProductDTO productDTO = this.convertToProductDTO(productSupplier);
         productDTOs.add(productDTO);
      }

      return productDTOs;
   }
}