package com.empapp.repository;

import com.empapp.model.Sessions;
import com.empapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionsRepository extends JpaRepository<Sessions, Long> {

    List<Sessions> findByUser(Users user);

    void deleteByUser(Users user);
}
