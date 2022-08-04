package com.example.entry.domain.admin.domain;

import com.example.entry.domain.auth.domain.types.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String adminId;

    @Column
    private String password;

    @Column
    private Role role;


    @Builder
    public Admin(Long id, String adminId, String password, Role role) {
        this.id = id;
        this.adminId = adminId;
        this.password = password;
        this.role = role;
    }
}
