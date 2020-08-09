package jp.co.joshua.dashboard.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;
import jp.co.joshua.common.web.auth.login.LoginAuthDto;
import jp.co.joshua.common.web.view.AppView;

/**
 * アプリエラーハンドラー
 *
 * @version 1.0.0
 */
@Component
public class AppErrorHandler implements HandlerExceptionResolver {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(AppErrorHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception e) {

        ModelAndView modelView = new ModelAndView();
        // error画面を設定
        modelView.setViewName(AppView.APP_ERROR_VIEW.getValue());

        // セキュリティ情報を取得
        LoginAuthDto authDto = (LoginAuthDto) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (e instanceof AccessDeniedException) {
            LOG.error("不正リクエストエラー. ログイン中ユーザのアプリ権限=" + authDto.getAppAuth(), e);
        } else {
            LOG.error("エラーが発生しました", e);
        }

        return modelView;
    }

}
