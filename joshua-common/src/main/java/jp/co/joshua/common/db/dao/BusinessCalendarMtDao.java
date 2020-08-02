package jp.co.joshua.common.db.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.joshua.common.db.entity.BusinessCalendarMt;

/**
 * 営業日マスタ Dao
 *
 * @version 1.0.0
 */
@Dao
@ConfigAutowireable
public interface BusinessCalendarMtDao extends BaseDao {

    @Delete
    public int delete(BusinessCalendarMt entity);

    @Update
    public int update(BusinessCalendarMt entity);

    @Insert
    public int insert(BusinessCalendarMt entity);

    @Select
    public List<BusinessCalendarMt> selectByMonth(String date);

}