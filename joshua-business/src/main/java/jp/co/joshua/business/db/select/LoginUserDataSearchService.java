package jp.co.joshua.business.db.select;

import java.util.List;

import jp.co.joshua.common.db.entity.LoginUserData;

/**
 * ログインユーザ情報検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface LoginUserDataSearchService {

    LoginUserData selectById(Integer id);

    List<Integer> selectIdList();

}
