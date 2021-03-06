package jp.co.joshua.common.log;

/**
 * Loggerのファクトリークラス
 *
 * @version 1.0.0
 */
public class LoggerFactory {

    /**
     * プライベートコンストラクタ
     */
    private LoggerFactory() {
    }

    /**
     * <code>Logger</code>を返す
     *
     * @param clazz
     *            クラス型
     * @return <code>Logger</code>
     */
    public static Logger getLogger(Class<?> clazz) {
        return new Logger(org.slf4j.LoggerFactory.getLogger(clazz));
    }

}
