package jp.co.joshua.common.web.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.co.joshua.common.web.auth.login.LoginAuthServiceImpl;
import jp.co.joshua.common.web.auth.login.PasswordEncoderImpl;

/**
 * アプリの認証設定クラス
 *
 * @version 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    /** 認証処理を行わないURI */
    private static final String[] PERMIT_URIS = new String[] { "/login",
            "/login/regist", "/login/registconfirm", "/login/registprocess",
            "/login/login-error", "/css/**", "/js/**", "/webjars/**", "/actuator" };

    /** ログイン認証サービス {@linkplain LoginAuthServiceImpl} */
    @Autowired
    @Qualifier("loginAuthService")
    private UserDetailsService userService;
    /** パスワードEncoder {@linkplain PasswordEncoderImpl} */
    @Autowired
    @Qualifier("passwordEncoderImpl")
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // ログイン等の画面URIと静的ファイルを許可
                .antMatchers(PERMIT_URIS)
                .permitAll()
                .anyRequest()
                .authenticated();

        http.formLogin()
                // ログイン処理をするURL
                .loginProcessingUrl("/login/processLogin")
                // ログイン画面のURL
                .loginPage("/login")
                // 認証失敗時のURL
                .failureUrl("/login/error")
                // 認証成功時のURL
                .successForwardUrl("/login/success")
                // ログインIDのパラメータ名
                .usernameParameter("loginId")
                // パスワードのパラメータ名
                .passwordParameter("password")
                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
                .deleteCookies("SESSION", "JSESSIONID")
                // ログアウトのURL
                .logoutUrl("/login/logout")
                // ログアウト成功URL
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/webjars/**");
    }

}
