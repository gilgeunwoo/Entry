package com.example.entry.domain.submit.domain;

import com.example.entry.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APP_ID")
    private Long id;

    @Column
    private boolean apply;
    @Column
    private String username;

    @Column
    private String githubUrl;

    @Column
    private String notionUrl;

    @OneToOne(mappedBy = "application")
    private User user;

    @Builder
    public Application(Long id, boolean apply, String username, String githubUrl, String notionUrl, User user) {
        this.id = id;
        this.apply = apply;
        this.username = username;
        this.githubUrl = githubUrl;
        this.notionUrl = notionUrl;
        this.user = user;
    }

    public void setApply(boolean apply) {
        this.apply = apply;
    }
}
