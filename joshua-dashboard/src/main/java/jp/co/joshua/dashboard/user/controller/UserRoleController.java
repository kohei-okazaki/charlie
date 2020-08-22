package jp.co.joshua.dashboard.user.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.joshua.business.db.select.LoginUserDataSearchService;
import jp.co.joshua.business.db.update.LoginUserDataUpdateService;
import jp.co.joshua.common.db.entity.LoginUserData;
import jp.co.joshua.common.db.type.AppAuth;
import jp.co.joshua.common.web.view.AppView;
import jp.co.joshua.dashboard.user.form.UserRoleForm;

/**
 * ユーザ権限付与画面Controller
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/user")
public class UserRoleController {

    /** {@linkplain LoginUserDataSearchService} */
    @Autowired
    private LoginUserDataSearchService loginUserDataSearchService;
    /** {@linkplain LoginUserDataUpdateService} */
    @Autowired
    private LoginUserDataUpdateService loginUserDataUpdateService;

    @ModelAttribute
    public UserRoleForm userRoleForm() {
        return new UserRoleForm();
    }

    /**
     * 権限付与画面表示処理
     *
     * @param model
     *            {@linkplain Model}
     * @param seqLoginId
     *            ログインID
     * @param redirectAttributes
     *            {@linkplain RedirectAttributes}
     * @return 権限付与画面
     */
    @GetMapping("role/{seqLoginId}")
    public String role(Model model,
            @PathVariable("seqLoginId") Optional<Integer> seqLoginId,
            RedirectAttributes redirectAttributes) {

        if (!seqLoginId.isPresent()) {
            redirectAttributes.addFlashAttribute("errMsg", "ログインIDの指定が不正です.");
            return AppView.USER_LIST_VIEW.toRedirect();
        }
        LoginUserData entity = loginUserDataSearchService.selectById(seqLoginId.get());
        if (entity == null) {
            redirectAttributes.addFlashAttribute("errMsg",
                    "ログインIDの指定が不正です.ログインID=" + seqLoginId.get());
            return AppView.USER_LIST_VIEW.toRedirect();
        }
        model.addAttribute("entity", entity);

        model.addAttribute("roleList", AppAuth.values());

        return AppView.USER_ROLE_VIEW.getValue();
    }

    /**
     * 権限付与画面表示処理
     *
     * @param model
     *            {@linkplain Model}
     * @param form
     *            ユーザ権限画面Form
     * @param result
     *            {@linkplain BindingResult}
     * @param redirectAttributes
     *            {@linkplain RedirectAttributes}
     * @return 権限付与画面
     */
    @PostMapping("role")
    public String role(Model model, @Validated UserRoleForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return AppView.USER_ROLE_VIEW.getValue();
        }

        loginUserDataUpdateService.updateAppAuthBySeqLoginId(form.getSeqLoginId(),
                form.getAppAuth());
        redirectAttributes.addAttribute("seqLoginId", form.getSeqLoginId());
        redirectAttributes.addFlashAttribute("isSuccess", true);

        return AppView.USER_ROLE_VIEW.toRedirect() + "/{seqLoginId}";
    }

}
