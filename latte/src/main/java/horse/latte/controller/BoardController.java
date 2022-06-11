package horse.latte.controller;

import horse.latte.model.Board;
import horse.latte.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    //게시물 조회
    @GetMapping("/board")
    //required = true 는 기본값, true 일 경우 필수로 year 을 받아와야됨! 배운거다 현석아
    public List<Board> readBoard(@RequestParam(value = "year", required = false) Long year) {
        //만약 연도가 들어온게 있다면
        if (year != null) {
            //연도별 게시물로 조회해라
            return boardService.getYearSearch(year);
        } else {
            //그게 아닌 null값으로 그냥 조회가 된다면 전체조회
            return boardService.getBoard();
        }
    }

    //좋아요 기능 구현해보기          //매개변수에 토큰도 넣어서
    @PostMapping("/board/{id}/like")
    public Long like(@PathVariable Long id){
        System.out.println(id);
        return null;
    }
}


//등록된 전체 게시물 조회
//    @GetMapping("/")
//    public List<Board> getBoard() {
//        return boardService.getBoard();
//    }

//연도별로 분류된 게시물 조회
//    @GetMapping("/{year}")
//    public List<Board> getYearSearch(@RequestParam Long year) {
//        return boardService.getYearSearch(year);
//    }
