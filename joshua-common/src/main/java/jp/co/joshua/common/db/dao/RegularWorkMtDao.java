package jp.co.joshua.common.db.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import jp.co.joshua.common.db.entity.RegularWorkMt;

/**
 * 定時情報マスタ Dao
 *
 * @version 1.0.0
 */
@Dao
@ConfigAutowireable
public interface RegularWorkMtDao extends BaseDao {

    @Delete
    public int delete(RegularWorkMt entity);

    @Update
    public int update(RegularWorkMt entity);

    @Insert
    public int insert(RegularWorkMt entity);

    @Select
    public long count();

    @Select
    public List<RegularWorkMt> selectAll();

    @Select
    public List<RegularWorkMt> selectAll(SelectOptions option);

    @Select
    public RegularWorkMt selectById(Integer seqRegularWorkMtId);

}