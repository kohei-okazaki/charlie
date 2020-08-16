package jp.co.joshua.common.web.auth.login;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Spring Securityの認証情報のラッパークラス
 *
 * @version 1.0.0
 */
@Component
public class SecurityContextWrapper {

    /**
     * {@linkplain LoginAuthDto}を返す
     *
     * @return LoginAuthDto
     */
    public Optional<LoginAuthDto> getLoginAuthDto() {
        LoginAuthDto principal = (LoginAuthDto) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return Optional.ofNullable(principal);
    }

}
