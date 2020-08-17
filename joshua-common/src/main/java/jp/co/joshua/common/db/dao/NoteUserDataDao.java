package jp.co.joshua.common.db.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import jp.co.joshua.common.db.entity.NoteUserData;

/**
 * メモユーザ情報 Dao
 *
 * @version 1.0.0
 */
@Dao
@ConfigAutowireable
public interface NoteUserDataDao extends BaseDao {

    @Delete
    public int delete(NoteUserData entity);

    @Update
    public int update(NoteUserData entity);

    @Insert
    public int insert(NoteUserData entity);

    @Select
    public List<NoteUserData> selectBySeqLoginIdAndLikeTitle(Integer seqLoginId,
            String title, SelectOptions option);

    @Select
    public long countBySeqLoginIdAndLikeTitle(Integer seqLoginId, String title);

    @Select
    public NoteUserData selectById(Integer seqNoteUserDataId);

}