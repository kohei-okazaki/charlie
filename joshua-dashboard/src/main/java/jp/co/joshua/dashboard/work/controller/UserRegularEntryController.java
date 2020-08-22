package jp.co.joshua.dashboard.work.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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

import jp.co.joshua.business.db.create.WorkUserDetailMtCreateService;
import jp.co.joshua.business.db.create.WorkUserHistMtCreateService;
import jp.co.joshua.business.db.create.WorkUserMngMtCreateService;
import jp.co.joshua.business.db.select.LoginUserDataSearchService;
import jp.co.joshua.business.db.select.RegularWorkMtSearchService;
import jp.co.joshua.business.db.select.WorkUserHistMtSearchService;
import jp.co.joshua.business.db.select.WorkUserMngMtSearchService;
import jp.co.joshua.business.db.update.WorkUserMngMtUpdateService;
import jp.co.joshua.common.db.entity.RegularWorkMt;
import jp.co.joshua.common.db.entity.WorkUserDetailMt;
import jp.co.joshua.common.db.entity.WorkUserHistMt;
import jp.co.joshua.common.db.entity.WorkUserMngMt;
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

    /** {@linkplain ModelMapper} */
    @Autowired
    private ModelMapper modelMapper;
    /** {@linkplain LoginUserDataSearchService} */
    @Autowired
    private LoginUserDataSearchService loginUserDataSearchService;
    /** {@linkplain RegularWorkMtSearchService} */
    @Autowired
    private RegularWorkMtSearchService regularWorkMtSearchService;
    /** {@linkplain WorkUserMngMtSearchService} */
    @Autowired
    private WorkUserMngMtSearchService mngMtSearchService;
    /** {@linkplain WorkUserMngMtCreateService} */
    @Autowired
    private WorkUserMngMtCreateService mngMtCreateService;
    /** {@linkplain WorkUserMngMtUpdateService} */
    @Autowired
    private WorkUserMngMtUpdateService mngMtUpdateService;
    /** {@linkplain WorkUserDetailMtCreateService} */
    @Autowired
    private WorkUserDetailMtCreateService detailMtCreateService;
    /** {@linkplain WorkUserHistMtCreateService} */
    @Autowired
    private WorkUserHistMtCreateService histMtCreateService;
    /** {@linkplain WorkUserHistMtSearchService} */
    @Autowired
    private WorkUserHistMtSearchService histMtSearchService;

    @ModelAttribute
    public UserRegularEntryForm userRegularEntryForm() {
        return new UserRegularEntryForm();
    }

    /**
     * ユーザ定時情報登録画面表示処理
     *
     * @param model
     *            {@linkplain Model}
     * @param mtPage
     *            定時情報マスタのリクエストページ数
     * @param histMtPage
     *            勤怠ユーザ履歴マスタのリクエストページ数
     * @return ユーザ定時情報登録画面View
     */
    @GetMapping("entry")
    @PreAuthorize("hasAuthority('00') || hasAuthority('01')")
    public String entry(Model model,
            @RequestParam(name = "mt_page", required = false) String mtPage,
            @RequestParam(name = "hist_mt_page", required = false) String histMtPage) {

        Pageable regularMtPageable = PagingFactory.getPageable(mtPage, 5);
        Pageable histMtPageable = PagingFactory.getPageable(histMtPage, 5);

        // 勤怠ユーザページはそのまま
        model.addAttribute("regularMtPaging",
                PagingFactory.getPageView(regularMtPageable,
                        "/work/userregular/entry?hist_mt_page="
                                + histMtPageable.getPageNumber() + "&mt_page",
                        regularWorkMtSearchService.count()));

        // 定時情報マスタのページはそのまま
        model.addAttribute("histMtPaging",
                PagingFactory.getPageView(histMtPageable,
                        "/work/userregular/entry?mt_page="
                                + regularMtPageable.getPageNumber() + "&hist_mt_page",
                        histMtSearchService.count()));

        List<RegularWorkMt> mtList = regularWorkMtSearchService
                .selectAll(regularMtPageable);
        model.addAttribute("mtList", mtList);

        model.addAttribute("seqLoginIdList", loginUserDataSearchService.selectIdList());
        model.addAttribute("seqRegularWorkMtIdList", mtList.stream()
                .map(RegularWorkMt::getSeqRegularWorkMtId)
                .collect(Collectors.toList()));
        model.addAttribute("histMtList",
                histMtSearchService.selectAllJoinRegularMt(histMtPageable));

        return AppView.WORK_USER_REGULAR_ENTRY_VIEW.getValue();
    }

    /**
     * ユーザ定時情報登録処理
     *
     * @param model
     *            {@linkplain Model}
     * @param form
     *            {@linkplain UserRegularEntryForm}
     * @param result
     *            {@linkplain BindingResult}
     * @param redirectAttributes
     *            {@linkplain RedirectAttributes}
     * @return ユーザ定時情報登録画面View
     */
    @PostMapping("entry")
    @PreAuthorize("hasAuthority('00') || hasAuthority('01')")
    public String entry(Model model, @Validated UserRegularEntryForm form,
            BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return AppView.WORK_USER_REGULAR_ENTRY_VIEW.getValue();
        }

        // 詳細マスタを新規登録
        WorkUserDetailMt detailMt = modelMapper.map(form, WorkUserDetailMt.class);
        detailMtCreateService.create(detailMt);

        WorkUserMngMt mngMt = mngMtSearchService
                .selectBySeqLoginId(form.getSeqLoginId());
        if (mngMt == null) {
            // 管理マスタを新規登録
            mngMt = new WorkUserMngMt();
            mngMt.setSeqLoginId(form.getSeqLoginId());
            mngMt.setSeqWorkUserDetailMtId(detailMt.getSeqWorkUserDetailMtId());
            mngMtCreateService.create(mngMt);
        } else {
            // 管理マスタを更新
            mngMt.setSeqWorkUserDetailMtId(detailMt.getSeqWorkUserDetailMtId());
            mngMtUpdateService.update(mngMt);
        }

        // 履歴マスタを作成
        WorkUserHistMt histMt = modelMapper.map(mngMt, WorkUserHistMt.class);
        histMt.setSeqRegularWorkMtId(detailMt.getSeqRegularWorkMtId());
        histMtCreateService.create(histMt);

        redirectAttributes.addFlashAttribute("entrySuccess", true);

        return AppView.WORK_USER_REGULAR_ENTRY_VIEW.toRedirect();
    }

}
