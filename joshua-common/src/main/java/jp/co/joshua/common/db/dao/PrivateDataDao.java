package jp.co.joshua.common.db.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.joshua.common.db.entity.PrivateData;

/**
 * 個別ユーザ情報 Dao
 *
 * @version 1.0.0
 */
@Dao
@ConfigAutowireable
public interface PrivateDataDao extends BaseDao {

    @Delete
    public int delete(PrivateData entity);

    @Update
    public int update(PrivateData entity);

    @Insert
    public int insert(PrivateData entity);

    @Select
    public PrivateData selectBySeqLoginId(Integer seqLoginId);

    @Select
    public PrivateData selectById(Integer seqPrivateDataId);

}