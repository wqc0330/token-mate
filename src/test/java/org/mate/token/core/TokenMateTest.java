package org.mate.token.core;

import org.junit.jupiter.api.Test;
import org.mate.token.context.TokenContextHolder;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author 微信号:java0305
 */
class TokenMateTest {

    @Test
    void testTokenMate() {
        TokenContextHolder.setResponse(new MockHttpServletResponse());

        String subject = "123";
        TokenMate.login(subject);
        TokenMate.verify(subject, TokenContextHolder.getToken());
    }
}