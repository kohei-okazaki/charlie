package jp.co.joshua.common.web.auth.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import jp.co.joshua.common.log.LoggerFactory;

/**
 * ログイン情報認証のInterceptor
 *
 * @version 1.0.0
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {

        if (handler instanceof ResourceHttpRequestHandler) {
            // 静的リソースの場合、ログイン認証を行わない
            return true;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object o = auth.getPrincipal();
        if (o instanceof LoginAuthDto) {
            LoginAuthDto dto = (LoginAuthDto) o;
            if (dto != null) {
                LoggerFactory.getLogger(this.getClass()).debugBean(dto);
            }
        }

        // TODO
        System.out.println(request.getRequestURI());
        if ("q".equals(request.getRequestURI())) {
            response.sendRedirect("/login/error");
        }

        return true;
    }

}
