package org.mate.token.core;

import org.mate.token.context.TokenContext;
import org.mate.token.context.TokenContextHolder;
import org.mate.token.utils.JwtUtils;

import javax.servlet.http.Cookie;

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
            String token = JwtUtils.createToken(subject, 30000L, "token_mate");
            httpServletResponse.addCookie(new Cookie("Token", token));
            TokenContextHolder.setToken(token);
        });
    }
}
