package jp.co.joshua.dashboard.user.controller;

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

import jp.co.joshua.business.user.component.UserEditComponent;
import jp.co.joshua.business.user.dto.UserEditDto;
import jp.co.joshua.common.exception.AppException;
import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;
import jp.co.joshua.common.web.auth.login.LoginAuthDto;
import jp.co.joshua.common.web.auth.login.SecurityContextWrapper;
import jp.co.joshua.common.web.view.AppView;
import jp.co.joshua.dashboard.user.form.UserEditForm;

/**
 * ユーザ情報変更画面コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/user")
public class UserEditController {

    /** LOG */
    private static final Logger LOG = LoggerFactory
            .getLogger(UserEditController.class);

    /** {@linkplain HttpSession} */
    @Autowired
    private HttpSession session;
    /** {@linkplain SecurityContextWrapper} */
    @Autowired
    private SecurityContextWrapper wrapper;
    /** {@linkplain UserEditComponent} */
    @Autowired
    private UserEditComponent userEditComponent;
    /** {@linkplain ModelMapper} */
    @Autowired
    private ModelMapper modelMapper;

    @ModelAttribute
    public UserEditForm userEditForm() throws AppException {

        LoginAuthDto loginAuthDto = wrapper.getLoginAuthDto()
                .orElseThrow(() -> new AppException("認証情報が設定されていません"));
        UserEditDto dto = userEditComponent
                .getUserEditDto(loginAuthDto.getSeqLoginId());

        UserEditForm form = modelMapper.map(dto, UserEditForm.class);
        LOG.debugBean(form);

        return form;
    }

    /**
     * ユーザ情報設定変更画面
     *
     * @return ユーザ情報設定変更View
     */
    @GetMapping("/edit")
    public String edit() {
        return AppView.USER_EDIT_VIEW.getValue();
    }

    /**
     * ユーザ情報設定変更確認画面
     *
     * @param model
     *            {@linkplain Model}
     * @param userEditForm
     *            {@linkplain UserEditForm}
     * @param result
     *            {@linkplain BindingResult}
     * @return ユーザ情報設定変更確認View
     */
    @PostMapping("/editconfirm")
    public String editConfirm(Model model, @Validated UserEditForm userEditForm,
            BindingResult result) {

        if (result.hasErrors()) {
            return AppView.USER_EDIT_VIEW.getValue();
        }

        session.setAttribute("userEditForm", userEditForm);

        model.addAttribute("userEditForm", userEditForm);

        return AppView.USER_EDIT_CONFIRM_VIEW.getValue();
    }

    /**
     * ユーザ情報設定完了画面
     *
     * @param model
     *            {@linkplain Model}
     * @return ユーザ情報設定完了View
     * @throws AppException
     */
    @PostMapping("/editprocess")
    public String editProcess(Model model) throws AppException {

        UserEditForm form = (UserEditForm) session
                .getAttribute("userEditForm");
        LoginAuthDto loginAuthDto = wrapper.getLoginAuthDto()
                .orElseThrow(() -> new AppException("認証情報が設定されていません"));

        UserEditDto dto = modelMapper.map(form, UserEditDto.class);
        dto.setSeqLoginId(loginAuthDto.getSeqLoginId());

        userEditComponent.edit(dto);

        return AppView.USER_EDIT_PROCESS_VIEW.getValue();
    }

}
