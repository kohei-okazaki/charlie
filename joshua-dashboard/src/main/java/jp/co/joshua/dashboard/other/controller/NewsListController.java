package jp.co.joshua.dashboard.other.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.joshua.business.other.component.NewsComponent;
import jp.co.joshua.business.other.dto.NewsListDto.NewsDto;
import jp.co.joshua.common.exception.AppException;
import jp.co.joshua.common.util.CollectionUtil;
import jp.co.joshua.common.web.view.AppView;
import jp.co.joshua.common.web.view.PagingFactory;

/**
 * お知らせ一覧画面Controller
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/news/list")
public class NewsListController {

    /** {@linkplain NewsComponent} */
    @Autowired
    private NewsComponent newsComponent;

    /**
     * お知らせ一覧画面表示処理
     *
     * @param model
     *            {@linkplain Model}
     * @param pageable
     *            {@linkplain Pageable}
     * @return お知らせ画面View
     * @throws AppException
     *             S3からお知らせJSONファイルの取得に失敗した場合
     */
    @GetMapping
    public String list(Model model,
            @PageableDefault(size = 5, page = 0) Pageable pageable) throws AppException {

        List<NewsDto> list = newsComponent.getNewsDtoList();
        model.addAttribute("paging", PagingFactory.getPageView(pageable,
                "/news/list?page", list.size()));

        model.addAttribute("newsList", CollectionUtil.getListByPageable(list, pageable));

        return AppView.NEWS_LIST_VIEW.getValue();
    }
}
