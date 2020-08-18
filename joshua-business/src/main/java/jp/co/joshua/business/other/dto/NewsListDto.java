package jp.co.joshua.business.other.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**
 * お知らせ一覧画面のDto
 *
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsListDto {

    /** お知らせ情報一覧リスト */
    @JsonProperty("news_list")
    private List<NewsDto> newsDtoList;

    public List<NewsDto> getNewsDtoList() {
        return newsDtoList;
    }

    public void setNewsDtoList(List<NewsDto> newsDtoList) {
        this.newsDtoList = newsDtoList;
    }

    /**
     * お知らせ画面の1レコードあたりの情報
     *
     * @version 1.0.0
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NewsDto {

        /** 順序 */
        @JsonProperty("index")
        private Integer index;
        /** タイトル */
        @JsonProperty("title")
        private String title;
        /** 日付 */
        @JsonProperty("date")
        @JsonFormat(pattern = "yyyy/MM/dd")
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate date;
        /** 詳細 */
        @JsonProperty("detail")
        private String detail;
        /** タグ色 */
        @JsonProperty("tag_color")
        private String tagColor;
        /** タグ名 */
        @JsonProperty("tag_name")
        private String tagName;

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTagColor() {
            return tagColor;
        }

        public void setTagColor(String tagColor) {
            this.tagColor = tagColor;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

    }
}
