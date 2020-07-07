package org.viper75.uploadexcelfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.viper75.uploadexcelfile.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
