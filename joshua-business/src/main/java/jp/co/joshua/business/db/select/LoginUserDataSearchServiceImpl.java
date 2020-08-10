package jp.co.joshua.business.db.select;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.LoginUserDataDao;
import jp.co.joshua.common.db.entity.CompositeLoginPrivateData;
import jp.co.joshua.common.db.entity.LoginUserData;
import jp.co.joshua.common.db.util.DomaUtil;

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

    @Override
    public List<CompositeLoginPrivateData> selectLoginDataJoinPrivate(Pageable pageable) {
        SelectOptions option = DomaUtil.createSelectOptions(pageable, false);
        return dao.selectLoginDataJoinPrivate(option);
    }

    @Override
    public long count() {
        return dao.count();
    }

}
