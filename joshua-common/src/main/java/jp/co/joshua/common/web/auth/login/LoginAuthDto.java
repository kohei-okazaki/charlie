package jp.co.joshua.common.web.auth.login;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.joshua.common.db.entity.LoginUserData;
import jp.co.joshua.common.log.annotation.Ignore;
import jp.co.joshua.common.util.DateUtil;

/**
 * ログイン認証情報Dtoクラス
 *
 * @version 1.0.0
 */
public class LoginAuthDto extends LoginUserData implements UserDetails {

    /** serialVersionUID */
    @Ignore
    private static final long serialVersionUID = 8880157500498766605L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(
                super.getAppAuth().getValue()));
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getSeqLoginId() == null ? null : super.getSeqLoginId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return DateUtil.isAfter(super.getPasswordExpire(),
                DateUtil.toLocalDate(DateUtil.getSysDate()), true);
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
