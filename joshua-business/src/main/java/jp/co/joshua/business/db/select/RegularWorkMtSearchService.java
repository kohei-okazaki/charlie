package jp.co.joshua.business.db.select;

import java.util.List;

import org.springframework.data.domain.Pageable;

import jp.co.joshua.common.db.entity.RegularWorkMt;

/**
 * 定時情報マスタ検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface RegularWorkMtSearchService {

    long count();

    List<RegularWorkMt> selectAll();

    List<RegularWorkMt> selectAll(Pageable pageable);

    RegularWorkMt selectById(Integer seqRegularWorkMtId);

}
