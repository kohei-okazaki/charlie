package jp.co.joshua.common.db.util;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.data.domain.Pageable;

/**
 * DomaのUtilクラス
 *
 * @version 1.0.0
 */
public class DomaUtil {

    /**
     * プライベートコンストラクタ
     */
    private DomaUtil() {
    }

    /**
     * SelectOptionsを作成して返す
     *
     * @return SelectOptions
     */
    public static SelectOptions createSelectOptions() {
        return SelectOptions.get();
    }

    /**
     * {@linkplain SelectOptions}を返す
     *
     * @param pageable
     *            Pageable
     * @param countFlg
     *            カウント取得フラグ
     * @return SelectOptions
     */
    public static SelectOptions createSelectOptions(Pageable pageable, boolean countFlg) {

        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();

        SelectOptions selectOptions = SelectOptions.get().offset(offset).limit(limit);
        if (countFlg) {
            selectOptions = selectOptions.count();
        }

        return selectOptions;
    }

    // /**
    // * SelectOptionsを作成して返す
    // *
    // * @param pageable
    // * Pageable
    // * @return SelectOptions
    // */
    // public static SelectOptions createSelectOptions(Pageable pageable) {
    // return createSelectOptions(pageable.getPage(), pageable.getPerpage());
    // }
    //
    // /**
    // * SelectOptionsを作成して返す
    // *
    // * @param page
    // * ページ数
    // * @param perpage
    // * 許容数
    // * @return SelectOptions
    // */
    // private static SelectOptions createSelectOptions(int page, int perpage) {
    // int offset = (page - 1) * perpage;
    // return createSelectOptions().offset(offset).limit(perpage);
    // }

}
