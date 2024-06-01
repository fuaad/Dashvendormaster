    package com.app.CRM.Controller;

import com.app.CRM.Service.UserService;
import com.app.CRM.model.supplier;
import com.app.CRM.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
   @Autowired
   private UserService userService;

   @GetMapping({"/redirect"})
   public String redirectBasedOnRole(Model model, Authentication authentication) {
      UserDetails userDetails = (UserDetails)authentication.getPrincipal();
      user user = this.userService.getUserid(userDetails.getUsername());
      System.out.println("reached " + user.getRole());
      if (user.getRole().equals("admin")) {
         return "redirect:/dashboard";
      } else if (user.getRole().equals("supplier")) {
         System.out.println("i am supplier");
         supplier supplier = user.getSupplier();
         model.addAttribute("supplierId", supplier.getSupplierId());
         return "app";
      } else {
         return "redirect:/";
      }
   }

   @GetMapping({"/dashboard"})
   public String adminPage() {
      return "dash";
   }

   @GetMapping({"/addProduct"})
   public String addProductPage() {
      return "addProductPage";
   }

   @GetMapping({"/addSupplier"})
   public String addSupplierPage() {
      return "addSupplierPage";
   }

   @GetMapping({"/supplier-form"})
   public String showSupplierForm() {
      return "supplier-form";
   }

   @GetMapping({"/app"})
   public String index(Model model, Authentication authentication) {
      UserDetails userDetails = (UserDetails)authentication.getPrincipal();
      user user = this.userService.getUserid(userDetails.getUsername());
      supplier supplier = user.getSupplier();
      model.addAttribute("supplierId", supplier.getSupplierId());
      return "app";
   }
}