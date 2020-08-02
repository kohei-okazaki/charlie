package jp.co.joshua.common.db.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import jp.co.joshua.common.db.entity.CompositeWorkUserMt;
import jp.co.joshua.common.db.entity.WorkUserMngMt;

/**
 * 勤怠ユーザ管理マスタ Dao
 *
 * @version 1.0.0
 */
@Dao
@ConfigAutowireable
public interface WorkUserMngMtDao extends BaseDao {

    @Delete
    public int delete(WorkUserMngMt entity);

    @Update
    public int update(WorkUserMngMt entity);

    @Insert
    public int insert(WorkUserMngMt entity);

    @Select
    public long count();

    @Select
    public List<CompositeWorkUserMt> selectCompositeRegularMt(SelectOptions option);

    @Select
    public WorkUserMngMt selectBySeqLoginId(Integer seqLoginId);

    @Select
    public CompositeWorkUserMt selectActiveRegularMt(Integer seqLoginId);

}