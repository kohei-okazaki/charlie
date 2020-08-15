package jp.co.joshua.business.db.delete;

import java.util.List;

/**
 * 日別勤怠登録情報削除サービスインターフェース
 *
 * @version 1.0.0
 */
public interface DailyWorkEntryDataDeleteService {

    void deleteById(List<Integer> seqDailyWorkEntryDataIdList);

}
