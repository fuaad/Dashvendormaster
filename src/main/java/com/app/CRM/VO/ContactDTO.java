 package com.app.CRM.VO;

public class ContactDTO {
   private String ContactName;
   private String contactPhone;
   private String email;

   public String getName() {
      return this.ContactName;
   }

   public void setName(String name) {
      this.ContactName = name;
   }

   public String getPhone() {
      return this.contactPhone;
   }

   public void setPhone(String phone) {
      this.contactPhone = phone;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
   