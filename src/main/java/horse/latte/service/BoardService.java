package horse.latte.service;

import horse.latte.dto.BoardRequestDto;
import horse.latte.model.Board;
import horse.latte.model.User;
import horse.latte.repository.BoardRepository;
import horse.latte.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    @Autowired
    private final BoardRepository boardRepository;

    public Board createBoard(BoardRequestDto requestDto, UserDetailsImpl userDetails) {
        //요정받은 DTO로 DB에 저장할 객체 만들기
        Board board = new Board(requestDto, userDetails);
        return  boardRepository.save(board);
    }

    @Transactional
    public Long update(Long id, String username, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        if (board.getUsername().equals(username)) {
            board.update(requestDto, id);
            boardRepository.save(board);
            return id;
        } else{
            throw new IllegalArgumentException("수정 불가능한 게시글입니다.");
        }
    }

    public Long delete(Long id, String username) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        if (board.getUsername().equals(username)) {
            board.delete(id);
            boardRepository.delete(board);
            return id;
        } else {
            throw new IllegalArgumentException("삭제 불가능한 게시글입니다.");
        }
    }

    public Board getBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()) {
            return board.get();
        } else {
            throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
        }
    }

//    public List<Board> getAllBoards() {
//        return boardRepository.findAllByOrderByModifiedAtDesc();
//    }
//
//    public List<Board> getBoardsByYear(Long year){
//        return boardRepository.findAllByYear(year);
//    }
}
