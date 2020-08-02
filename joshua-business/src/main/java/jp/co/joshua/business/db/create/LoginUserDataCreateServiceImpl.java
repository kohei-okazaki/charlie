package jp.co.joshua.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.algorithm.Sha256HashEncoder;
import jp.co.joshua.common.db.dao.LoginUserDataDao;
import jp.co.joshua.common.db.entity.LoginUserData;
import jp.co.joshua.common.util.DateUtil;

/**
 * ログインユーザ情報作成サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class LoginUserDataCreateServiceImpl implements LoginUserDataCreateService {

    @Autowired
    private LoginUserDataDao dao;

    @Autowired
    private Sha256HashEncoder sha256HashEncoder;

    @Override
    public void create(LoginUserData entity) {

        // パスワード(SHA-256でハッシュ化)
        entity.setPassword(sha256HashEncoder.encode(entity.getPassword()));
        // パスワード有効期限(システム日時 + 12ヶ月)
        entity.setPasswordExpire(
                DateUtil.addMonth(DateUtil.toLocalDate(DateUtil.getSysDate()), 12));
        dao.insert(entity);
    }

}
