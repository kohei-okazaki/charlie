package jp.co.joshua.business.note.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import jp.co.joshua.business.note.dto.NoteDto;
import jp.co.joshua.common.exception.AppException;

/**
 * メモのサービスインターフェース
 *
 * @version 1.0.0
 */
public interface NoteService {

    List<NoteDto> getNoteDtoList(String title, Pageable pageable) throws AppException;

    void entryNote(NoteDto noteDto) throws AppException;

    NoteDto getNote(Integer seqNoteUserDataId) throws AppException;

    void editNote(NoteDto dto) throws AppException;

}
