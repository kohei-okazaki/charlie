package jp.co.joshua.dashboard.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;
import jp.co.joshua.common.web.view.AppView;

/**
 * アプリ内でエラーが発生した場合のController<br>
 * SpringBootではデフォルトで/errorに遷移するため、/common/errorに遷移させるために{@linkplain AppView#APP_ERROR_VIEW}を戻り値に設定している
 * {@linkplain AppErrorHandler}で実際のエラーハンドリングを行う
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/error")
public class AppErrorController implements ErrorController {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(AppErrorController.class);

    @Override
    public String getErrorPath() {
        return AppView.APP_ERROR_VIEW.getValue();
    }

    @GetMapping
    @PostMapping
    public String error(Exception e) {

        LOG.error("エラーが発生しました", e);

        return AppView.APP_ERROR_VIEW.getValue();
    }

}
