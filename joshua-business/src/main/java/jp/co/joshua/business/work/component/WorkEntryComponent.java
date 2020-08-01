package jp.co.joshua.business.work.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.joshua.business.db.select.WorkUserMtSearchService;
import jp.co.joshua.common.db.entity.CompositeWorkUserMt;
import jp.co.joshua.common.db.entity.WorkUserMt;
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
    private WorkUserMtSearchService workUserMtSearchService;

    public CompositeWorkUserMt getActiveRegularMtBySeqLoginId(Integer seqLoginId)
            throws AppException {

        CompositeWorkUserMt regularMt = workUserMtSearchService
                .selectByLoginIdAndMaxWorkUserMtId(seqLoginId);
        if (regularMt == null) {
            throw new AppException("このユーザに定時情報または勤怠ユーザマスタが設定されていません seqLoginId="
                    + seqLoginId);
        }
        return regularMt;
    }

    public WorkUserMt getActiveWorkUserMtBySeqLoginId(Integer seqLoginId) {
        return workUserMtSearchService.selectActiveBySeqLoginId(seqLoginId);
    }

}
