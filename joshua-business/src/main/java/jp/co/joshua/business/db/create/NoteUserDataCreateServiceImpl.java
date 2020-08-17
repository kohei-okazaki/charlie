package jp.co.joshua.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.NoteUserDataDao;
import jp.co.joshua.common.db.entity.NoteUserData;

/**
 * メモユーザ情報作成サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class NoteUserDataCreateServiceImpl implements NoteUserDataCreateService {

    @Autowired
    private NoteUserDataDao dao;

    @Override
    public void create(NoteUserData entity) {
        dao.insert(entity);
    }

}
