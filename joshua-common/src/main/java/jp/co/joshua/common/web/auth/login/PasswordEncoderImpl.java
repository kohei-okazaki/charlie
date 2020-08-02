package jp.co.joshua.common.web.auth.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.algorithm.Sha256HashEncoder;
import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;

/**
 * パスワードエンコードクラス<br>
 * SHA-256でのハッシュ化を行い、SpringSecurityで利用する
 *
 * @version 1.0.0
 */
@Service("passwordEncoderImpl")
public class PasswordEncoderImpl implements PasswordEncoder {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(PasswordEncoderImpl.class);
    /** ハッシュ関数 */
    @Autowired
    private Sha256HashEncoder encoder;

    @Override
    public String encode(CharSequence rawPassword) {
        LOG.debug(rawPassword.toString());
        return encoder.encode(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return this.encode(rawPassword).equals(encodedPassword);
    }

}
