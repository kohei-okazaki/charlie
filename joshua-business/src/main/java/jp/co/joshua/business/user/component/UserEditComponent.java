package jp.co.joshua.business.user.component;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
 * ユーザ情報編集のComponent
 *
 * @version 1.0.0
 */
@Component
public class UserEditComponent {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(UserEditComponent.class);

    /** {@linkplain PlatformTransactionManager} */
    @Autowired
    private PlatformTransactionManager transactionManager;
    /** {@linkplain DefaultTransactionDefinition} */
    @Autowired
    private DefaultTransactionDefinition defaultTransactionDefinition;
    /** {@linkplain LoginUserDataSearchService} */
    @Autowired
    private LoginUserDataSearchService loginUserDataSearchService;
    /** {@linkplain LoginUserDataUpdateService} */
    @Autowired
    private LoginUserDataUpdateService loginUserDataUpdateService;
    /** {@linkplain PrivateDataSearchService} */
    @Autowired
    private PrivateDataSearchService privateDataSearchService;
    /** {@linkplain PrivateDataCreateService} */
    @Autowired
    private PrivateDataCreateService privateDataCreateService;
    /** {@linkplain PrivateDataUpdateService} */
    @Autowired
    private PrivateDataUpdateService privateDataUpdateService;
    /** {@linkplain ModelMapper} */
    @Autowired
    private ModelMapper modelMapper;

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
