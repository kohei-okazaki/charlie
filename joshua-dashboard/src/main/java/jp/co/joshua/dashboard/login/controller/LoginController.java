package jp.co.joshua.dashboard.login.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.joshua.common.web.view.AppView;
import jp.co.joshua.dashboard.login.form.LoginForm;

/**
 * ログイン画面コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /** {@linkplain HttpSession} */
    @Autowired
    private HttpSession session;

    @ModelAttribute
    public LoginForm loginForm() {
        return new LoginForm();
    }

    @GetMapping
    public String index() {
        return AppView.LOGIN_VIEW.getValue();
    }

    @RequestMapping(path = "/processLogin", method = { RequestMethod.GET,
            RequestMethod.POST })
    public String loginPost(@SuppressWarnings("unused") @Validated LoginForm form,
            BindingResult br) {
        // TODO このメソッド使ってなさそうなら削除

        if (br.hasErrors()) {
            // 入力チェックエラーがある場合は、ログイン画面に戻る
            return AppView.LOGIN_VIEW.getValue();
        }

        return "forward:/success";
    }

    @RequestMapping(path = "/success", method = { RequestMethod.GET, RequestMethod.POST })
    public String success() {
        // ログイン成功時、TOP画面にRedirectする
        return "redirect:/top";
    }

    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("loginError", true);
        return AppView.LOGIN_VIEW.getValue();
    }

    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {

        Enumeration<String> sessionData = session.getAttributeNames();
        while (sessionData.hasMoreElements()) {
            String key = sessionData.nextElement();
            System.out.println(key);
            session.removeAttribute(key);
        }

        redirectAttributes.addFlashAttribute("isLogout", true);
        redirectAttributes.addFlashAttribute("infoMessage", "ログアウトしました");
        return "redirect:/login";
    }
}
