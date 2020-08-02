package jp.co.joshua.common.exception;

/**
 * アプリケーション例外クラス
 *
 * @version 1.0.0
 */
public class AppException extends Exception {

    /** serialVersionUID */
    private static final long serialVersionUID = 5772724999554656015L;

    public AppException() {
        super();
    }

    public AppException(String detail) {
        super(detail);
    }

    public AppException(Exception e) {
        super(e);
    }

    public AppException(String detail, Exception e) {
        super(detail, e);
    }

}
