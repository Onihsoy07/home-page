package com.example.homepage.domain.dto;

import com.example.homepage.domain.entity.Board;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.createAt = board.getCreateAt();
        this.updateAt = board.getUpdateAt();
    }

    public static List<BoardDto> convertToList(Collection<Board> boardList) {
        return boardList.stream().map(BoardDto::new).collect(Collectors.toList());
    }
}
