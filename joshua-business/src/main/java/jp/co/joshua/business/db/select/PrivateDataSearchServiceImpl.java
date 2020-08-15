package jp.co.joshua.business.db.select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.PrivateDataDao;
import jp.co.joshua.common.db.entity.PrivateData;

@Service
public class PrivateDataSearchServiceImpl implements PrivateDataSearchService {

    @Autowired
    private PrivateDataDao dao;

    @Override
    public PrivateData selectBySeqLoginId(Integer seqLoginId) {
        return dao.selectBySeqLoginId(seqLoginId);
    }

}
