package jp.co.joshua.common.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

import jp.co.joshua.common.bean.ApplicationComponent;
import jp.co.joshua.common.exception.AppException;
import jp.co.joshua.common.util.BeanUtil;

/**
 * AWS認証情報ラッパー <br>
 * ※事前にAWS-CLIでローカルPCに対し、CLI用のIAMユーザを設定しておくこと
 *
 * @version 1.0.0
 */
@Component
public class AwsCredentialsWrapper {

    /** {@linkplain ApplicationComponent} */
    @Autowired
    private ApplicationComponent applicationComponent;

    /**
     * システムプロパティ.環境より、以下の{@linkplain AWSCredentialsProvider}インスタンスを返す<br>
     * <ul>
     * <li>ローカル環境の場合、{@linkplain ProfileCredentialsProvider}</li>
     * <li>EC2環境の場合、{@linkplain InstanceProfileCredentialsProvider}</li>
     * </ul>
     *
     * @return AWSCredentialsProvider
     * @throws AppException
     *             環境の指定が不正な場合
     */
    public AWSCredentialsProvider getAWSCredentialsProvider() throws AppException {

        if (BeanUtil.notNull(applicationComponent.getEnvironment())) {
            switch (applicationComponent.getEnvironment()) {
            case LOCAL:
                return getProfileCredentialsProvider();
            case EC2:
                return getInstanceProfileCredentialsProvider();
            }
        }

        // システムプロパティの環境がNullや実行可能環境でない場合
        throw new AppException("環境の指定が不正です.環境=" + applicationComponent.getEnvironment());
    }

    /**
     * {@linkplain ProfileCredentialsProvider}を返す
     *
     * @return ProfileCredentialsProvider
     */
    public AWSCredentialsProvider getProfileCredentialsProvider() {
        return new ProfileCredentialsProvider();
    }

    /**
     * {@linkplain InstanceProfileCredentialsProvider}を返す
     *
     * @return InstanceProfileCredentialsProvider
     */
    public AWSCredentialsProvider getInstanceProfileCredentialsProvider() {
        return new InstanceProfileCredentialsProvider(false);
    }
}
