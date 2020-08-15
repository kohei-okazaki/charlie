package jp.co.joshua.common.bean;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Bean定義を設定するクラス
 *
 * @version 1.0.0
 */
@Configuration
public class BeanConfig implements WebMvcConfigurer {

    /**
     * Beanのコピーを行う
     *
     * @return ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        org.modelmapper.config.Configuration conf = modelMapper.getConfiguration();
        conf.setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addConverter(fromStringToBooleanConverter());
        modelMapper.addConverter(fromStringToIntegerConverter());

        return modelMapper;
    }

    /**
     * ValidatorがAutoConfigurationで作成された{@linkplain MessageSource}を使うようにする
     *
     * @param messageSource
     *            MessageSource
     * @return LocalValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean beanValidator(MessageSource messageSource) {
        LocalValidatorFactoryBean lvfb = new LocalValidatorFactoryBean();
        lvfb.setValidationMessageSource(messageSource);
        return lvfb;
    }

    /**
     * StringをBooleanに変換するConverterクラスを返す
     *
     * @return Converter
     */
    private Converter<String, Boolean> fromStringToBooleanConverter() {
        return new AbstractConverter<String, Boolean>() {
            /** true対象文字列 */
            private final List<String> TRUE_STR_LIST = Arrays.asList("1", "true", "TRUE",
                    "Y");

            @Override
            public Boolean convert(String source) {
                return TRUE_STR_LIST.contains(source);
            }
        };
    }

    /**
     * StringをIntegerに変換するConverterクラスを返す
     *
     * @return Converter
     */
    private Converter<String, Integer> fromStringToIntegerConverter() {
        return new AbstractConverter<String, Integer>() {

            @Override
            protected Integer convert(String source) {
                if (source == null) {
                    return null;
                }
                return Integer.valueOf(source);
            }

        };
    }

}
