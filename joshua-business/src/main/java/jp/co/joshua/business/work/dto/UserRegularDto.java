package jp.co.joshua.business.work.dto;

/**
 * ユーザ定時情報機能Dto
 *
 * @version 1.0.0
 */
public class UserRegularDto {

    /** ログインID */
    private Integer seqLoginId;
    /** 定時情報マスタID */
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
