package jp.co.joshua.common.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <ul>
 * <li>application.yml</li>
 * <li>application-${environment}.yml</li>
 * </ul>
 * のBean定義
 *
 * @version 1.0.0
 */
@Component
public class ApplicationComponent {

    @Value("${logging.pattern.level}")
    private String level;
    @Value("${logging.level.org.springframework}")
    private String springframework;
    @Value("${logging.level.org.springframework.jdbc}")
    private String springframeworkJdbc;
    @Value("${logging.level.org.thymeleaf}")
    private String thymealeaf;
    @Value("${logging.level.jp.co.nok}")
    private String nok;
    @Value("${nok.crypt.mode}")
    private String cryptMode;
    @Value("${nok.crypt.key}")
    private String cryptKey;
    @Value("${nok.hash.algorithm}")
    private String hashAlgorithm;
    @Value("${nok.hash.stretch-count}")
    private int hashStrechCount;
    @Value("${nok.hash.key-length}")
    private int hashKeyLength;
    @Value("${nok.hash.salt}")
    private String hashSalt;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSpringframework() {
        return springframework;
    }

    public void setSpringframework(String springframework) {
        this.springframework = springframework;
    }

    public String getSpringframeworkJdbc() {
        return springframeworkJdbc;
    }

    public void setSpringframeworkJdbc(String springframeworkJdbc) {
        this.springframeworkJdbc = springframeworkJdbc;
    }

    public String getThymealeaf() {
        return thymealeaf;
    }

    public void setThymealeaf(String thymealeaf) {
        this.thymealeaf = thymealeaf;
    }

    public String getNok() {
        return nok;
    }

    public void setNok(String nok) {
        this.nok = nok;
    }

    public String getCryptMode() {
        return cryptMode;
    }

    public void setCryptMode(String cryptMode) {
        this.cryptMode = cryptMode;
    }

    public String getCryptKey() {
        return cryptKey;
    }

    public void setCryptKey(String cryptKey) {
        this.cryptKey = cryptKey;
    }

    public String getHashAlgorithm() {
        return hashAlgorithm;
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public int getHashStrechCount() {
        return hashStrechCount;
    }

    public void setHashStrechCount(int hashStrechCount) {
        this.hashStrechCount = hashStrechCount;
    }

    public int getHashKeyLength() {
        return hashKeyLength;
    }

    public void setHashKeyLength(int hashKeyLength) {
        this.hashKeyLength = hashKeyLength;
    }

    public String getHashSalt() {
        return hashSalt;
    }

    public void setHashSalt(String hashSalt) {
        this.hashSalt = hashSalt;
    }

}
