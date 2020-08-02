package jp.co.joshua.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.MailUserDataDao;
import jp.co.joshua.common.db.entity.MailUserData;

/**
 * メールユーザ情報サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class MailUserDataCreateServiceImpl implements MailUserDataCreateService {

    /** メールユーザ情報Dao */
    @Autowired
    private MailUserDataDao dao;

    @Override
    public void create(MailUserData entity) {
        dao.insert(entity);
    }

}
