package com.empapp.repository;

import com.empapp.model.Notifications;
import com.empapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

    List<Notifications> findByUser(Users user);

    List<Notifications> findByUserAndIsReadFalse(Users user);
}
