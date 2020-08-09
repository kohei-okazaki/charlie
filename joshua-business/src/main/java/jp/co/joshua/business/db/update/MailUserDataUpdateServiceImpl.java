package jp.co.joshua.business.db.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.MailUserDataDao;
import jp.co.joshua.common.db.entity.MailUserData;

/**
 * メールユーザ情報更新サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class MailUserDataUpdateServiceImpl implements MailUserDataUpdateService {

    @Autowired
    private MailUserDataDao dao;

    @Override
    public void update(MailUserData entity) {
        dao.update(entity);
    }

}
