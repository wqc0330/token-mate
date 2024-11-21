package org.mate.token.filter;

import org.mate.token.context.TokenContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional.ofNullable(request.getHeader(TOKEN_HEADER)).ifPresent(TokenContextHolder::setToken);
        TokenContextHolder.setResponse(response);
        try {
            filterChain.doFilter(request, response);
        } finally {
            TokenContextHolder.cleanAll();
        }
    }
}
