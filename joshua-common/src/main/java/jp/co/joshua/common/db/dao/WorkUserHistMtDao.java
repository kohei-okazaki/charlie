package jp.co.joshua.common.db.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import jp.co.joshua.common.db.entity.CompositeWorkHistMt;
import jp.co.joshua.common.db.entity.WorkUserHistMt;

/**
 * 勤怠ユーザ履歴マスタ Dao
 *
 * @version 1.0.0
 */
@Dao
@ConfigAutowireable
public interface WorkUserHistMtDao extends BaseDao {

    @Delete
    public int delete(WorkUserHistMt entity);

    @Update
    public int update(WorkUserHistMt entity);

    @Insert
    public int insert(WorkUserHistMt entity);

    @Select
    public long count();

    @Select
    public List<CompositeWorkHistMt> selectAllJoinRegularMt(SelectOptions options);

}