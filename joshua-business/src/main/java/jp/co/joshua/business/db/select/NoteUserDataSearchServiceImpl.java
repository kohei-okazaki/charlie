package jp.co.joshua.business.db.select;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.NoteUserDataDao;
import jp.co.joshua.common.db.entity.NoteUserData;
import jp.co.joshua.common.db.util.DomaUtil;

/**
 * メモユーザ情報検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class NoteUserDataSearchServiceImpl implements NoteUserDataSearchService {

    @Autowired
    private NoteUserDataDao dao;

    @Override
    public List<NoteUserData> selectBySeqLoginIdAndLikeTitle(Integer seqLoginId,
            String title, Pageable pageable) {
        SelectOptions option = DomaUtil.createSelectOptions(pageable, false);
        return dao.selectBySeqLoginIdAndLikeTitle(seqLoginId, title, option);
    }

    @Override
    public long countBySeqLoginIdAndLikeTitle(Integer seqLoginId, String title) {
        return dao.countBySeqLoginId(seqLoginId, title);
    }

}
