package jp.co.joshua.common.db.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.joshua.common.db.entity.MailUserData;

/**
 * メールユーザ情報 Dao
 *
 * @version 1.0.0
 */
@Dao
@ConfigAutowireable
public interface MailUserDataDao extends BaseDao {

    @Delete
    public int delete(MailUserData entity);

    @Update
    public int update(MailUserData entity);

    @Insert
    public int insert(MailUserData entity);

    @Select
    public MailUserData selectBySeqLoginId(Integer seqLoginId);

}