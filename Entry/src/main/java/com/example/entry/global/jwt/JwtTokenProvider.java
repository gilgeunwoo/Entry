package com.example.entry.global.jwt;

import com.example.entry.domain.user.controller.dto.response.TokenResponse;
import com.example.entry.domain.user.domain.RefreshToken;
import com.example.entry.domain.user.domain.Role;
import com.example.entry.domain.user.domain.repository.RefreshTokenRepository;
import com.example.entry.global.exception.ExpiredRefreshTokenException;
import com.example.entry.global.exception.IncorrectTokenException;
import com.example.entry.global.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${secretKey}")
    private String secretKey;


    //@Value("${accessTime}")
    private long accessTokenValidTime = 30 * 60 * 1000L;

    private long refreshTokenValidTime = 60 * 60 * 1000L;

    private final UserDetailsService userDetailsService;

    private final RefreshTokenRepository refreshTokenRepository;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public TokenResponse createTokens(String userPK, String email) {

        String accessToken = createToken(userPK, Role.ROLE_USER, "access", accessTokenValidTime, email);
        String refreshToken = createToken(userPK, Role.ROLE_USER, "refresh", refreshTokenValidTime, email);

        refreshTokenRepository.save(RefreshToken.builder()
                .refreshToken(refreshToken)
                .userName(userPK)
                .refreshExpiration(refreshTokenValidTime)
                .build());
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String createToken(String userPK, Role roles, String type, Long exp, String email) {
        Claims claims = Jwts.claims().setSubject(userPK);
        claims.put("type", type);
        claims.put("roles", roles);
        claims.put("email", email);
        Date now =new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + exp))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPK(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPK(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Claims parseToken(String token) {
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getSecretKey() {
        return secretKey;
    }

    public boolean isRefreshToken(String refreshToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(refreshToken)
                    .getBody();

            return claims.get("type").equals("refresh") && !claims.getExpiration().before(new Date());
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            throw IncorrectTokenException.EXCEPTION;
        } catch (ExpiredJwtException e) {
            throw ExpiredRefreshTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }
}
