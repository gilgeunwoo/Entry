package com.example.entry.domain.submit.domain.repository;

import com.example.entry.domain.submit.domain.Application;
import com.example.entry.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByUser(User user);
}
