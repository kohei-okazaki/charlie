package jp.co.joshua.business.db.select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.MailUserDataDao;
import jp.co.joshua.common.db.entity.MailUserData;

/**
 * メールユーザ情報検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class MailUserDataSearchServiceImpl implements MailUserDataSearchService {

    @Autowired
    private MailUserDataDao dao;

    @Override
    public MailUserData selectBySeqLoginId(Integer seqLoginId) {
        return dao.selectBySeqLoginId(seqLoginId);
    }

}
