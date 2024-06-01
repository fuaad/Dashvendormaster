package com.app.CRM.VO;

public class contactVO {
   private Long contactId;
   private String contactName;
   private String phoneNumber;
   private String email;

   public Long getContactId() {
      return this.contactId;
   }

   public void setContactId(Long contactId) {
      this.contactId = contactId;
   }

   public String getPhoneNumber() {
      return this.phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getContactName() {
      return this.contactName;
   }

   public void setContactName(String contactName) {
      this.contactName = contactName;
   }
}
    