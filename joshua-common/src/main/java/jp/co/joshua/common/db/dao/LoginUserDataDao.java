package jp.co.joshua.common.db.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import jp.co.joshua.common.db.entity.CompositeLoginPrivateData;
import jp.co.joshua.common.db.entity.LoginUserData;

/**
 * ログインユーザ情報 Dao
 *
 * @version 1.0.0
 */
@Dao
@ConfigAutowireable
public interface LoginUserDataDao extends BaseDao {

    @Delete
    public int delete(LoginUserData entity);

    @Update
    public int update(LoginUserData entity);

    @Insert
    public int insert(LoginUserData entity);

    @Select
    public LoginUserData selectById(Integer id);

    @Select
    public List<Integer> selectIdList();

    @Select
    public List<CompositeLoginPrivateData> selectLoginDataJoinPrivate(
            SelectOptions option);

    @Select
    public long count();

}