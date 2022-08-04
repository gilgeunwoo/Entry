package com.example.entry.domain.user.domain;


import com.example.entry.domain.auth.domain.types.Role;
import com.example.entry.domain.submit.domain.Application;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USER_ID")
    private Long id;

    @Column
    private String username;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private Role role;



    @Builder
    public User(Long id, String username, String email, String password, Role role, Application application) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User update(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        return this;
    }
}
