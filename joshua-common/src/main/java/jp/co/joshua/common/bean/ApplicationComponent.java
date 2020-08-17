package jp.co.joshua.common.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

import jp.co.joshua.common.type.Environment;

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

    @Value("${spring.profiles}")
    private Environment environment;
    @Value("${logging.pattern.level}")
    private String level;
    @Value("${logging.level.org.springframework}")
    private String springframework;
    @Value("${logging.level.org.springframework.jdbc}")
    private String springframeworkJdbc;
    @Value("${logging.level.org.thymeleaf}")
    private String thymealeaf;
    @Value("${logging.level.jp.co.joshua}")
    private String joshua;
    @Value("${joshua.crypt.mode}")
    private String cryptMode;
    @Value("${joshua.crypt.key}")
    private String cryptKey;
    @Value("${joshua.hash.algorithm}")
    private String hashAlgorithm;
    @Value("${joshua.hash.stretch-count}")
    private int hashStrechCount;
    @Value("${joshua.hash.key-length}")
    private int hashKeyLength;
    @Value("${joshua.hash.salt}")
    private String hashSalt;
    @Value("${joshua.aws.regions}")
    private Regions regions;
    @Value("${joshua.aws.s3.backet}")
    private String backet;
    @Value("${joshua.aws.s3.timeout}")
    private int s3Timeout;

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = Environment.of(environment);
    }

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

    public String getJoshua() {
        return joshua;
    }

    public void setJoshua(String joshua) {
        this.joshua = joshua;
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

    public Regions getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = Regions.fromName(regions);
    }

    public String getBacket() {
        return backet;
    }

    public void setBacket(String backet) {
        this.backet = backet;
    }

    public int getS3Timeout() {
        return s3Timeout;
    }

    public void setS3Timeout(int s3Timeout) {
        this.s3Timeout = s3Timeout;
    }

}
