package com.example.entry.domain.admin.domain.repository;

import com.example.entry.domain.admin.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByAdminId(String adminId);
}
