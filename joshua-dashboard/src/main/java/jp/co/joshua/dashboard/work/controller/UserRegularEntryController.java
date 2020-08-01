package jp.co.joshua.dashboard.work.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.joshua.business.db.create.WorkUserMtCreateService;
import jp.co.joshua.business.db.select.LoginUserDataSearchService;
import jp.co.joshua.business.db.select.RegularWorkMtSearchService;
import jp.co.joshua.business.db.select.WorkUserMtSearchService;
import jp.co.joshua.common.db.entity.RegularWorkMt;
import jp.co.joshua.common.db.entity.WorkUserMt;
import jp.co.joshua.common.web.view.AppView;
import jp.co.joshua.common.web.view.PagingFactory;
import jp.co.joshua.dashboard.work.form.UserRegularEntryForm;

/**
 * ユーザ定時情報登録Controller
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/work/userregular")
public class UserRegularEntryController {

    /** ModelMapper */
    @Autowired
    private ModelMapper modelMapper;
    /** ログインユーザ情報検索サービス */
    @Autowired
    private LoginUserDataSearchService loginUserDataSearchService;
    /** 定時情報マスタ検索サービス */
    @Autowired
    private RegularWorkMtSearchService regularWorkMtSearchService;
    /** 勤怠ユーザマスタ検索サービス */
    @Autowired
    private WorkUserMtSearchService workUserMtSearchService;
    /** 勤怠ユーザマスタ作成サービス */
    @Autowired
    private WorkUserMtCreateService workUserMtCreateService;

    @ModelAttribute
    public UserRegularEntryForm userRegularEntryForm() {
        return new UserRegularEntryForm();
    }

    /**
     * ユーザ定時情報登録画面表示処理
     *
     * @param model
     *            Model
     * @param mtPage
     *            定時情報マスタのリクエストページ数
     * @param userMtPage
     *            勤怠ユーザマスタのリクエストページ数
     * @return ユーザ定時情報登録画面View
     */
    @GetMapping("entry")
    public String entry(Model model,
            @RequestParam(name = "mt_page", required = false) String mtPage,
            @RequestParam(name = "user_mt_page", required = false) String userMtPage) {

        Pageable regularMtPageable = PagingFactory.getPageable(mtPage, 5);
        Pageable userMtPageable = PagingFactory.getPageable(userMtPage, 10);

        // 勤怠ユーザマスタのページはそのまま
        model.addAttribute("regularMtPaging",
                PagingFactory.getPageView(regularMtPageable,
                        "/work/userregular/entry?user_mt_page="
                                + userMtPageable.getPageNumber() + "&mt_page",
                        regularWorkMtSearchService.count()));

        // 定時情報マスタのページはそのまま
        model.addAttribute("userMtPaging",
                PagingFactory.getPageView(userMtPageable,
                        "/work/userregular/entry?mt_page="
                                + regularMtPageable.getPageNumber() + "&user_mt_page",
                        workUserMtSearchService.count()));

        List<RegularWorkMt> mtList = regularWorkMtSearchService
                .selectAll(regularMtPageable);
        model.addAttribute("mtList", mtList);

        model.addAttribute("seqLoginIdList", loginUserDataSearchService.selectIdList());
        model.addAttribute("seqRegularWorkMtIdList", mtList.stream()
                .map(RegularWorkMt::getSeqRegularWorkMtId)
                .collect(Collectors.toList()));
        model.addAttribute("workUserCopositeMtList", workUserMtSearchService
                .selectCompositeRegularMt(userMtPageable));

        return AppView.WORK_USER_REGULAR_ENTRY_VIEW.getValue();
    }

    /**
     * ユーザ定時情報登録処理
     *
     * @param model
     *            Model
     * @param form
     *            ユーザ定時情報登録画面Form
     * @param result
     *            validation結果
     * @param redirectAttributes
     *            RedirectAttributes
     * @return ユーザ定時情報登録画面View
     */
    @PostMapping("entry")
    public String entry(Model model, @Validated UserRegularEntryForm form,
            BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return AppView.WORK_USER_REGULAR_ENTRY_VIEW.getValue();
        }

        WorkUserMt workUserMt = modelMapper.map(form, WorkUserMt.class);
        workUserMtCreateService.create(workUserMt);

        redirectAttributes.addFlashAttribute("entrySuccess", "1");

        return AppView.WORK_USER_REGULAR_ENTRY_VIEW.toRedirect();
    }

}
