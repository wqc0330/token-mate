package org.mate.token.context;

import org.mate.token.utils.JwtUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * TokenContextHolder.
 *
 * @author liujinfeng
 */
public class TokenContextHolder {
    private static final InheritableThreadLocal<TokenContext> TOKEN_LOCAL = new InheritableThreadLocal<>();
    private static final InheritableThreadLocal<HttpServletResponse> RESPONSE_LOCAL  = new InheritableThreadLocal<>();

    public static TokenContext getTokenContext() {
        return TOKEN_LOCAL.get();
    }

    public static String getToken() {
        return Optional.ofNullable(getTokenContext()).orElseThrow(RuntimeException::new).token();
    }

    public static Optional<HttpServletResponse> getHttpServletResponse() {
        return Optional.ofNullable(RESPONSE_LOCAL.get());
    }

    public static void setToken(String token) {
        TOKEN_LOCAL.set(new TokenContext(token, JwtUtils.parseToken(token, "token_mate")));
    }

    public static void setResponse(HttpServletResponse servletResponse) {
        RESPONSE_LOCAL.set(servletResponse);
    }

    public static void cleanAll() {
        TOKEN_LOCAL.remove();
        RESPONSE_LOCAL.remove();
    }
}
