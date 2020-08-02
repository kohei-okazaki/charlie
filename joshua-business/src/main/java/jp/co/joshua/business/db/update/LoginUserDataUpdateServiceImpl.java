package jp.co.joshua.business.db.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.algorithm.Sha256HashEncoder;
import jp.co.joshua.common.db.dao.LoginUserDataDao;
import jp.co.joshua.common.db.entity.LoginUserData;
import jp.co.joshua.common.util.DateUtil;

/**
 * ログインユーザ情報更新サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class LoginUserDataUpdateServiceImpl implements LoginUserDataUpdateService {

    /** ログインユーザ情報Dao */
    @Autowired
    private LoginUserDataDao dao;
    /** SHA256変換クラス */
    @Autowired
    private Sha256HashEncoder sha256HashEncoder;

    @Override
    public void update(LoginUserData entity) {

        // パスワード(SHA-256でハッシュ化)
        entity.setPassword(sha256HashEncoder.encode(entity.getPassword()));
        // パスワード有効期限(システム日時 + 12ヶ月)
        entity.setPasswordExpire(
                DateUtil.addMonth(DateUtil.toLocalDate(DateUtil.getSysDate()), 12));
        dao.update(entity);
    }
}
