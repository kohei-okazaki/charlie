package jp.co.joshua.business.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jp.co.joshua.business.db.create.PrivateDataCreateService;
import jp.co.joshua.business.db.select.LoginUserDataSearchService;
import jp.co.joshua.business.db.select.PrivateDataSearchService;
import jp.co.joshua.business.db.update.LoginUserDataUpdateService;
import jp.co.joshua.business.db.update.PrivateDataUpdateService;
import jp.co.joshua.business.user.dto.UserEditDto;
import jp.co.joshua.common.db.entity.LoginUserData;
import jp.co.joshua.common.db.entity.PrivateData;
import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;

/**
 * ユーザ情報変更画面のサービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class UserEditServiceImpl implements UserEditService {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(UserEditServiceImpl.class);

    /** トランザクション管理クラス */
    @Autowired
    private PlatformTransactionManager transactionManager;
    /** トランザクションクラス */
    @Autowired
    private DefaultTransactionDefinition defaultTransactionDefinition;
    /** ログインユーザ情報検索サービス */
    @Autowired
    private LoginUserDataSearchService loginUserDataSearchService;
    /** ログインユーザ情報更新サービス */
    @Autowired
    private LoginUserDataUpdateService loginUserDataUpdateService;
    /** メールユーザ情報検索サービス */
    @Autowired
    private PrivateDataSearchService privateDataSearchService;
    /** メールユーザ情報作成サービス */
    @Autowired
    private PrivateDataCreateService privateDataCreateService;
    /** メールユーザ情報更新サービス */
    @Autowired
    private PrivateDataUpdateService privateDataUpdateService;
    /** ModelMapper */
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void edit(UserEditDto dto) {

        LOG.debugBean(dto);

        TransactionStatus status = transactionManager
                .getTransaction(defaultTransactionDefinition);

        try {

            if (dto.getPasswordEditFlag() != null
                    && dto.getPasswordEditFlag().booleanValue()) {

                LoginUserData loginUserData = loginUserDataSearchService
                        .selectById(dto.getSeqLoginId());
                loginUserData.setPassword(dto.getPassword());

                loginUserDataUpdateService.update(loginUserData);
                LOG.debugBean(loginUserData);
            }

            PrivateData privateData = privateDataSearchService
                    .selectBySeqLoginId(dto.getSeqLoginId());

            if (privateData == null) {

                privateData = new PrivateData();
                modelMapper.map(dto, privateData);

                privateDataCreateService.create(privateData);

            } else {

                privateData.setMailAddress(dto.getMailAddress());
                privateData.setUserName(dto.getUserName());

                privateDataUpdateService.update(privateData);
            }

            LOG.debugBean(privateData);
            transactionManager.commit(status);

        } catch (Exception e) {
            transactionManager.rollback(status);
            LOG.error("ユーザ情報変更処理をrollbackしました", e);
            throw e;
        }

    }

    @Override
    public UserEditDto getUserEditDto(Integer seqLoginId) {

        PrivateData privateData = privateDataSearchService
                .selectBySeqLoginId(seqLoginId);

        UserEditDto dto = new UserEditDto();
        dto.setUserName(
                privateData == null ? seqLoginId.toString() : privateData.getUserName());
        dto.setMailAddress(privateData == null ? null : privateData.getMailAddress());

        return dto;
    }

}
