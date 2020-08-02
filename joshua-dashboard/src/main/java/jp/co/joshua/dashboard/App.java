package jp.co.joshua.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * アプリ起動クラス
 *
 * @version 1.0.0
 */
@SpringBootApplication
@ComponentScan({
        "jp.co.joshua.dashboard",
        "jp.co.joshua.common",
        "jp.co.joshua.business" })
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
