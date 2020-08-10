package jp.co.joshua.dashboard.work.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.joshua.business.db.select.DailyWorkEntryDataSearchService;
import jp.co.joshua.business.db.update.DailyWorkEntryDataUpdateService;
import jp.co.joshua.business.work.component.WorkEntryComponent;
import jp.co.joshua.common.db.entity.CompositeDailyWorkAuthStatusData;
import jp.co.joshua.common.db.entity.DailyWorkEntryData;
import jp.co.joshua.common.db.type.WorkAuthStatus;
import jp.co.joshua.common.exception.AppException;
import jp.co.joshua.common.web.view.AppView;
import jp.co.joshua.common.web.view.PagingFactory;

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
    /** 日別勤怠登録情報更新サービス */
    @Autowired
    private DailyWorkEntryDataUpdateService dailyWorkEntryDataUpdateService;

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
    @PreAuthorize("hasAuthority('00') || hasAuthority('01')")
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

        List<CompositeDailyWorkAuthStatusData> list = dailyWorkEntryDataSearchService
                .selectStatusList(date, pageable);
        model.addAttribute("userList", list);
        model.addAttribute("paging", PagingFactory.getPageView(pageable,
                "/work/regular/entry?page", list.size()));

        return AppView.WORK_AUTH_USER_LIST.getValue();
    }

    /**
     * 月別勤怠一覧画面表示処理
     *
     * @param model
     *            Model
     * @param seqLoginId
     *            ログインID
     * @param year
     *            指定年
     * @param month
     *            指定月
     * @return 月別勤怠一覧画面
     * @throws AppException
     *             日付変換に失敗した場合
     */
    @GetMapping("monthly")
    @PreAuthorize("hasAuthority('00') || hasAuthority('01')")
    public String monthly(Model model,
            @RequestParam("seq_login_id") String seqLoginId,
            @RequestParam(name = "year", required = false) String year,
            @RequestParam(name = "month", required = false) String month)
            throws AppException {

        LocalDate date = workEntryComponent.getTargetDate(year, month);
        model.addAttribute("yearList", workEntryComponent.getYearList(date));
        model.addAttribute("monthList", workEntryComponent.getMonthList());
        model.addAttribute("selectedYear", date.getYear());
        model.addAttribute("selectedMonth", date.getMonthValue());
        model.addAttribute("seqLoginId", seqLoginId);
        model.addAttribute("authDataList", dailyWorkEntryDataSearchService
                .selectAuthTargetDataList(Integer.valueOf(seqLoginId), date));

        return AppView.WORK_AUTH_MONTHLY.getValue();
    }

    /**
     * 承認処理
     *
     * @param model
     *            Model
     * @param seqDailyWorkEntryDataId
     *            日別勤怠登録情報ID
     * @param seqLoginId
     *            ログインID
     * @param year
     *            指定年
     * @param month
     *            指定月
     * @param redirectAttributes
     *            RedirectAttributes
     * @return 勤怠承認画面-月別勤怠一覧
     * @throws AppException
     *             日別勤怠登録情報が存在しない場合
     */
    @GetMapping("done/{seqDailyWorkEntryDataId}")
    @PreAuthorize("hasAuthority('00') || hasAuthority('01')")
    public String done(Model model,
            @PathVariable("seqDailyWorkEntryDataId") String seqDailyWorkEntryDataId,
            @RequestParam(name = "seq_login_id") String seqLoginId,
            @RequestParam(name = "year", required = false) String year,
            @RequestParam(name = "month", required = false) String month,
            RedirectAttributes redirectAttributes) throws AppException {

        DailyWorkEntryData entity = dailyWorkEntryDataSearchService
                .selectById(Integer.valueOf(seqDailyWorkEntryDataId));

        if (entity == null) {
            throw new AppException("指定した日別勤怠登録情報が存在しない.seqDailyWorkEntryDataId="
                    + seqDailyWorkEntryDataId);
        } else if (WorkAuthStatus.STILL != entity.getWorkAuthStatus()) {
            throw new AppException("指定した日別勤怠登録情報のステータスが一致しない.seqDailyWorkEntryDataId="
                    + seqDailyWorkEntryDataId + ",workAuthStatus="
                    + entity.getWorkAuthStatus());
        }

        dailyWorkEntryDataUpdateService.updateAuthDone(entity);

        redirectAttributes.addAttribute("seq_login_id", seqLoginId);
        redirectAttributes.addAttribute("year", year);
        redirectAttributes.addAttribute("month", month);

        redirectAttributes.addFlashAttribute("doneSuccess", true);

        return AppView.WORK_AUTH_MONTHLY.toRedirect();
    }

    /**
     * 承認却下処理
     *
     * @param model
     *            Model
     * @param seqDailyWorkEntryDataId
     *            日別勤怠登録情報ID
     * @param seqLoginId
     *            ログインID
     * @param year
     *            指定年
     * @param month
     *            指定月
     * @param redirectAttributes
     *            RedirectAttributes
     * @return 勤怠承認画面-月別勤怠一覧
     * @throws AppException
     *             日別勤怠登録情報が存在しない場合
     */
    @GetMapping("reject/{seqDailyWorkEntryDataId}")
    @PreAuthorize("hasAuthority('00') || hasAuthority('01')")
    public String reject(Model model,
            @PathVariable("seqDailyWorkEntryDataId") String seqDailyWorkEntryDataId,
            @RequestParam(name = "seq_login_id") String seqLoginId,
            @RequestParam(name = "year", required = false) String year,
            @RequestParam(name = "month", required = false) String month,
            RedirectAttributes redirectAttributes) throws AppException {

        DailyWorkEntryData entity = dailyWorkEntryDataSearchService
                .selectById(Integer.valueOf(seqDailyWorkEntryDataId));

        if (entity == null) {
            throw new AppException("指定した日別勤怠登録情報が存在しない.seqDailyWorkEntryDataId="
                    + seqDailyWorkEntryDataId);
        } else if (WorkAuthStatus.DONE != entity.getWorkAuthStatus()) {
            throw new AppException("指定した日別勤怠登録情報のステータスが一致しない.seqDailyWorkEntryDataId="
                    + seqDailyWorkEntryDataId + ",workAuthStatus="
                    + entity.getWorkAuthStatus());
        }

        dailyWorkEntryDataUpdateService.updateAuthReject(entity);

        redirectAttributes.addAttribute("seq_login_id", seqLoginId);
        redirectAttributes.addAttribute("year", year);
        redirectAttributes.addAttribute("month", month);

        redirectAttributes.addFlashAttribute("rejestSuccess", true);

        return AppView.WORK_AUTH_MONTHLY.toRedirect();
    }

}
