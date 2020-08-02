package jp.co.joshua.common.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.joshua.common.web.auth.login.LoginAuthInterceptor;
import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * WebのBean定義を設定するクラス
 *
 * @version 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestTrackingInterceptor interceptor;
    @Autowired
    private LoginAuthInterceptor loginAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
        registry.addInterceptor(loginAuthInterceptor);
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
