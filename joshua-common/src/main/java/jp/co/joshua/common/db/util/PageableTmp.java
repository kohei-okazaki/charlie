package jp.co.joshua.common.db.util;

/**
 * DBのページャブル
 *
 * @version 1.0.0
 */
public class PageableTmp {

    /** ページ数 */
    private int page;
    /** 1ページあたりの件数 */
    private int perpage;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

}
