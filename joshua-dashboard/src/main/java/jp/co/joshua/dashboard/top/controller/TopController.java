package jp.co.joshua.dashboard.top.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.joshua.common.web.view.AppView;

/**
 * TOP画面コントローラ
 *
 * @version 1.0.0
 */
@Controller
public class TopController {

    /**
     * ログイン後の画面を表示するためのTOP画面
     *
     * @return TOP
     */
    @RequestMapping(path = "/top", method = { RequestMethod.GET, RequestMethod.POST })
    public String top() {
        return AppView.TOP_VIEW.getValue();
    }
}
