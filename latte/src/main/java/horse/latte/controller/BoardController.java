package horse.latte.controller;


import horse.latte.dto.BoardRequestDto;
import horse.latte.model.Board;
import horse.latte.model.User;
import horse.latte.repository.BoardRepository;
import horse.latte.security.UserDetailsImpl;
import horse.latte.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 조회 및 연도별 카테고리 조회
    @GetMapping("/api/boards")
    public List<Board> readBoard(@RequestParam(required = false) Long year) {
        if (year != null) {
            return boardService.getBoardsByYear(year);
        } else {
            return boardService.getAllBoards();
        }
    }

    @PostMapping("/api/board/write")
    public Board createBoard(@RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 회원 테이블의 ID
        return boardService.createBoard(requestDto, userDetails);
    }

    @PutMapping("/api/board/{id}")
    public Long updateBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BoardRequestDto requestDto) {
        return boardService.update(id, userDetails.getUsername(), requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/board/{id}")
    public Long deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.delete(id, userDetails.getUsername());
    }

    // 상세페이지
    @GetMapping("/api/board/{id}")
    public Board getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

}
