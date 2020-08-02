package jp.co.joshua.business.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jp.co.joshua.business.db.create.MailUserDataCreateService;
import jp.co.joshua.business.db.select.LoginUserDataSearchService;
import jp.co.joshua.business.db.select.MailUserDataSearchService;
import jp.co.joshua.business.db.update.LoginUserDataUpdateService;
import jp.co.joshua.business.db.update.MailUserDataUpdateService;
import jp.co.joshua.business.user.dto.UserEditDto;
import jp.co.joshua.common.db.entity.LoginUserData;
import jp.co.joshua.common.db.entity.MailUserData;
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
    private MailUserDataSearchService mailUserDataSearchService;
    /** メールユーザ情報作成サービス */
    @Autowired
    private MailUserDataCreateService mailUserDataCreateService;
    /** メールユーザ情報更新サービス */
    @Autowired
    private MailUserDataUpdateService mailUserDataUpdateService;
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

            MailUserData mailUserData = mailUserDataSearchService
                    .selectBySeqLoginId(dto.getSeqLoginId());

            if (mailUserData == null) {

                mailUserData = new MailUserData();
                modelMapper.map(dto, mailUserData);

                mailUserDataCreateService.create(mailUserData);

            } else {

                mailUserData.setMailAddress(dto.getMailAddress());

                mailUserDataUpdateService.update(mailUserData);
            }

            LOG.debugBean(mailUserData);
            transactionManager.commit(status);

        } catch (Exception e) {
            transactionManager.rollback(status);
            LOG.error("ユーザ情報変更処理をrollbackしました", e);
            throw e;
        }

    }

    @Override
    public UserEditDto getUserEditDto(Integer seqLoginId) {

        MailUserData mailUserData = mailUserDataSearchService
                .selectBySeqLoginId(seqLoginId);

        UserEditDto dto = new UserEditDto();
        dto.setMailAddress(mailUserData == null ? null : mailUserData.getMailAddress());

        return dto;
    }

}
