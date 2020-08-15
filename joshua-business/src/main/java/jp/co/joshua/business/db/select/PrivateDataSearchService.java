package jp.co.joshua.business.db.select;

import jp.co.joshua.common.db.entity.PrivateData;

public interface PrivateDataSearchService {

    PrivateData selectBySeqLoginId(Integer seqLoginId);

}
