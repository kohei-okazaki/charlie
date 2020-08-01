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
import jp.co.joshua.common.db.entity.WorkUserMt;

/**
 * 勤怠ユーザマスタ Dao
 *
 * @version 1.0.0
 */
@Dao
@ConfigAutowireable
public interface WorkUserMtDao extends BaseDao {

    @Delete
    public int delete(WorkUserMt entity);

    @Update
    public int update(WorkUserMt entity);

    @Insert
    public int insert(WorkUserMt entity);

    @Select
    public int count();

    @Select
    public List<CompositeWorkUserMt> selectCompositeRegularMt();

    @Select
    public List<CompositeWorkUserMt> selectCompositeRegularMt(SelectOptions option);

    @Select
    public WorkUserMt selectById(Integer seqWorkUserMtId);

    @Select
    public CompositeWorkUserMt selectByLoginIdAndMaxWorkUserMtId(Integer seqLoginId);

}