package jp.co.joshua.common.web.view;

/**
 * ページングViewクラス
 *
 * @version 1.0.0
 */
public class PagingView {

    private long totalRecordNum;
    private int fromRecordNum;
    private long toRecordNum;

    private int currentPageNum;
    private int recordPerPage;

    private boolean canGoNext;
    private String nextHref;
    private boolean canGoPrevious;
    private String previousHref;

    private boolean canGoLast;
    private String lastHref;
    private boolean canGoFirst;
    private String firstHref;

    // private List<PagingViewElement> pagingViewElements;

    public boolean isCanGoNext() {
        return canGoNext;
    }

    public long getTotalRecordNum() {
        return totalRecordNum;
    }

    public void setTotalRecordNum(long totalRecordNum) {
        this.totalRecordNum = totalRecordNum;
    }

    public int getFromRecordNum() {
        return fromRecordNum;
    }

    public void setFromRecordNum(int fromRecordNum) {
        this.fromRecordNum = fromRecordNum;
    }

    public long getToRecordNum() {
        return toRecordNum;
    }

    public void setToRecordNum(long toRecordNum) {
        this.toRecordNum = toRecordNum;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getRecordPerPage() {
        return recordPerPage;
    }

    public void setRecordPerPage(int recordPerPage) {
        this.recordPerPage = recordPerPage;
    }

    public void setCanGoNext(boolean canGoNext) {
        this.canGoNext = canGoNext;
    }

    public String getNextHref() {
        return nextHref;
    }

    public void setNextHref(String nextHref) {
        this.nextHref = nextHref;
    }

    public boolean isCanGoPrevious() {
        return canGoPrevious;
    }

    public void setCanGoPrevious(boolean canGoPrevious) {
        this.canGoPrevious = canGoPrevious;
    }

    public String getPreviousHref() {
        return previousHref;
    }

    public void setPreviousHref(String previousHref) {
        this.previousHref = previousHref;
    }

    public boolean isCanGoLast() {
        return canGoLast;
    }

    public void setCanGoLast(boolean canGoLast) {
        this.canGoLast = canGoLast;
    }

    public String getLastHref() {
        return lastHref;
    }

    public void setLastHref(String lastHref) {
        this.lastHref = lastHref;
    }

    public boolean isCanGoFirst() {
        return canGoFirst;
    }

    public void setCanGoFirst(boolean canGoFirst) {
        this.canGoFirst = canGoFirst;
    }

    public String getFirstHref() {
        return firstHref;
    }

    public void setFirstHref(String firstHref) {
        this.firstHref = firstHref;
    }

    // public List<PagingViewElement> getPagingViewElements() {
    // return pagingViewElements;
    // }

    // public void setPagingViewElements(List<PagingViewElement>
    // pagingViewElements) {
    // this.pagingViewElements = pagingViewElements;
    // }

}
