  package com.app.CRM.Service;

import com.app.CRM.Repo.ContactRepository;
import com.app.CRM.model.contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
   @Autowired
   private ContactRepository contactRepository;

   public contact save(contact contact) {
      return (contact)this.contactRepository.save(contact);
   }

   public void deleteContact(Long id) {
      this.contactRepository.deleteById(id);
   }
}
    