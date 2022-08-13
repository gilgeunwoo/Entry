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
    private String field;
    @Column
    private String username;

    @Column
    private String githubUrl;

    @Column
    private String notionUrl;

    @Column
    private String ERDUrl;

    @Column
    private boolean pass;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Application(Long id, boolean apply, String field, String username, String githubUrl, String notionUrl, String erdUrl, boolean pass, User user) {
        this.id = id;
        this.apply = apply;
        this.field = field;
        this.username = username;
        this.githubUrl = githubUrl;
        this.notionUrl = notionUrl;
        ERDUrl = erdUrl;
        this.pass = pass;
        this.user = user;
    }

    public void submitAssignment(String githubUrl, String notionUrl) {
        this.githubUrl = githubUrl;
        this.notionUrl = notionUrl;
    }

    public void submitERD(String ERDUrl) {
        this.ERDUrl = ERDUrl;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
