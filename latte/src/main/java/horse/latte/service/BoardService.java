package horse.latte.service;

import horse.latte.model.Board;
import horse.latte.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    //전체조회
    public List<Board> getBoard() {
        return boardRepository.findAll();
    }


}
