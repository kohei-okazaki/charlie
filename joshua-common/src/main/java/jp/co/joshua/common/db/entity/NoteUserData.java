package jp.co.joshua.common.db.entity;

import jp.co.joshua.common.db.entity.BaseEntity;
import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;
import org.seasar.doma.Id;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import java.lang.Integer;
import java.lang.String;

/**
 * メモユーザ情報 Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class NoteUserData extends BaseEntity {

    /** メモユーザ情報ID */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer seqNoteUserDataId;
    /** ログインID */
    private Integer seqLoginId;
    /** 件名 */
    private String title;
    /** S3キー */
    private String s3Key;

    public void setSeqNoteUserDataId(Integer seqNoteUserDataId) {
        this.seqNoteUserDataId = seqNoteUserDataId;
    }

    public Integer getSeqNoteUserDataId() {
        return seqNoteUserDataId;
    }

    public void setSeqLoginId(Integer seqLoginId) {
        this.seqLoginId = seqLoginId;
    }

    public Integer getSeqLoginId() {
        return seqLoginId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }

    public String getS3Key() {
        return s3Key;
    }

}