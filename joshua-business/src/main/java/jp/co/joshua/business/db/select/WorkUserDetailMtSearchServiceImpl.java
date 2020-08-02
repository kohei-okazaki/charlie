package jp.co.joshua.business.db.select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.WorkUserDetailMtDao;

/**
 * 勤怠ユーザ詳細マスタ検索サービス実装クラス
 * 
 * @version 1.0.0
 */
@Service
public class WorkUserDetailMtSearchServiceImpl implements WorkUserDetailMtSearchService {

    @Autowired
    private WorkUserDetailMtDao dao;

    @Override
    public long count() {
        return dao.count();
    }

}
