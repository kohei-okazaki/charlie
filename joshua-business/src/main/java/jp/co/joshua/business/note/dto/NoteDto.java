package jp.co.joshua.business.note.dto;

import java.time.LocalDateTime;

/**
 * メモ表示情報
 *
 * @version 1.0.0
 */
public class NoteDto {

    /** メモユーザ情報ID */
    private Integer seqNoteUserDataId;
    /** 件名 */
    private String title;
    /** 詳細 */
    private String detail;
    /** 登録日時 */
    private LocalDateTime regDate;
    /** 更新日時 */
    private LocalDateTime updateDate;

    public Integer getSeqNoteUserDataId() {
        return seqNoteUserDataId;
    }

    public void setSeqNoteUserDataId(Integer seqNoteUserDataId) {
        this.seqNoteUserDataId = seqNoteUserDataId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
