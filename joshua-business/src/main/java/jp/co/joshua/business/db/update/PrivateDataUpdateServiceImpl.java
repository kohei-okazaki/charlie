package jp.co.joshua.business.db.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.PrivateDataDao;
import jp.co.joshua.common.db.entity.PrivateData;

@Service
public class PrivateDataUpdateServiceImpl implements PrivateDataUpdateService {

    @Autowired
    private PrivateDataDao dao;

    @Override
    public void update(PrivateData privateData) {
        dao.update(privateData);
    }

}
