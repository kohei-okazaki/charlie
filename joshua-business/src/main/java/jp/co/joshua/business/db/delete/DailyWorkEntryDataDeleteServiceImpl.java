package jp.co.joshua.business.db.delete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.DailyWorkEntryDataDao;

/**
 * 日別勤怠登録情報削除サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class DailyWorkEntryDataDeleteServiceImpl
        implements DailyWorkEntryDataDeleteService {

    @Autowired
    private DailyWorkEntryDataDao dao;

    @Override
    public void deleteById(List<Integer> seqDailyWorkEntryDataIdList) {
        dao.deleteById(seqDailyWorkEntryDataIdList);
    }

}
