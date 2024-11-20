package org.mate.token.context;

import javax.servlet.ServletResponse;
import java.util.Optional;

/**
 * TokenContextHolder.
 *
 * @author liujinfeng
 */
public class TokenContextHolder {
    private static final InheritableThreadLocal<TokenContext> TOKEN_LOCAL = new InheritableThreadLocal<>();
    private static final InheritableThreadLocal<ServletResponse> RESPONSE_LOCAL  = new InheritableThreadLocal<>();

    public static TokenContext getTokenContext() {
        return TOKEN_LOCAL.get();
    }

    public static String getToken() {
        return Optional.ofNullable(getTokenContext()).orElseThrow().token();
    }

    public static void setToken(String token) {
        TOKEN_LOCAL.set(new TokenContext(token));
    }

    public static void setResponseLocal(ServletResponse servletResponse) {
        RESPONSE_LOCAL.set(servletResponse);
    }

    public static void clean() {
        TOKEN_LOCAL.remove();
    }
}
