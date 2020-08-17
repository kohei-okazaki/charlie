package jp.co.joshua.business.db.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.NoteUserDataDao;
import jp.co.joshua.common.db.entity.NoteUserData;

/**
 * メモユーザ情報更新サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class NoteUserDataUpdateServiceImpl implements NoteUserDataUpdateService {

    @Autowired
    private NoteUserDataDao dao;

    @Override
    public void update(NoteUserData entity) {
        dao.update(entity);
    }

}
