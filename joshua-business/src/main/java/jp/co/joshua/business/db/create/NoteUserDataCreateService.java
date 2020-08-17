package jp.co.joshua.business.db.create;

import jp.co.joshua.common.db.entity.NoteUserData;

/**
 * メモユーザ情報作成サービスインターフェース
 *
 * @version 1.0.0
 */
public interface NoteUserDataCreateService {

    void create(NoteUserData note);

}
