package jp.co.joshua.business.db.select;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.BusinessCalendarMtDao;
import jp.co.joshua.common.db.entity.BusinessCalendarMt;
import jp.co.joshua.common.util.DateUtil;
import jp.co.joshua.common.util.DateUtil.DateFormatType;

/**
 * 営業日マスタ検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class BusinessCalendarMtSearchServiceImpl
        implements BusinessCalendarMtSearchService {

    @Autowired
    private BusinessCalendarMtDao dao;

    @Override
    public List<BusinessCalendarMt> selectByMonth(LocalDate date) {
        return dao
                .selectByMonth(DateUtil.toString(date, DateFormatType.YYYYMM_NOSEP));
    }

}
