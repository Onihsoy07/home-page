package com.example.homepage.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardSaveDto {

    @NotBlank(message = "제목을 입력해주세요")
    @Schema(description = "게시물 제목", nullable = false, example = "안녕하세요")
    private String title;

    @NotBlank(message = "내용을 입력해주세요")
    @Schema(description = "게시물 내용", nullable = false, example = "안녕하세요 내용 ~~")
    private String contents;

}
