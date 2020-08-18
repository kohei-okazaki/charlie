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
import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;
import jp.co.joshua.common.util.CollectionUtil;
import jp.co.joshua.common.web.view.AppView;
import jp.co.joshua.common.web.view.PagingFactory;

/**
 * お知らせ画面Controller
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/news/list")
public class NewsController {

    private static final Logger LOG = LoggerFactory.getLogger(NewsController.class);

    /** {@linkplain NewsComponent} */
    @Autowired
    private NewsComponent newsComponent;

    /**
     * お知らせ画面表示処理
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
        LOG.debug("PageNumber=" + pageable.getPageNumber());
        LOG.debug("PageSize=" + pageable.getPageSize());
        LOG.debug("Offset=" + pageable.getOffset());
        model.addAttribute("paging", PagingFactory.getPageView(pageable,
                "/news/list?page", list.size()));

        list = CollectionUtil.getListByPageable(list, pageable);
        model.addAttribute("newsList", list);

        return AppView.NEWS_LIST_VIEW.getValue();
    }
}
