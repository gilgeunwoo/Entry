package com.example.entry.domain.auth.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;


@Getter
@NoArgsConstructor
@RedisHash(value = "refresh_token")
public class RefreshToken implements Serializable {

    @Id
    private String userName;

    @Indexed
    private String email;
    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long refreshExpiration;

    @Builder
    public RefreshToken(String userName, String email, String refreshToken, Long refreshExpiration) {
        this.userName = userName;
        this.email = email;
        this.refreshToken = refreshToken;
        this.refreshExpiration = refreshExpiration;
    }

    public RefreshToken update(Long RefreshExp) {
        this.refreshExpiration = RefreshExp;
        return this;
    }
}
