package horse.latte.controller;


import horse.latte.dto.request.BoardRequestDto;
import horse.latte.dto.response.BoardResponseDto;
import horse.latte.security.UserDetailsImpl;
import horse.latte.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 조회 및 연도별 카테고리 조회
    @GetMapping("/api/boards")
    public ResponseEntity<List<BoardResponseDto>> readBoard(@RequestParam(required = false) String year) {
        if (year != null) {
            return ResponseEntity.ok().body(boardService.getBoardsByYear(year));
        } else {
            return ResponseEntity.ok().body(boardService.getAllBoards());
        }
    }

    @PostMapping("/api/board/write")
    public ResponseEntity createBoard(@RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 회원 테이블의 ID
        return ResponseEntity.ok().body(boardService.createBoard(requestDto, userDetails));
    }

    @PutMapping("/api/board/{id}")
    public ResponseEntity updateBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BoardRequestDto requestDto) {
        return ResponseEntity.ok().body(boardService.update(id, userDetails.getUser().getNickname(), requestDto));
    }

    // 게시글 삭제
    @DeleteMapping("/api/board/{id}")
    public ResponseEntity deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(boardService.delete(id, userDetails.getUser().getNickname()));
    }

    // 상세페이지
    @GetMapping("/api/detail/{id}")
    public ResponseEntity getBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(boardService.getBoard(id, userDetails));
    }
}
