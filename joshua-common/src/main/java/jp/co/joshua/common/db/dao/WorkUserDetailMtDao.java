package jp.co.joshua.common.db.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.joshua.common.db.entity.WorkUserDetailMt;

/**
 * 勤怠ユーザ詳細マスタ Dao
 *
 * @version 1.0.0
 */
@Dao
@ConfigAutowireable
public interface WorkUserDetailMtDao extends BaseDao {

    @Delete
    public int delete(WorkUserDetailMt entity);

    @Update
    public int update(WorkUserDetailMt entity);

    @Insert
    public int insert(WorkUserDetailMt entity);

    @Select
    public long count();

}