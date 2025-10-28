package com.empapp.repository;

import com.empapp.model.Contacts;
import com.empapp.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContactsRepository extends JpaRepository<Contacts, Long> {
    List<Contacts> findByEmployee(Employees employee);
}
