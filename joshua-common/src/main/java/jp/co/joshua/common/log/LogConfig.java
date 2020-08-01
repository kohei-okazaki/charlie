package jp.co.joshua.common.log;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * application-${environment}.ymlのBean定義
 *
 * @version 1.0.0
 */
@Component
public class LogConfig {

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

}
