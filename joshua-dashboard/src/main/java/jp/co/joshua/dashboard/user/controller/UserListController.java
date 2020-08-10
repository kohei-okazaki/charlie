package jp.co.joshua.dashboard.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.joshua.common.web.view.AppView;

/**
 * ユーザ一覧画面Controller
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/user")
public class UserListController {

    /**
     * ユーザ一覧画面表示処理
     *
     * @param model
     *            Model
     * @return ユーザ一覧画面
     */
    @GetMapping("list")
    @PreAuthorize("hasAuthority('00')")
    public String list(Model model) {

        return AppView.USER_LIST_VIEW.getValue();
    }

}
