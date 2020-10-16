package jp.co.joshua.common.bean;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;
import jp.co.joshua.common.log.MDC;
import jp.co.joshua.common.util.StringUtil;

/**
 * 処理時間をDebugログに出力するInterceptor
 *
 * @version 1.0.0
 */
@Component
public class RequestTrackingInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<Long> START_TIME_HOLDER = new ThreadLocal<>();
    private static final String HEADER_X_TRACK_ID = "X-track-Id";
    private static final Logger LOG = LoggerFactory
            .getLogger(RequestTrackingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {

        if (handler instanceof ResourceHttpRequestHandler) {
            // 静的リソースの場合、トレースを行わない
            return true;
        }

        // 現在時刻を保持
        START_TIME_HOLDER.set(System.nanoTime());

        // トラッキングID
        String trackId = getTrackId(request);
        MDC.put(HEADER_X_TRACK_ID, trackId);
        response.setHeader(HEADER_X_TRACK_ID, trackId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception e) throws Exception {

        Long before = START_TIME_HOLDER.get();
        if (before == null) {
            return;
        }

        Long duration = System.nanoTime() - before;
        TimeUnit.NANOSECONDS.toMillis(duration);
        LOG.info("[URI=" + request.getRequestURI() + "] [METHOD="
                + request.getMethod() + "] [DIFF="
                + TimeUnit.NANOSECONDS.toMillis(duration) + ".ms]");

        START_TIME_HOLDER.remove();
    }

    /**
     * ヘッダ内の<b>X-track-Id</b>が設定されていない場合、新規で乱数を生成し、<b>X-track-Id</b>に設定する
     *
     * @param request
     *            リクエスト
     * @return トラックID
     */
    private String getTrackId(HttpServletRequest request) {
        String trackId = request.getHeader(HEADER_X_TRACK_ID);
        if (StringUtil.isBrank(trackId)) {
            trackId = StringUtil.getRandamStr(15);
        }
        return trackId;
    }

}
