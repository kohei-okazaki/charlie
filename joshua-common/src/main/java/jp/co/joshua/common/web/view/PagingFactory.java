package jp.co.joshua.common.web.view;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;

/**
 * {@linkplain PagingView}のFactoryクラス
 *
 * @version 1.0.0
 */
public class PagingFactory {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(PagingFactory.class);

    /**
     * プライベートコンストラクタ
     */
    private PagingFactory() {
    }

    /**
     * {@linkplain Pageable}を返す
     *
     * @param strPage
     *            ページ数の文字列表現
     * @param size
     *            1ページあたりに表示させる件数
     * @return Pageable
     * @see #getPageable(int, int)
     */
    public static Pageable getPageable(String strPage, int size) {
        int page = (strPage == null) ? 0 : Integer.parseInt(strPage);
        return getPageable(page, size);
    }

    /**
     * {@linkplain Pageable}を返す
     *
     * @param page
     *            ページ数
     * @param size
     *            1ページあたりに表示させる件数
     * @return Pageable
     */
    public static Pageable getPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    /**
     * 画面のページング用のクラス({@linkplain PagingView})を返す<br>
     * <code>path</code>には遷移先のURLのパスを指定する<br>
     * 例:http://localhost:88/work/userregular/entry?page=nnに遷移したい場合、<br>
     * <b>/work/userregular/entry?page</b>を指定する
     *
     * @param pageable
     *            Pageable
     * @param path
     *            遷移先パスの固定文言
     * @param count
     *            総レコード件数
     * @return PagingView
     */
    public static PagingView getPageView(Pageable pageable, String path, long count) {

        LOG.debug(
                // 要求しているページ番号
                "pageable.getPageNumber()=" + pageable.getPageNumber()
                // 表示件数
                        + ", pageable.getPageSize()=" + pageable.getPageSize()
                        // ↑2つの積
                        + ", pageable.getOffset()=" + pageable.getOffset());

        PagingView pv = new PagingView();

        pv.setCanGoFirst(pageable.getPageNumber() != 0);
        pv.setCanGoPrevious(pageable.getPageNumber() != 0);
        pv.setCanGoNext(
                pageable.getPageNumber() < (count / pageable.getPageSize()));
        pv.setCanGoLast(
                pageable.getPageNumber() < (count / pageable.getPageSize()));

        pv.setCurrentPageNum(pageable.getPageNumber());
        pv.setFirstHref(path + "=" + 0);

        int fromRecordNum = pageable.getPageSize() * pageable.getPageNumber() + 1;
        pv.setFromRecordNum(fromRecordNum);
        pv.setLastHref(
                path + "=" + (count / pageable.getPageSize()));
        pv.setNextHref(
                path + "=" + (pageable.getPageNumber() + 1));
        pv.setPreviousHref(
                path + "=" + (pageable.getPageNumber() - 1));
        pv.setRecordPerPage(5);

        long toRecordNum = pageable.getPageSize() * pageable.getPageNumber()
                + pageable.getPageSize();
        toRecordNum = count < toRecordNum ? count : toRecordNum;
        pv.setToRecordNum(toRecordNum);
        pv.setTotalRecordNum(count);

        return pv;
    }

    /**
     * {@linkplain PagingViewElement}のリストを返す
     *
     * @param currentPageNum
     *            現在のページ数
     * @param totalPageNum
     *            総ページ数
     * @param length
     *            長さ
     * @param preAppendPageNum
     *            遷移先のリンクの接頭語(/hoge/huga)
     * @return PagingViewElementリスト
     */
    @Deprecated
    public static List<PagingViewElement> generatePagingViewElements(
            int currentPageNum,
            int totalPageNum,
            int length,
            String preAppendPageNum) {
        /* 偶数個のリストが要求された場合は現在のページが前寄せになる。
           例) [] がついているのが現在ページ
             << < 1 2 [3] 4 5 6 > >>
        */
        int backSpan = (length - 1) / 2;
        int forthSpan = (length - 1) - backSpan;

        int startIndex;
        int endIndex;

        if (currentPageNum - backSpan < 1) {
            // 表示幅に従うと存在しないページ(0ページ以下)が生成されるので、1ページから始める
            startIndex = 1;
            endIndex = length < totalPageNum ? length : totalPageNum;
        } else if (currentPageNum + forthSpan > totalPageNum) {
            // 表示幅に従うと存在しないページ(最終ページ以降)が生成されるので、表示領域を最終ページから逆算する
            startIndex = totalPageNum - (length - 1) > 1 ? totalPageNum - (length - 1)
                    : 1;
            endIndex = totalPageNum;
        } else {
            // その間なので、中央にcurrentPageNumがくるように配置する。
            // ページのリストの端に当たっていないので、単純に中央にくるような両端を考えればよい。
            startIndex = currentPageNum - backSpan;
            endIndex = currentPageNum + forthSpan;
        }
        return IntStream.range(startIndex, endIndex + 1)
                .mapToObj(n -> new PagingViewElement(String.valueOf(n),
                        preAppendPageNum + (n - 1)))
                // preAppendPageNum + n))
                .collect(Collectors.toList());
    }
}
