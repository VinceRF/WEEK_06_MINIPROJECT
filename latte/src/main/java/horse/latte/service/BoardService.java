package horse.latte.service;

import horse.latte.model.Board;
import horse.latte.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    //전체조회
    @Transactional
    public List<Board> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    //게시물 연도별 조회 구현
    public List<Board> getYearSearch(Long year) {
        return boardRepository.findByYearByModifiedAtDesc(year);
    }

//    //연도별 게시물 조회
//    @Transactional
//    public List<Board> getYearSearch(Long year) {
//        return boardRepository.findByYear(year);
//    }



}
