package jp.co.joshua.dashboard.note.form;

import org.hibernate.validator.constraints.Length;

/**
 * メモ登録画面Form
 *
 * @version 1.0.0
 */
public class NoteEntryForm {

    /** 件名 */
    @Length(min = 1, max = 30)
    private String title;
    /** 内容 */
    @Length(min = 1, max = 10000)
    private String detail;

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

}
