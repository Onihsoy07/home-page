package com.example.homepage.service;

import com.example.homepage.domain.dto.BoardDto;
import com.example.homepage.domain.dto.BoardSaveDto;
import com.example.homepage.domain.dto.BoardUpdateDto;
import com.example.homepage.domain.entity.Board;
import com.example.homepage.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public void saveBoard(BoardSaveDto boardSaveDto) {
        Board board = Board.builder()
                .title(boardSaveDto.getTitle())
                .contents(boardSaveDto.getContents())
                .build();
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public List<BoardDto> getList() {
        return BoardDto.convertToList(boardRepository.findAll());
    }

    @Transactional(readOnly = true)
    public BoardDto getBoard(Long boardId) {
        return new BoardDto(findByBoardEntity(boardId));
    }

    @Transactional(readOnly = true)
    public Board findByBoardEntity(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> {
            throw new IllegalArgumentException(String.format("Board ID %d로 찾을 수 없습니다.", boardId));
        });
    }

    public void updateBoard(Long boardId, BoardUpdateDto boardUpdateDto) {
        findByBoardEntity(boardId).updateBoard(boardUpdateDto);
    }

    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

}
