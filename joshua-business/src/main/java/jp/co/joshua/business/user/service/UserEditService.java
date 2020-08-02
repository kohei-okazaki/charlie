package jp.co.joshua.business.user.service;

import jp.co.joshua.business.user.dto.UserEditDto;

/**
 * ユーザ情報変更画面のサービスインターフェース
 *
 * @version 1.0.0
 */
public interface UserEditService {

    /**
     * ユーザ情報変更処理を行う
     *
     * @param dto
     *            ユーザ情報設定変更画面DTO
     */
    void edit(UserEditDto dto);

    /**
     * ユーザ情報変更DTOを返す
     *
     * @param seqLoginId
     *            ログインID
     * @return ユーザ情報変更DTO
     */
    UserEditDto getUserEditDto(Integer seqLoginId);

}
