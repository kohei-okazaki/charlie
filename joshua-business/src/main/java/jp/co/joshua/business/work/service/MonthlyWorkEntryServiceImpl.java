package jp.co.joshua.business.work.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jp.co.joshua.business.db.create.DailyWorkEntryDataCreateService;
import jp.co.joshua.business.db.select.DailyWorkEntryDataSearchService;
import jp.co.joshua.business.db.update.DailyWorkEntryDataUpdateService;
import jp.co.joshua.business.work.component.WorkEntryComponent;
import jp.co.joshua.business.work.dto.DailyWorkEntryDataDto;
import jp.co.joshua.common.db.entity.DailyWorkEntryData;
import jp.co.joshua.common.db.entity.WorkUserMngMt;
import jp.co.joshua.common.db.type.WorkAuthStatus;
import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;
import jp.co.joshua.common.util.DateUtil;

/**
 * 当月勤怠登録画面サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class MonthlyWorkEntryServiceImpl implements MonthlyWorkEntryService {

    /** LOG */
    private static final Logger LOG = LoggerFactory
            .getLogger(MonthlyWorkEntryServiceImpl.class);

    /** トランザクション管理クラス */
    @Autowired
    private PlatformTransactionManager transactionManager;
    /** トランザクションクラス */
    @Autowired
    private DefaultTransactionDefinition defaultTransactionDefinition;
    /** 勤怠関連Component */
    @Autowired
    private WorkEntryComponent workEntryComponent;
    /** 日別勤怠登録情報検索サービス */
    @Autowired
    private DailyWorkEntryDataSearchService dailyWorkEntryDataSearchService;
    /** 日別勤怠登録情報登録サービス */
    @Autowired
    private DailyWorkEntryDataCreateService dailyWorkEntryDataCreateService;
    /** 日別勤怠登録情報更新サービス */
    @Autowired
    private DailyWorkEntryDataUpdateService dailyWorkEntryDataUpdateService;

    @Override
    public void executeEntry(LocalDate targetDate, Integer seqLoginId,
            List<DailyWorkEntryDataDto> dtoList) {

        WorkUserMngMt mngMt = workEntryComponent
                .getActiveWorkUserMtBySeqLoginId(seqLoginId);

        TransactionStatus status = transactionManager
                .getTransaction(defaultTransactionDefinition);

        // 既に登録された日別勤怠登録情報を検索
        List<DailyWorkEntryData> dailyWorkEntryDataList = dailyWorkEntryDataSearchService
                .selectDailyMtListByDate(targetDate, mngMt.getSeqWorkUserMngMtId());

        try {
            for (DailyWorkEntryDataDto dailyWorkEntryDataDto : dtoList) {
                boolean isInsert = true;

                LocalDate date = DateUtil.toLocalDate(dailyWorkEntryDataDto.getBegin());
                for (DailyWorkEntryData entity : dailyWorkEntryDataList) {

                    if (DateUtil.toLocalDate(entity.getBegin()).equals(date)) {
                        // 更新処理
                        entity.setBegin(dailyWorkEntryDataDto.getBegin());
                        entity.setEnd(dailyWorkEntryDataDto.getEnd());
                        entity.setActualTime(dailyWorkEntryDataDto.getActualTime());
                        // TODO 残業時間
                        entity.setOverTime(LocalTime.MIN);
                        // TODO 深夜残業時間
                        entity.setLateOverTime(LocalTime.MIN);
                        entity.setHolidayWorkTime(
                                dailyWorkEntryDataDto.getHolidayWorkTime());
                        entity.setWorkAuthStatus(WorkAuthStatus.STILL);
                        dailyWorkEntryDataUpdateService.update(entity);
                        isInsert = false;
                        break;
                    }
                }

                if (isInsert) {
                    // 登録処理
                    DailyWorkEntryData entity = new DailyWorkEntryData();
                    entity.setSeqWorkUserMngMtId(mngMt.getSeqWorkUserMngMtId());
                    entity.setBegin(dailyWorkEntryDataDto.getBegin());
                    entity.setEnd(dailyWorkEntryDataDto.getEnd());
                    entity.setActualTime(dailyWorkEntryDataDto.getActualTime());
                    // TODO 残業時間
                    entity.setOverTime(LocalTime.MIN);
                    // TODO 深夜残業時間
                    entity.setLateOverTime(LocalTime.MIN);
                    entity.setHolidayWorkTime(dailyWorkEntryDataDto.getHolidayWorkTime());
                    entity.setWorkAuthStatus(WorkAuthStatus.STILL);
                    dailyWorkEntryDataCreateService.create(entity);
                }
            }

        } catch (Exception e) {
            transactionManager.rollback(status);
            LOG.error("日次勤怠登録情報の登録処理をrollbackしました", e);
            throw e;
        }
    }

}
