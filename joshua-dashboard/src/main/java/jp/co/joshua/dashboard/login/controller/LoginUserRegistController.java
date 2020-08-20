package jp.co.joshua.dashboard.login.controller;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.joshua.business.db.create.LoginUserDataCreateService;
import jp.co.joshua.common.db.entity.LoginUserData;
import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;
import jp.co.joshua.common.web.view.AppView;
import jp.co.joshua.dashboard.login.form.LoginUserRegistForm;

/**
 * ログインユーザ登録画面コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/login")
public class LoginUserRegistController {

    /** LOG */
    private static final Logger LOG = LoggerFactory
            .getLogger(LoginUserRegistController.class);

    /** {@linkplain HttpSession} */
    @Autowired
    private HttpSession session;
    /** {@linkplain ModelMapper} */
    @Autowired
    private ModelMapper modelMapper;
    /** {@linkplain LoginUserDataCreateService} */
    @Autowired
    private LoginUserDataCreateService loginUserCreateService;

    @ModelAttribute
    public LoginUserRegistForm loginUserRegistForm() {
        return new LoginUserRegistForm();
    }

    /**
     * 登録情報入力画面
     *
     * @return ログインユーザ登録View
     */
    @GetMapping("/regist")
    public String regist() {
        return AppView.LOGIN_REGIST_VIEW.getValue();
    }

    /**
     * 登録情報入力確認画面
     *
     * @param model
     *            {@linkplain Model}
     * @param loginUserRegistForm
     *            {@linkplain LoginUserRegistForm}
     * @param result
     *            {@linkplain BindingResult}
     * @return ログインユーザ登録確認View
     */
    @PostMapping("/registconfirm")
    public String registConfirm(Model model,
            @Validated LoginUserRegistForm loginUserRegistForm, BindingResult result) {

        if (result.hasErrors()) {
            return AppView.LOGIN_REGIST_VIEW.getValue();
        }

        session.setAttribute("loginUserRegistForm", loginUserRegistForm);

        return AppView.LOGIN_REGIST_CONFIRM_VIEW.getValue();
    }

    /**
     * 登録完了画面
     *
     * @param model
     *            {@linkplain Model}
     * @return ログインユーザ登録完了View
     */
    @PostMapping("/registprocess")
    public String registProcess(Model model) {

        LoginUserRegistForm form = (LoginUserRegistForm) session
                .getAttribute("loginUserRegistForm");

        LoginUserData loginUserData = modelMapper.map(form, LoginUserData.class);
        LOG.debugBean(loginUserData);

        loginUserCreateService.create(loginUserData);

        model.addAttribute("loginId", loginUserData.getSeqLoginId());

        return AppView.LOGIN_REGIST_PROCESS_VIEW.getValue();
    }

}
