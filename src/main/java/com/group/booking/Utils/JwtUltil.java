package com.group.booking.Utils;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.group.booking.Interfaces.Addons.JWTImplement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUltil implements JWTImplement{
    private static final String JWT_SECRET = "booking";
    private static final Long TOKEN_EXPIRATION = TimeUltil.getCurrentTimeMillis()+TimeUltil.getThreeDayMillis();
    private static final Long Verify_EXPIRATION = TimeUltil.getCurrentTimeMillis()+TimeUltil.getTenMinuteMillis();

    @Override
    public String generateAccessToken(String subject) {
        // Tạo chuỗi json web token từ subject.
        return Jwts.builder()
            .setSubject(subject)
                .setIssuedAt(new Date())
                    .setExpiration(new Date(TOKEN_EXPIRATION))
                        .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                            .compact();
    }

    @Override
    public String generateVerify(String subject) {
        // Tạo chuỗi json web token từ subject.
        return Jwts.builder()
            .setSubject(subject)
                .setIssuedAt(new Date())
                    .setExpiration(new Date(Verify_EXPIRATION))
                        .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                            .compact();
    }

    @Override
    public String getSubject(String jwt) {
        Claims claims = Jwts.parser()
            .setSigningKey(JWT_SECRET)
                .parseClaimsJws(jwt)
                    .getBody();
        return claims.getSubject();
    }

    @Override
    public String validateJWT(String jwt) {
        try {
            Jwts.parser()
                .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(jwt);
        return "OK";
        } catch (ExpiredJwtException ex) {
            return "Hết thời gian truy cập";
        } catch (Exception ex) {
            return "Không có quyền truy cập";
        }
    }

    @Override
    public String validateAndGetSubject(String authorization) {
        try {
            if(StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
                Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(authorization.substring(7))
                        .getBody();
                return claims.getSubject();
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }
}