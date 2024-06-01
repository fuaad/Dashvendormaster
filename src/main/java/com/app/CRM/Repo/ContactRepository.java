    package com.app.CRM.Repo;

import com.app.CRM.model.contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<contact, Long> {
}
    