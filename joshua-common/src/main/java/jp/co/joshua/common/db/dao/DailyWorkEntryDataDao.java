package jp.co.joshua.common.db.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.joshua.common.db.entity.CompositeDailyWorkEntryData;
import jp.co.joshua.common.db.entity.DailyWorkEntryData;

/**
 * 日別勤怠登録情報 Dao
 *
 * @version 1.0.0
 */
@Dao
@ConfigAutowireable
public interface DailyWorkEntryDataDao extends BaseDao {

    @Delete
    public int delete(DailyWorkEntryData entity);

    @Update
    public int update(DailyWorkEntryData entity);

    @Insert
    public int insert(DailyWorkEntryData entity);

    @Select
    List<CompositeDailyWorkEntryData> selectDailyMtAndCalendarMtByDate(String date,
            Integer seqWorkUserMngMtId);

    @Select
    public List<DailyWorkEntryData> selectDailyMtListByDate(String date,
            Integer seqWorkUserMngMtId);

}