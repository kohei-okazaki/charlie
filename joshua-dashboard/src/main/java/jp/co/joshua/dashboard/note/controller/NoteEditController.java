package jp.co.joshua.dashboard.note.controller;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.joshua.business.note.dto.NoteDto;
import jp.co.joshua.business.note.service.NoteService;
import jp.co.joshua.common.exception.AppException;
import jp.co.joshua.common.web.view.AppView;
import jp.co.joshua.dashboard.note.form.NoteEditForm;

/**
 * メモ編集コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/note/edit")
public class NoteEditController {

    /** {@linkplain ModelMapper} */
    @Autowired
    private ModelMapper modelMapper;
    /** メモサービス */
    @Autowired
    private NoteService noteService;

    @ModelAttribute
    private NoteEditForm noteEditForm() {
        return new NoteEditForm();
    }

    /**
     * メモ更新画面表示処理
     *
     * @param model
     *            {@linkplain Model}
     * @param seqNoteUserDataId
     *            メモユーザ情報ID
     * @param pageable
     *            {@linkplain Pageable}
     * @param redirectAttributes
     *            {@linkplain RedirectAttributes}
     * @return メモ更新画面
     */
    @GetMapping("/{seqNoteUserDataId}")
    public String edit(Model model,
            @PathVariable("seqNoteUserDataId") Optional<Integer> seqNoteUserDataId,
            @PageableDefault(size = 3, page = 0) Pageable pageable,
            RedirectAttributes redirectAttributes) {

        if (!seqNoteUserDataId.isPresent()) {
            redirectAttributes.addFlashAttribute("errMsg", "リクエスト情報が不正です");
            return AppView.NOTE_LIST_VIEW.toRedirect();
        }

        NoteDto note = noteService.getNote(seqNoteUserDataId.get());
        if (note == null) {
            redirectAttributes.addFlashAttribute("errMsg", "メモが存在しません");
            return AppView.NOTE_LIST_VIEW.toRedirect();
        }

        model.addAttribute("note", note);

        return AppView.NOTE_EDIT_VIEW.getValue();
    }

    /**
     * メモ更新処理
     *
     * @param form
     *            メモ編集Form
     * @param result
     *            {@linkplain BindingResult}
     * @param redirectAttributes
     *            {@linkplain RedirectAttributes}
     * @return メモ更新画面へリダイレクト
     * @throws AppException
     *             S3ファイルのアップロードに失敗しました
     */
    @PostMapping
    public String edit(@Validated NoteEditForm form, BindingResult result,
            RedirectAttributes redirectAttributes) throws AppException {

        if (result.hasErrors()) {
            return AppView.NOTE_EDIT_VIEW.getValue();
        }

        NoteDto dto = modelMapper.map(form, NoteDto.class);
        noteService.editNote(dto);

        redirectAttributes.addFlashAttribute("editSuccess", true);
        redirectAttributes.addFlashAttribute("note", dto);

        return AppView.NOTE_EDIT_VIEW.toRedirect();
    }

}
