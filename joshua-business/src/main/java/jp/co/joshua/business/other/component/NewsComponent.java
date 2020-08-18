package jp.co.joshua.business.other.component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.joshua.business.other.dto.NewsListDto;
import jp.co.joshua.business.other.dto.NewsListDto.NewsDto;
import jp.co.joshua.common.aws.AwsS3Wrapper;
import jp.co.joshua.common.exception.AppException;
import jp.co.joshua.common.io.json.JsonReader;

/**
 * お知らせ画面のComponent
 *
 * @version 1.0.0
 */
@Component
public class NewsComponent {

    /** {@linkplain AwsS3Wrapper} */
    @Autowired
    private AwsS3Wrapper awsS3Wrapper;

    public List<NewsDto> getNewsDtoList() throws AppException {

        try (InputStream is = awsS3Wrapper.getS3ObjectByKey("news/news.json")) {

            List<NewsDto> newsList = new JsonReader().read(is, NewsListDto.class)
                    .getNewsDtoList();

            return newsList.stream()
                    .sorted(Comparator.comparing(NewsDto::getIndex).reversed())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new AppException(e);
        }
    }

}
