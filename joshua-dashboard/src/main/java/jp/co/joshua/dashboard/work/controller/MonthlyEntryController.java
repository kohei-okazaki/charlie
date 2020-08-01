package jp.co.joshua.dashboard.work.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.joshua.business.db.create.DailyWorkEntryDataCreateService;
import jp.co.joshua.business.db.select.DailyWorkEntryDataSearchService;
import jp.co.joshua.business.db.select.WorkUserMtSearchService;
import jp.co.joshua.business.db.update.DailyWorkEntryDataUpdateService;
import jp.co.joshua.business.work.service.MonthlyWorkEntryService;
import jp.co.joshua.common.db.entity.CompositeWorkUserMt;
import jp.co.joshua.common.db.entity.DailyWorkEntryData;
import jp.co.joshua.common.db.entity.WorkUserMt;
import jp.co.joshua.common.util.DateUtil;
import jp.co.joshua.common.util.DateUtil.DateFormatType;
import jp.co.joshua.common.web.auth.login.LoginAuthDto;
import jp.co.joshua.common.web.view.AppView;
import jp.co.joshua.dashboard.work.form.DailyEntryForm;
import jp.co.joshua.dashboard.work.form.MonthEntryForm;

/**
 * 当月勤怠登録コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/work/month")
public class MonthlyEntryController {

    @Autowired
    private HttpSession session;
    /** 当月勤怠登録画面サービス */
    @Autowired
    private MonthlyWorkEntryService monthlyWorkEntryService;
    /** 日別勤怠登録情報検索サービス */
    @Autowired
    private DailyWorkEntryDataSearchService dailyWorkEntryDataSearchService;
    /** 日別勤怠登録情報登録サービス */
    @Autowired
    private DailyWorkEntryDataCreateService dailyWorkEntryDataCreateService;
    /** 日別勤怠登録情報登録更新サービス */
    @Autowired
    private DailyWorkEntryDataUpdateService dailyWorkEntryDataUpdateService;
    /** 勤怠ユーザマスタ検索サービス */
    @Autowired
    private WorkUserMtSearchService workUserMtSearchService;

    @ModelAttribute
    public MonthEntryForm monthEntryForm() {
        MonthEntryForm form = new MonthEntryForm();
        return form;
    }

    /**
     * 当月勤怠登録画面表示
     *
     * @param model
     *            Model
     * @param year
     *            指定年
     * @param month
     *            指定月
     * @return 当月勤怠登録View
     */
    @GetMapping("/entry")
    public String entry(Model model,
            @RequestParam(name = "year", required = false) String year,
            @RequestParam(name = "month", required = false) String month) {

        LoginAuthDto loginAuthDto = (LoginAuthDto) session
                .getAttribute("loginAuthDto");
        // ログイン中のユーザに適用される最新の定時情報マスタを取得
        CompositeWorkUserMt regularMt = workUserMtSearchService
                .selectByLoginIdAndMaxWorkUserMtId(loginAuthDto.getSeqLoginId());
        model.addAttribute("regularMt", regularMt);

        // 処理対象年月初
        LocalDate targetDate = monthlyWorkEntryService.getTargetDate(year, month);

        model.addAttribute("thisMonthList", dailyWorkEntryDataSearchService
                .getMonthList(targetDate, regularMt.getSeqWorkUserMtId()));

        return AppView.WORK_MONTH_ENTRY_VIEW.getValue();
    }

    /**
     * 当月勤怠登録処理
     *
     * @param model
     *            Model
     * @param form
     *            当月勤怠登録Form
     * @param result
     *            validation結果
     * @return 当月勤怠登録View
     */
    @PostMapping("/entry")
    public String entry(Model model, @Validated MonthEntryForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return AppView.WORK_MONTH_ENTRY_VIEW.getValue();
        }

        LoginAuthDto loginAuthDto = (LoginAuthDto) session
                .getAttribute("loginAuthDto");

        LocalDate targetDate = DateUtil.toLocalDate(
                form.getDailyEntryFormList().get(0).getDate(),
                DateFormatType.YYYYMMDD_HYPHEN);
        // ログイン中のユーザに適用される最新の勤怠ユーザマスタを取得
        WorkUserMt userMt = workUserMtSearchService
                .selectActiveBySeqLoginId(loginAuthDto.getSeqLoginId());

        // 既に登録された日別勤怠登録情報を検索
        List<DailyWorkEntryData> dailyWorkEntryDataList = dailyWorkEntryDataSearchService
                .getDailyWorkEntryDataList(targetDate, userMt.getSeqWorkUserMtId());

        for (DailyEntryForm dailyForm : form.getDailyEntryFormList()) {
            LocalDate formBeginDate = DateUtil.toLocalDate(dailyForm.getDate(),
                    DateFormatType.YYYYMMDD_HYPHEN);
            boolean isInsert = true;
            for (DailyWorkEntryData entity : dailyWorkEntryDataList) {

                if (DateUtil.toLocalDate(entity.getBegin()).equals(formBeginDate)) {
                    // 更新処理
                    // TODO update処理
                    isInsert = false;
                    break;
                }
            }

            if (isInsert) {
                // 登録処理
            }
        }

        return AppView.WORK_MONTH_ENTRY_VIEW.toRedirect();
    }

}
