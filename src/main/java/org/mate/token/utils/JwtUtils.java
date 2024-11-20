package org.mate.token.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * JwtUtils.
 *
 * @author liujinfeng
 */
public class JwtUtils {
    private static final String ISSUER = "TOKEN_MATE";

    public static String createToken(String subject, Long expirationTime, String secret) {
        return JWT.create()
                .withSubject(subject)
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));
    }

    public static String parseToken(String token, String secret) {
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getSubject();
    }
}
