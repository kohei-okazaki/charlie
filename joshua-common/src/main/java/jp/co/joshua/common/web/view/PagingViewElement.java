package jp.co.joshua.common.web.view;

/**
 * ページングViewの属性情報
 *
 * @version 1.0.0
 */
public class PagingViewElement {

    private String name;
    private String href;

    public PagingViewElement() {
    }

    public PagingViewElement(String name, String href) {
        this.name = name;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
