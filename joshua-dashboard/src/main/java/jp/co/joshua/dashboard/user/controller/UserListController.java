package jp.co.joshua.dashboard.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.joshua.business.db.select.LoginUserDataSearchService;
import jp.co.joshua.common.web.view.AppView;
import jp.co.joshua.common.web.view.PagingFactory;

/**
 * ユーザ一覧画面Controller
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/user")
public class UserListController {

    /** {@linkplain LoginUserDataSearchService} */
    @Autowired
    private LoginUserDataSearchService loginUserDataSearchService;

    /**
     * ユーザ一覧画面表示処理
     *
     * @param model
     *            {@linkplain Model}
     * @param pageable
     *            {@linkplain Pageable}
     * @return ユーザ一覧画面
     */
    @GetMapping("list")
    @PreAuthorize("hasAuthority('00')")
    public String list(Model model,
            @PageableDefault(size = 5, page = 0) Pageable pageable) {

        model.addAttribute("paging", PagingFactory.getPageView(pageable,
                "/user/list?page", loginUserDataSearchService.count()));
        model.addAttribute("userList", loginUserDataSearchService
                .selectLoginDataJoinPrivate(pageable));

        return AppView.USER_LIST_VIEW.getValue();
    }

}
