package jp.co.joshua.dashboard.top.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;
import jp.co.joshua.common.web.auth.login.LoginAuthDto;
import jp.co.joshua.common.web.view.AppView;

/**
 * TOP画面コントローラ
 *
 * @version 1.0.0
 */
@Controller
public class TopController {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(TopController.class);
    @Autowired
    private HttpSession session;

    /**
     * ログイン後の画面を表示するためのTOP画面
     *
     * @return TOP
     */
    @RequestMapping(path = "/top", method = { RequestMethod.GET, RequestMethod.POST })
    public String top() {
        LoginAuthDto loginAuthDto = (LoginAuthDto) session.getAttribute("loginAuthDto");
        LOG.debugBean(loginAuthDto);
        return AppView.TOP_VIEW.getValue();
    }
}
