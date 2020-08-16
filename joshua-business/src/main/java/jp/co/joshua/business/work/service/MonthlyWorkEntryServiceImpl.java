package jp.co.joshua.business.work.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jp.co.joshua.business.db.create.DailyWorkEntryDataCreateService;
import jp.co.joshua.business.db.delete.DailyWorkEntryDataDeleteService;
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
    /** 日別勤怠登録情報削除サービス */
    @Autowired
    private DailyWorkEntryDataDeleteService dailyWorkEntryDataDeleteService;

    @Override
    public void executeEntry(LocalDate targetDate, Integer seqLoginId,
            List<DailyWorkEntryDataDto> dtoList, List<Integer> deleteIdList) {

        WorkUserMngMt mngMt = workEntryComponent
                .getActiveWorkUserMtBySeqLoginId(seqLoginId);

        // トランザクション開始
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
                        updateDailyWorkEntryData(entity, dailyWorkEntryDataDto);
                        isInsert = false;
                        break;
                    }
                }

                if (isInsert) {
                    // 登録処理
                    createDailyWorkEntryData(mngMt.getSeqWorkUserMngMtId(),
                            dailyWorkEntryDataDto);
                }
            }

            dailyWorkEntryDataDeleteService.deleteById(deleteIdList);

        } catch (Exception e) {
            transactionManager.rollback(status);
            LOG.error("日次勤怠登録情報の登録処理をrollbackしました", e);
            throw e;
        }
    }

    /**
     * 日別勤怠登録情報を更新する
     *
     * @param entity
     *            日別勤怠登録情報
     * @param dto
     *            日別勤怠登録情報Dto
     */
    private void updateDailyWorkEntryData(DailyWorkEntryData entity,
            DailyWorkEntryDataDto dto) {

        entity.setBegin(dto.getBegin());
        entity.setEnd(dto.getEnd());
        entity.setActualTime(dto.getActualTime());
        entity.setOverTime(dto.getOverTime());
        entity.setLateOverTime(dto.getLateOverTime());
        entity.setHolidayWorkTime(dto.getHolidayWorkTime());
        entity.setWorkAuthStatus(WorkAuthStatus.STILL);

        dailyWorkEntryDataUpdateService.update(entity);
    }

    /**
     * 日別勤怠登録情報を新規登録する
     *
     * @param seqWorkUserMngMtId
     *            勤怠ユーザ管理マスタID
     * @param dto
     *            日別勤怠登録情報Dto
     */
    private void createDailyWorkEntryData(Integer seqWorkUserMngMtId,
            DailyWorkEntryDataDto dto) {

        DailyWorkEntryData entity = new DailyWorkEntryData();
        entity.setSeqWorkUserMngMtId(seqWorkUserMngMtId);
        entity.setBegin(dto.getBegin());
        entity.setEnd(dto.getEnd());
        entity.setActualTime(dto.getActualTime());
        entity.setOverTime(dto.getOverTime());
        entity.setLateOverTime(dto.getLateOverTime());
        entity.setHolidayWorkTime(dto.getHolidayWorkTime());
        entity.setWorkAuthStatus(WorkAuthStatus.STILL);

        dailyWorkEntryDataCreateService.create(entity);
    }

}
