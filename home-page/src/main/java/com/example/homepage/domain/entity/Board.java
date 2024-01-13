package com.example.homepage.domain.entity;

import com.example.homepage.domain.dto.BoardUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = "home_page")
public class Board extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = false)
    private String title;

    @Lob
    @Column(nullable = false, unique = false)
    private String contents;

    @Builder
    public Board(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void updateBoard(BoardUpdateDto boardUpdateDto) {
        this.title = boardUpdateDto.getTitle();
        this.contents = boardUpdateDto.getContents();
    }

}