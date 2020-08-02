package jp.co.joshua.common.web.auth.login;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.LoginUserDataDao;
import jp.co.joshua.common.db.entity.LoginUserData;
import jp.co.joshua.common.log.LoggerFactory;
import jp.co.joshua.common.util.StringUtil;

/**
 * ログイン認証サービス
 *
 * @version 1.0.0
 */
@Service("loginAuthService")
public class LoginAuthServiceImpl implements UserDetailsService {

    /** ログインユーザ情報検索サービス */
    @Autowired
    private LoginUserDataDao loginUserDataSearchDao;
    /** データマッピング */
    @Autowired
    private ModelMapper mapper;
    /** HttpSession情報 */
    @Autowired
    private HttpSession httpSession;

    @Override
    public LoginAuthDto loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (StringUtil.isEmpty(username)) {
            LoggerFactory.getLogger(this.getClass())
                    .warn("指定したログインIDが無効です。ログインID:" + username);
            throw new UsernameNotFoundException("指定したログインIDが無効です。ログインID:" + username);
        }

        LoginUserData entity = loginUserDataSearchDao
                .selectById(Integer.valueOf(username));

        if (entity == null) {
            LoggerFactory.getLogger(this.getClass())
                    .warn("指定したログインIDが存在しません。ログインID:" + username);
            throw new UsernameNotFoundException("指定したログインIDが存在しません。ログインID:" +
                    username);
        }
        LoginAuthDto loginAuthDto = mapper.map(entity, LoginAuthDto.class);

        httpSession.setAttribute("loginAuthDto", loginAuthDto);

        return loginAuthDto;
    }

}
