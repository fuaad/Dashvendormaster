 package com.app.CRM.Service;

import com.app.CRM.Repo.UserRepository;
import com.app.CRM.model.user;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
   @Autowired
   private UserRepository userRepository;

   public user saveUser(user user) {
      return (user)this.userRepository.save(user);
   }

   public user updateUser(user user) {
      return (user)this.userRepository.save(user);
   }

   public void deleteUser(Long userId) {
      this.userRepository.deleteById(userId);
   }

   public user getUserById(Long userId) {
      return (user)this.userRepository.findById(userId).orElse(null);
   }

   public user getUserid(String username) {
      return this.userRepository.findByUsername(username);
   }

   public List<user> getAllUsers() {
      return this.userRepository.findAll();
   }

   public String usernameValidation(String username) {
      user user = this.userRepository.findByUsername(username);
      return user == null ? "true" : "false";
   }

   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      user user = this.userRepository.findByUsername(username);
      System.out.println("ROLE  : " + user.getRole());
      if (user == null) {
         throw new UsernameNotFoundException("User not found with username: " + username);
      } else {
         return User.builder().username(user.getUsername()).password("{noop}" + user.getPassword()).roles(new String[]{user.getRole()}).build();
      }
   }
   public user getUserBySupplierId(Long supplierId) {
       return userRepository.findBySupplierId(supplierId);
   }
   
}