package jp.co.joshua.business.db.select;

import java.time.LocalDate;
import java.util.List;

import jp.co.joshua.common.db.entity.BusinessCalendarMt;

/**
 * 営業日マスタ検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface BusinessCalendarMtSearchService {

    List<BusinessCalendarMt> selectByMonth(LocalDate date);
}
