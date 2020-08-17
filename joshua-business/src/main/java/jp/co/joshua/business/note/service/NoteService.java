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

    List<NoteDto> getNoteDtoList(Pageable pageable) throws AppException;

}
