package org.mate.token.filter;

import org.mate.token.context.TokenContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static org.mate.token.core.TokenMate.TOKEN_HEADER;

/**
 * TokenFilter.
 *
 * @author liujinfeng
 */
public class TokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        Optional.ofNullable(request.getHeader(TOKEN_HEADER)).ifPresent(TokenContextHolder::setToken);
        TokenContextHolder.setResponse(response);
        try {
            filterChain.doFilter(request, response);
        } finally {
            TokenContextHolder.cleanAll();
        }
    }
}
