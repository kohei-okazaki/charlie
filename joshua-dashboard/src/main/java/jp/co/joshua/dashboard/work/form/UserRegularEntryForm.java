package jp.co.joshua.dashboard.work.form;

import javax.validation.constraints.NotBlank;

/**
 * ユーザ定時情報登録Form
 *
 * @version 1.0.0
 */
public class UserRegularEntryForm {

    /** ログインID */
    @NotBlank
    private Integer seqLoginId;
    /** 定時情報マスタID */
    @NotBlank
    private Integer seqRegularWorkMtId;

    public Integer getSeqLoginId() {
        return seqLoginId;
    }

    public void setSeqLoginId(Integer seqLoginId) {
        this.seqLoginId = seqLoginId;
    }

    public Integer getSeqRegularWorkMtId() {
        return seqRegularWorkMtId;
    }

    public void setSeqRegularWorkMtId(Integer seqRegularWorkMtId) {
        this.seqRegularWorkMtId = seqRegularWorkMtId;
    }

}
