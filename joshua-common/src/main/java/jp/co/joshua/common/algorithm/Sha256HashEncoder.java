package jp.co.joshua.common.algorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.joshua.common.bean.ApplicationComponent;
import jp.co.joshua.common.type.Algorithm;

/**
 * SHA-256ハッシュ関数クラス
 *
 * @version 1.0.0
 */
@Component
public class Sha256HashEncoder {

    /** アプリケーション設定情報 */
    @Autowired
    private ApplicationComponent component;

    /**
     * SHA-256でハッシュ化を行う
     *
     * @param str
     *            平文文字列
     * @return SHA-256でハッシュ化後の文字列
     */
    public String encode(String str) {

        try {
            char[] passCharArray = str.toCharArray();
            byte[] hashedSalt = getHashedSalt(component.getHashSalt());

            PBEKeySpec keySpec = new PBEKeySpec(passCharArray, hashedSalt,
                    component.getHashStrechCount(), component.getHashKeyLength());

            SecretKeyFactory skf = SecretKeyFactory
                    .getInstance(component.getHashAlgorithm());

            SecretKey secretKey = skf.generateSecret(keySpec);

            byte[] passByteAry = secretKey.getEncoded();

            // 生成されたバイト配列を16進数の文字列に変換
            StringBuilder sb = new StringBuilder(64);
            for (byte b : passByteAry) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("秘密鍵情報が不正です", e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("アルゴリズムの指定が不正です", e);
        }
    }

    /**
     * ソルトをハッシュ化して返却します<br>
     * ※ハッシュアルゴリズムはSHA-256を使用
     *
     * @param salt
     *            ソルト
     * @return ハッシュ化されたバイト配列のソルト
     */
    private static byte[] getHashedSalt(String salt) {

        byte[] result = null;
        try {
            MessageDigest messageDigest = MessageDigest
                    .getInstance(Algorithm.SHA_256.getValue());
            messageDigest.update(salt.getBytes());
            result = messageDigest.digest();
        } catch (@SuppressWarnings("unused") NoSuchAlgorithmException e) {
            // SHA256を指定している為、発生しない
        }

        return result;
    }

}
