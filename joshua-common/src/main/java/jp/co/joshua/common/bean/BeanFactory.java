package jp.co.joshua.common.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import jp.co.joshua.common.util.BeanUtil;

/**
 * SpringのDIを行うFactory<br>
 * <code>
 * Hoge hoge = BeanFactory.getBean(Hoge.class);
 * </code>
 *
 * @version 1.0.0
 */
public class BeanFactory {

    /** ApplicationContext */
    private static ApplicationContext context;

    /**
     * プライベートコンストラクタ
     */
    private BeanFactory() {
    }

    /**
     * Beanを取得
     *
     * @param clazz
     *            Beanの実装クラス
     * @return Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getContext().getBean(clazz);
    }

    /**
     * ApplicationContextを返す
     *
     * @return ApplicationContext
     */
    private static ApplicationContext getContext() {

        if (BeanUtil.notNull(context)) {
            return context;
        }
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        context = RequestContextUtils.findWebApplicationContext(sra.getRequest());
        return context;
    }

    /**
     * ApplicationContextを設定する
     *
     * @param context
     *            ApplicationContext
     */
    public static void setContext(ApplicationContext context) {
        BeanFactory.context = context;
    }
}
