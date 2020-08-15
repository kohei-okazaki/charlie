package jp.co.joshua.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.PrivateDataDao;
import jp.co.joshua.common.db.entity.PrivateData;

@Service
public class PrivateDataCreateServiceImpl implements PrivateDataCreateService {

    @Autowired
    private PrivateDataDao dao;

    @Override
    public void create(PrivateData privateData) {
        dao.insert(privateData);
    }

}
