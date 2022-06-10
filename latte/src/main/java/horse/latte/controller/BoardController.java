package horse.latte.controller;

import horse.latte.model.Board;
import horse.latte.repository.BoardRepository;
import horse.latte.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    //등록된 전체 게시물 조회
    @GetMapping("/")
    public List<Board> getBoard() {
        return boardService.getBoard();
    }

    //연도별로 분류된 게시물 조회
    @GetMapping("/year")
    public List<Board> getBoardYear() {
        return null;
    }

    //좋아요 기능 구현해보기          //매개변수에 토큰도 넣어서
    @PostMapping("/{id}/like")
    public Long like(@PathVariable Long id){
        return null;
    }

}
