package jp.co.joshua.dashboard.work.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.joshua.business.db.create.DailyWorkEntryDataCreateService;
import jp.co.joshua.business.db.select.DailyWorkEntryDataSearchService;
import jp.co.joshua.business.db.update.DailyWorkEntryDataUpdateService;
import jp.co.joshua.business.work.component.WorkEntryComponent;
import jp.co.joshua.business.work.dto.DailyWorkEntryDataDto;
import jp.co.joshua.business.work.service.MonthlyWorkEntryService;
import jp.co.joshua.common.db.entity.CompositeDailyWorkEntryData;
import jp.co.joshua.common.db.entity.CompositeWorkUserMt;
import jp.co.joshua.common.db.entity.WorkUserMt;
import jp.co.joshua.common.exception.AppException;
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
    /** 勤怠関連Component */
    @Autowired
    private WorkEntryComponent workEntryComponent;
    /** 当月勤怠登録画面サービス */
    @Autowired
    private MonthlyWorkEntryService monthlyWorkEntryService;
    /** 日別勤怠登録情報検索サービス */
    @Autowired
    private DailyWorkEntryDataSearchService dailyWorkEntryDataSearchService;
    /** 日別勤怠登録情報登録サービス */
    @Autowired
    private DailyWorkEntryDataCreateService dailyWorkEntryDataCreateService;
    /** 日別勤怠登録情報更新サービス */
    @Autowired
    private DailyWorkEntryDataUpdateService dailyWorkEntryDataUpdateService;

    @ModelAttribute
    public MonthEntryForm monthEntryForm() {
        return new MonthEntryForm();
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
     * @throws AppException
     *             アプリ例外
     */
    @GetMapping("/entry")
    public String entry(Model model,
            @RequestParam(name = "year", required = false) String year,
            @RequestParam(name = "month", required = false) String month)
            throws AppException {

        // ログイン中のユーザに適用される最新の定時情報マスタを取得
        LoginAuthDto loginAuthDto = (LoginAuthDto) session
                .getAttribute("loginAuthDto");
        CompositeWorkUserMt regularMt = workEntryComponent
                .getActiveRegularMtBySeqLoginId(loginAuthDto.getSeqLoginId());
        model.addAttribute("regularMt", regularMt);

        // 処理対象年月初
        LocalDate targetDate = monthlyWorkEntryService.getTargetDate(year, month);
        model.addAttribute("yearList", monthlyWorkEntryService.getYearList(targetDate));
        model.addAttribute("monthList", monthlyWorkEntryService.getMonthList());
        model.addAttribute("selectedYear", targetDate.getYear());
        model.addAttribute("selectedMonth", targetDate.getMonthValue());

        List<CompositeDailyWorkEntryData> list = dailyWorkEntryDataSearchService
                .getMonthList(targetDate, regularMt.getSeqWorkUserMtId());
        model.addAttribute("thisMonthList", list);

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
     * @param redirectAttributes
     *            リダイレクトパラメータ
     * @return 当月勤怠登録View
     */
    @PostMapping("/entry")
    public String entry(Model model, @Validated MonthEntryForm form,
            BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return AppView.WORK_MONTH_ENTRY_VIEW.getValue();
        }

        LocalDate targetDate = DateUtil.toLocalDate(
                form.getDailyEntryFormList().get(0).getDate(),
                DateFormatType.YYYYMMDD_HYPHEN);
        redirectAttributes.addAttribute("year", targetDate.getYear());
        redirectAttributes.addAttribute("month", targetDate.getMonthValue());

        // ログイン中のユーザに適用される最新の勤怠ユーザマスタを取得
        LoginAuthDto loginAuthDto = (LoginAuthDto) session
                .getAttribute("loginAuthDto");
        WorkUserMt userMt = workEntryComponent
                .getActiveWorkUserMtBySeqLoginId(loginAuthDto.getSeqLoginId());

        // // 既に登録された日別勤怠登録情報を検索
        // List<DailyWorkEntryData> dailyWorkEntryDataList =
        // dailyWorkEntryDataSearchService
        // .getDailyWorkEntryDataList(targetDate, userMt.getSeqWorkUserMtId());

        List<DailyWorkEntryDataDto> dtoList = new ArrayList<>();
        for (DailyEntryForm dailyForm : form.getDailyEntryFormList()) {

            if (dailyForm.getWorkBeginHour() == null
                    || dailyForm.getWorkBeginMinute() == null
                    || dailyForm.getWorkEndHour() == null
                    || dailyForm.getWorkEndMinute() == null) {
                continue;
            }
            DailyWorkEntryDataDto dto = new DailyWorkEntryDataDto();

            LocalDate date = DateUtil.toLocalDate(dailyForm.getDate(),
                    DateFormatType.YYYYMMDD_HYPHEN);

            LocalDateTime begin = LocalDateTime.of(date.getYear(), date.getMonthValue(),
                    date.getDayOfMonth(), dailyForm.getWorkBeginHour(),
                    dailyForm.getWorkBeginMinute());

            LocalDateTime end = LocalDateTime.of(date.getYear(), date.getMonthValue(),
                    date.getDayOfMonth(), dailyForm.getWorkEndHour(),
                    dailyForm.getWorkEndMinute());

            dto.setBegin(begin);
            dto.setEnd(end);
            dtoList.add(dto);
        }

        monthlyWorkEntryService.executeEntry(targetDate, userMt.getSeqWorkUserMtId(),
                dtoList);
        // for (DailyEntryForm dailyForm : form.getDailyEntryFormList()) {
        //
        // if (dailyForm.getWorkBeginHour() == null
        // || dailyForm.getWorkBeginMinute() == null
        // || dailyForm.getWorkEndHour() == null
        // || dailyForm.getWorkEndMinute() == null) {
        // continue;
        // }
        //
        // LocalDate date = DateUtil.toLocalDate(dailyForm.getDate(),
        // DateFormatType.YYYYMMDD_HYPHEN);
        //
        // LocalDateTime begin = LocalDateTime.of(date.getYear(),
        // date.getMonthValue(),
        // date.getDayOfMonth(), dailyForm.getWorkBeginHour(),
        // dailyForm.getWorkBeginMinute());
        //
        // LocalDateTime end = LocalDateTime.of(date.getYear(),
        // date.getMonthValue(),
        // date.getDayOfMonth(), dailyForm.getWorkEndHour(),
        // dailyForm.getWorkEndMinute());
        //
        // boolean isInsert = true;
        //
        // for (DailyWorkEntryData entity : dailyWorkEntryDataList) {
        //
        // if (DateUtil.toLocalDate(entity.getBegin()).equals(date)) {
        // // 更新処理
        // entity.setBegin(begin);
        // entity.setEnd(end);
        // entity.setStatus(WorkAuthStatus.STILL.getValue());
        // dailyWorkEntryDataUpdateService.update(entity);
        // isInsert = false;
        // break;
        // }
        // }
        //
        // if (isInsert) {
        // // 登録処理
        // DailyWorkEntryData entity = new DailyWorkEntryData();
        // entity.setSeqWorkUserMtId(userMt.getSeqWorkUserMtId());
        // entity.setBegin(begin);
        // entity.setEnd(end);
        // entity.setStatus(WorkAuthStatus.STILL.getValue());
        // dailyWorkEntryDataCreateService.create(entity);
        // }
        // }

        return AppView.WORK_MONTH_ENTRY_VIEW.toRedirect();
    }

}
