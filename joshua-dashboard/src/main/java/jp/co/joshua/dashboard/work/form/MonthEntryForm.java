package jp.co.joshua.dashboard.work.form;

import java.util.ArrayList;
import java.util.List;

/**
 * 当月勤怠登録Form
 *
 * @version 1.0.0
 */
public class MonthEntryForm {

    /** 1日あたりの勤怠データのリスト */
    private List<DailyEntryForm> dailyEntryFormList = new ArrayList<>();

    public List<DailyEntryForm> getDailyEntryFormList() {
        return dailyEntryFormList;
    }

    public void setDailyEntryFormList(List<DailyEntryForm> dailyEntryFormList) {
        this.dailyEntryFormList = dailyEntryFormList;
    }

}
