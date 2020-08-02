package jp.co.joshua.business.work.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.joshua.business.db.select.WorkUserMngMtSearchService;
import jp.co.joshua.common.db.entity.CompositeWorkUserMt;
import jp.co.joshua.common.db.entity.WorkUserMngMt;
import jp.co.joshua.common.exception.AppException;

/**
 * 勤怠関連Componentクラス<br>
 * IDからDBを取得する等の便利メソッドを提供
 *
 * @version 1.0.0
 */
@Component
public class WorkEntryComponent {

    @Autowired
    private WorkUserMngMtSearchService mngSearchService;

    public CompositeWorkUserMt getActiveRegularMtBySeqLoginId(Integer seqLoginId)
            throws AppException {

        CompositeWorkUserMt regularMt = mngSearchService
                .selectActiveRegularMt(seqLoginId);
        if (regularMt == null) {
            throw new AppException(
                    "このユーザに勤怠ユーザ管理マスタ、勤怠ユーザ詳細マスタが設定されていません seqLoginId="
                            + seqLoginId);
        }
        return regularMt;
    }

    public WorkUserMngMt getActiveWorkUserMtBySeqLoginId(Integer seqLoginId) {
        return mngSearchService.selectBySeqLoginId(seqLoginId);
    }

}
