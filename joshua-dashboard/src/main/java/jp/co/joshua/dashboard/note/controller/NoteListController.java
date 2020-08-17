package jp.co.joshua.dashboard.note.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.joshua.business.db.select.NoteUserDataSearchService;
import jp.co.joshua.business.note.dto.NoteDto;
import jp.co.joshua.business.note.service.NoteService;
import jp.co.joshua.common.exception.AppException;
import jp.co.joshua.common.web.auth.login.SecurityContextWrapper;
import jp.co.joshua.common.web.view.AppView;
import jp.co.joshua.common.web.view.PagingFactory;
import jp.co.joshua.dashboard.note.form.NoteEntryForm;

/**
 * メモ一覧コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/note/list")
public class NoteListController {

    /** 認証情報ラッパー */
    @Autowired
    private SecurityContextWrapper securityWrapper;
    /** メモユーザ情報検索サービス */
    @Autowired
    private NoteUserDataSearchService noteUserDataSearchService;
    /** メモサービス */
    @Autowired
    private NoteService noteService;

    @ModelAttribute
    private NoteEntryForm noteEntryForm() {
        return new NoteEntryForm();
    }

    /**
     * メモ一覧画面表示処理
     *
     * @param model
     *            {@linkplain Model}
     * @param pageable
     *            {@linkplain Pageable}
     * @return メモ一覧画面
     * @throws AppException
     *             S3からファイルの取得に失敗した場合
     */
    @GetMapping
    public String list(Model model,
            @PageableDefault(size = 10, page = 0) Pageable pageable) throws AppException {

        List<NoteDto> noteList = noteService.getNoteDtoList(pageable);
        model.addAttribute("noteList", noteList);

        Integer seqLoginId = securityWrapper.getLoginAuthDto().get().getSeqLoginId();
        model.addAttribute("paging", PagingFactory.getPageView(pageable,
                "/note/list?page",
                noteUserDataSearchService.countBySeqLoginId(seqLoginId)));

        return AppView.NOTE_LIST_VIEW.getValue();
    }

}
