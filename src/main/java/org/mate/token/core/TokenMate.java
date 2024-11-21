package org.mate.token.core;

import org.mate.token.context.TokenContextHolder;
import org.mate.token.utils.JwtUtils;

/**
 * TokenMate.
 *
 * @author liujinfeng
 */
public class TokenMate {
    public static String TOKEN_HEADER = "Token";
    public static String SECRET = "token_mate";

    public static void login(String subject) {
        TokenContextHolder.getHttpServletResponse().ifPresent(httpServletResponse -> {
            String token = JwtUtils.createToken(subject, 30000L, SECRET);
            httpServletResponse.addHeader(TOKEN_HEADER, token);
            TokenContextHolder.setToken(token);
        });
    }

    public static void logout(String subject) {
        TokenContextHolder.getHttpServletResponse().ifPresent(httpServletResponse -> {
            String token = JwtUtils.createToken(subject, 0L, SECRET);
            httpServletResponse.addHeader(TOKEN_HEADER, token);
        });
    }

    public static boolean verify(String subject, String token) {
        return JwtUtils.parseToken(token, SECRET).equals(subject);
    }
}
