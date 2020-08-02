package jp.co.joshua.common.db.entity;

import java.time.LocalDateTime;

import org.seasar.doma.Entity;
import org.seasar.doma.Version;

import jp.co.joshua.common.db.util.AppDaoListener;

/**
 * 基底Entityクラス
 *
 * @version 1.0.0
 */
@Entity(listener = AppDaoListener.class)
public abstract class BaseEntity {

    /** バージョン */
    @Version
    private Integer version;
    /** 登録日時 */
    private LocalDateTime regDate;
    /** 更新日時 */
    private LocalDateTime updateDate;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

}
