package jp.co.joshua.dashboard.work.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.joshua.business.db.select.DailyWorkEntryDataSearchService;
import jp.co.joshua.business.work.component.WorkEntryComponent;
import jp.co.joshua.common.exception.AppException;
import jp.co.joshua.common.web.view.AppView;

/**
 * 勤怠承認画面のController<br>
 * <ul>
 * <li>勤怠承認画面-ユーザ一覧</li>
 * <li>勤怠承認画面-月別勤怠一覧</li>
 * </ul>
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/work/auth")
public class WorkAuthController {

    /** 勤怠関連Component */
    @Autowired
    private WorkEntryComponent workEntryComponent;
    /** 日別勤怠登録情報検索サービス */
    @Autowired
    private DailyWorkEntryDataSearchService dailyWorkEntryDataSearchService;

    /**
     * ユーザ一覧表示処理
     *
     * @param model
     *            Model
     * @param year
     *            処理対象年
     * @param month
     *            処理対象月
     * @param pageable
     *            Pageable
     * @return ユーザ一覧表示画面
     * @throws AppException
     *             日付変換に失敗した場合
     */
    @GetMapping("userlist")
    public String userList(Model model,
            @RequestParam(name = "year", required = false) String year,
            @RequestParam(name = "month", required = false) String month,
            @PageableDefault(size = 10, page = 0) Pageable pageable)
            throws AppException {

        LocalDate date = workEntryComponent.getTargetDate(year, month);
        model.addAttribute("yearList", workEntryComponent.getYearList(date));
        model.addAttribute("monthList", workEntryComponent.getMonthList());
        model.addAttribute("selectedYear", date.getYear());
        model.addAttribute("selectedMonth", date.getMonthValue());
        model.addAttribute("userList", dailyWorkEntryDataSearchService
                .selectStatusList(date, pageable));

        return AppView.WORK_AUTH_USER_LIST.getValue();
    }

}
