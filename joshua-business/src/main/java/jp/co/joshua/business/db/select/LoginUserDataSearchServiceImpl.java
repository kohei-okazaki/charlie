package jp.co.joshua.business.db.select;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.LoginUserDataDao;
import jp.co.joshua.common.db.entity.LoginUserData;

/**
 * ログインユーザ情報検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class LoginUserDataSearchServiceImpl implements LoginUserDataSearchService {

    @Autowired
    private LoginUserDataDao dao;

    @Override
    public LoginUserData selectById(Integer id) {
        return dao.selectById(id);
    }

    @Override
    public List<Integer> selectIdList() {
        return dao.selectIdList();
    }

}
