package jp.co.joshua.common.bean;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;

/**
 * アプリケーションのログを出力するAspect
 *
 * @version 1.0.0
 */
@Aspect
@Component
public class AppLogAspect {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(AppLogAspect.class);

    @Before("execution(* jp.co.joshua.business.*.component.*.*(..))")
    public void beforeComponent(JoinPoint jp) {
        String logMessage = "CLASS_NAME=" + getClassName(jp)
                + ",METHOD_NAME=" + getSignatureName(jp)
                + ",ARGS=" + getArguments(jp);
        LOG.debug(logMessage);
    }

    @AfterReturning(pointcut = "execution(* jp.co.joshua.business.*.component.*.*(..))", returning = "returnValue")
    public void afterComponent(JoinPoint jp, Object returnValue) {
        String logMessage = "CLASS_NAME=" + getClassName(jp)
                + ",METHOD_NAME=" + getSignatureName(jp)
                + ",ARGS=" + getArguments(jp)
                + ",RETURN=" + getReturnValue(returnValue);
        LOG.debug(logMessage);
    }

    private String getClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName();
    }

    private String getSignatureName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

    private String getArguments(JoinPoint joinPoint) {
        if (joinPoint.getArgs() == null) {
            return "argument is null";
        }

        List<String> argumentStrings = Stream.of(joinPoint.getArgs())
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.toList());

        return String.join(",", argumentStrings);
    }

    private String getReturnValue(Object returnValue) {
        return returnValue == null ? "return value is null" : returnValue.toString();
    }
}
