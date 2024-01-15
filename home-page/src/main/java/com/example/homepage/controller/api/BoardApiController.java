package com.example.homepage.controller.api;

import com.example.homepage.domain.dto.BoardDto;
import com.example.homepage.domain.dto.BoardSaveDto;
import com.example.homepage.domain.dto.BoardUpdateDto;
import com.example.homepage.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping
    @Tag(name = "게시물 저장", description = "게시물 저장 가능")
    @Operation(method = "POST", description = "게시물 저장", parameters = {
            @Parameter(in = ParameterIn.DEFAULT, name = "data", required = true, schema = @Schema(implementation = BoardSaveDto.class))
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "SERVER ERROR")
    })
    public ResponseEntity<Void> saveBoard(@Valid @RequestBody final BoardSaveDto boardSaveDto,
                                          BindingResult bindingResult) {
        boardService.saveBoard(boardSaveDto);
        return ResponseEntity.created(URI.create("/boards")).build();
    }

    @GetMapping
    @Tag(name = "모든 게시물 조회", description = "모든 게시물 조회 가능")
    public ResponseEntity<List<BoardDto>> getAllBoard() {
        List<BoardDto> boardDtoList = boardService.getList();
        return ResponseEntity.status(HttpStatus.OK).body(boardDtoList);
    }

    @GetMapping("/{boardId}")
    @Tag(name = "게시물 단일 조회", description = "게시물 ID로 조회 가능")
    @Parameter(name = "boardId", example = "1", description = "게시물 아이디")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BoardDto.class))),
            @ApiResponse(responseCode = "500", description = "SERVER ERROR")
    })
    public ResponseEntity<BoardDto> getBoard(@PathVariable("boardId") final Long boardId) {
        BoardDto boardDto = boardService.getBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(boardDto);
    }

    @PutMapping("/{boardId}")
    @Tag(name = "게시물 변경", description = "게시물 제목, 내용 변경 가능")
    public ResponseEntity<Void> updateBoard(@PathVariable("boardId") final Long boardId,
                                            @Valid @RequestBody final BoardUpdateDto boardUpdateDto,
                                            BindingResult bindingResult) {
        boardService.updateBoard(boardId, boardUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{boardId}")
    @Tag(name = "게시물 삭제", description = "게시물 삭제 가능")
    public ResponseEntity<Void> deleteBoard(@PathVariable("boardId") final Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
