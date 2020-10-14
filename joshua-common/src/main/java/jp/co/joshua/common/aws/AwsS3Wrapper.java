package jp.co.joshua.common.aws;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.ClientConfigurationFactory;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import jp.co.joshua.common.bean.ApplicationComponent;
import jp.co.joshua.common.exception.AppException;
import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;
import jp.co.joshua.common.type.Charset;

/**
 * AWS-S3のラッパークラス
 *
 * @version 1.0.0
 */
@Component
public class AwsS3Wrapper {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(AwsS3Wrapper.class);
    /** 文字コード:UTF-8 */
    private static final Charset CHARSET = Charset.UTF_8;

    /** {@linkplain ApplicationComponent} */
    @Autowired
    private ApplicationComponent applicationComponent;
    /** {@linkplain AwsCredentialsWrapper} */
    @Autowired
    private AwsCredentialsWrapper credentialsWrapper;

    /**
     * 指定されたキーへファイルを配置する
     *
     * @param key
     *            バケット内のキー(ファイル名込)
     * @param file
     *            ファイル
     * @throws AppException
     *             S3へファイルアップロードに失敗した場合
     * @see #putFile(String, long, InputStream)
     */
    public void putFile(String key, File file) throws AppException {

        try (InputStream is = new FileInputStream(file)) {
            putFile(key, file.length(), is);
        } catch (FileNotFoundException e) {
            throw new AppException(file.getPath() + "が存在しません", e);
        } catch (IOException e) {
            throw new AppException(e);
        }
    }

    /**
     * 指定されたキーへ文字列データをファイルとして配置する
     *
     * @param key
     * @param strData
     * @throws AppException
     *             S3へファイルアップロードに失敗した場合
     * @see #putFile(String, long, InputStream)
     */
    public void putFile(String key, String strData) throws AppException {
        try {
            byte[] b = strData.getBytes(CHARSET.getValue());
            InputStream is = new ByteArrayInputStream(b);
            putFile(key, b.length, is);
        } catch (UnsupportedEncodingException e) {
            // UTF-8指定のため、発生しない
            LOG.warn("文字コードの指定が不正です", e);
        }
    }

    /**
     * 指定されたキーからファイルの入力Streamを返す
     *
     * @param key
     *            キー
     * @return 入力Stream
     * @throws AppException
     *             S3へのファイルダウンロードに失敗した場合
     */
    @SuppressWarnings("resource")
    public InputStream getS3ObjectByKey(String key) throws AppException {

        try {

            LOG.debug("Amazon S3 region=" + applicationComponent.getRegions().getName()
                    + ",backet=" + applicationComponent.getBacket() + ",key=" + key);

            GetObjectRequest request = new GetObjectRequest(
                    applicationComponent.getBacket(), key);

            S3Object s3Object = getAmazonS3().getObject(request);

            return s3Object.getObjectContent();

        } catch (AmazonServiceException e) {
            throw new AppException("リクエストの処理中にAmazon S3でエラーが発生。backet="
                    + applicationComponent.getBacket() + ", key=" + key, e);
        } catch (SdkClientException e) {
            throw new AppException("リクエストの作成中またはレスポンスの処理中にクライアントでエラーが発生"
                    + applicationComponent.getBacket() + ", key=" + key, e);
        }

    }

    /**
     * {@linkplain S3ObjectSummary}のリストを返す
     *
     * @return ファイルリスト
     * @throws AppException
     *             AWS、SDK接続エラー
     */
    public List<S3ObjectSummary> getS3ObjectList() throws AppException {
        try {

            LOG.debug("Amazon S3 region=" + applicationComponent.getRegions().getName()
                    + ",backet=" + applicationComponent.getBacket());

            ObjectListing objectListing = getAmazonS3()
                    .listObjects(applicationComponent.getBacket());

            return objectListing.getObjectSummaries();

        } catch (AmazonServiceException e) {
            throw new AppException("リクエストの処理中にAmazon S3でエラーが発生。backet="
                    + applicationComponent.getBacket(), e);
        } catch (SdkClientException e) {
            throw new AppException("リクエストの作成中またはレスポンスの処理中にクライアントでエラーが発生。backet="
                    + applicationComponent.getBacket(), e);
        }
    }

    /**
     * 指定したS3キーを削除する
     *
     * @param key
     *            キー
     * @throws AppException
     */
    public void deleteS3ObjectByKey(String key) throws AppException {

        try {

            LOG.debug("Amazon S3 region=" + applicationComponent.getRegions().getName()
                    + ",backet=" + applicationComponent.getBacket() + ",key=" + key);

            getAmazonS3().deleteObject(
                    new DeleteObjectRequest(applicationComponent.getBacket(), key));

        } catch (AmazonServiceException e) {
            throw new AppException("リクエストの処理中にAmazon S3でエラーが発生。backet="
                    + applicationComponent.getBacket(), e);
        } catch (SdkClientException e) {
            throw new AppException("リクエストの作成中またはレスポンスの処理中にクライアントでエラーが発生。backet="
                    + applicationComponent.getBacket(), e);
        }
    }

    /**
     * {@linkplain AmazonS3}を返す
     *
     * @return AmazonS3
     * @throws AppException
     *             認証情報の取得に失敗した場合
     */
    private AmazonS3 getAmazonS3() throws AppException {
        return AmazonS3ClientBuilder
                .standard()
                // IAMユーザ認証情報を設定
                .withCredentials(credentialsWrapper.getAWSCredentialsProvider())
                .withClientConfiguration(getClientConfiguration())
                .withRegion(applicationComponent.getRegions())
                .build();
    }

    /**
     * S3の指定したキーにInputStreamのデータをファイルとしてアップロードする
     *
     * @param key
     *            バケット内のキー(ファイル名込)
     * @param length
     *            ファイルサイズ
     * @param is
     *            InputStream
     * @throws AppException
     *             S3へのファイルアップロードに失敗した場合
     */
    private void putFile(String key, long length, InputStream is) throws AppException {

        try {

            ObjectMetadata om = new ObjectMetadata();
            om.setContentLength(length);
            PutObjectRequest putRequest = new PutObjectRequest(
                    applicationComponent.getBacket(), key, is, om);
            // 権限の設定
            putRequest.setCannedAcl(CannedAccessControlList.PublicReadWrite);
            // アップロード
            getAmazonS3().putObject(putRequest);

        } catch (AmazonServiceException e) {
            throw new AppException("リクエストの処理中にAmazon S3でエラーが発生し、S3へのファイルアップロードに失敗。backet="
                    + applicationComponent.getBacket() + ", key=" + key, e);
        } catch (SdkClientException e) {
            throw new AppException("リクエストの作成中またはレスポンスの処理中にクライアントでエラーが発生。backet="
                    + applicationComponent.getBacket() + ", key=" + key, e);
        }
    }

    /**
     * {@linkplain ClientConfiguration}を返す
     *
     * @return ClientConfiguration
     */
    private ClientConfiguration getClientConfiguration() {
        ClientConfiguration config = new ClientConfigurationFactory().getConfig();
        config.setConnectionTimeout(applicationComponent.getS3Timeout());
        return config;
    }
}
