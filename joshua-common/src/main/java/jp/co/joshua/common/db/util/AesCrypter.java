package jp.co.joshua.common.db.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.joshua.common.bean.ApplicationComponent;
import jp.co.joshua.common.type.Algorithm;
import jp.co.joshua.common.type.Charset;
import jp.co.joshua.common.util.StringUtil;

/**
 * AESの可逆暗号クラス
 *
 * @version 1.0.0
 */
@Component("aesCrypter")
public class AesCrypter implements Crypter {

    @Autowired
    private ApplicationComponent component;

    @Override
    public String encrypt(String str) throws Exception {

        if (StringUtil.isEmpty(str)) {
            return str;
        }

        byte[] input = str.getBytes(Charset.UTF_8.getValue());

        // 暗号化
        byte[] encrypted = getCipher(Cipher.ENCRYPT_MODE).doFinal(input);

        return Base64.getEncoder().encodeToString(encrypted);

    }

    @Override
    public String decrypt(String str) throws Exception {

        if (StringUtil.isEmpty(str)) {
            return str;
        }

        byte[] input = Base64.getDecoder().decode(str);

        // 復号
        byte[] decrypted = getCipher(Cipher.DECRYPT_MODE).doFinal(input);

        return new String(decrypted, Charset.UTF_8.getValue());

    }

    /**
     * Cipherを返す
     *
     * @param mode
     *            暗号化モード
     * @return Cipher
     * @throws UnsupportedEncodingException
     *             文字コードが不正な場合
     * @throws NoSuchAlgorithmException
     *             Algorithmが不正な場合
     * @throws NoSuchPaddingException
     *             Cipherの生成に失敗した場合
     * @throws InvalidKeyException
     *             Cipherの初期化に失敗した場合
     */
    private Cipher getCipher(int mode) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        SecretKeySpec sks = new SecretKeySpec(getKey(), Algorithm.AES.getValue());

        Cipher cipher = Cipher.getInstance(component.getCryptMode());
        cipher.init(mode, sks);

        return cipher;
    }

    /**
     * 秘密鍵を返す
     *
     * @return 秘密鍵
     * @throws UnsupportedEncodingException
     *             文字コードの指定が不正の場合
     */
    private byte[] getKey() throws UnsupportedEncodingException {
        return component.getCryptKey().getBytes(Charset.UTF_8.getValue());
    }
}
