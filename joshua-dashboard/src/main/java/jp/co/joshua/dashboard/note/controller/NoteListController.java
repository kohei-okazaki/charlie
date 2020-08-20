package jp.co.joshua.dashboard.note.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.joshua.business.db.select.NoteUserDataSearchService;
import jp.co.joshua.business.note.component.NoteComponent;
import jp.co.joshua.business.note.dto.NoteDto;
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
@RequestMapping("/note")
public class NoteListController {

    /** {@linkplain SecurityContextWrapper} */
    @Autowired
    private SecurityContextWrapper securityWrapper;
    /** {@linkplain ModelMapper} */
    @Autowired
    private ModelMapper modelMapper;
    /** {@linkplain NoteUserDataSearchService} */
    @Autowired
    private NoteUserDataSearchService noteUserDataSearchService;
    /** {@linkplain NoteComponent} */
    @Autowired
    private NoteComponent noteComponent;

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
     * @param title
     *            件名
     * @return メモ一覧画面
     * @throws AppException
     *             S3からファイルの取得に失敗した場合
     */
    @GetMapping("/list")
    public String list(Model model,
            @PageableDefault(size = 3, page = 0) Pageable pageable,
            @RequestParam(name = "title", required = false) String title)
            throws AppException {

        List<NoteDto> noteList = noteComponent.getNoteDtoList(title, pageable);
        model.addAttribute("noteList", noteList);

        Integer seqLoginId = securityWrapper.getLoginAuthDto().get().getSeqLoginId();
        model.addAttribute("paging", PagingFactory.getPageView(pageable,
                "/note/list?page", noteUserDataSearchService
                        .countBySeqLoginIdAndLikeTitle(seqLoginId, title)));

        return AppView.NOTE_LIST_VIEW.getValue();
    }

    /**
     * メモ登録処理
     *
     * @param model
     *            {@linkplain Model}
     * @param form
     *            メモ登録Form
     * @param result
     *            {@linkplain BindingResult}
     * @param redirectAttributes
     *            {@linkplain RedirectAttributes}
     * @return メモ一覧画面へリダイレクト
     * @throws AppException
     *             S3へファイルのアップロード失敗した場合
     */
    @PostMapping("/entry")
    public String entry(Model model,
            @Validated NoteEntryForm form, BindingResult result,
            RedirectAttributes redirectAttributes) throws AppException {

        if (result.hasErrors()) {
            return AppView.NOTE_LIST_VIEW.getValue();
        }

        noteComponent.entryNote(modelMapper.map(form, NoteDto.class));

        redirectAttributes.addFlashAttribute("entrySuccess", true);

        return AppView.NOTE_LIST_VIEW.toRedirect();
    }

}
