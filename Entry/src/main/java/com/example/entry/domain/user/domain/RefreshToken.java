package com.example.entry.domain.user.domain;

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
    private String refreshToken;

    @TimeToLive
    private Long refreshExpiration;

    @Builder
    public RefreshToken(String userName, String refreshToken, Long refreshExpiration) {
        this.userName = userName;
        this.refreshToken = refreshToken;
        this.refreshExpiration = refreshExpiration;
    }

    public RefreshToken (Long RefreshExp) {
        this.refreshExpiration = RefreshExp;
    }
}
