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

}
