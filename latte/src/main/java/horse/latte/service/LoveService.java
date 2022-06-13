package horse.latte.service;

import horse.latte.model.Board;
import horse.latte.model.Love;
import horse.latte.model.User;
import horse.latte.repository.BoardRepository;
import horse.latte.repository.LoveRepository;
import horse.latte.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoveService {

    private final LoveRepository loveRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public LoveService(LoveRepository loveRepository, BoardRepository boardRepository) {
        this.loveRepository = loveRepository;
        this.boardRepository = boardRepository;
    }

    //좋아요
    @Transactional
    public boolean lovePosting(Long boardId, UserDetailsImpl userDetails) {

        //로그인한 유저 찾기
        User user = userDetails.getUser();

        //post 여부 확인
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new NullPointerException("게시물이 존재하지 않습니다."));

        //로그인한 유저, 해당 게시글 pk로 좋아요 유무
        Optional<Love> click = loveRepository.findLikesByUserAndBoardId(user, boardId);
        //게시글에 좋아요를 찾아온다.   만약 존재한다면
        if (click.isPresent()) {
            //리포지토리에 클릭한 유저의 정보를 받아 해당 게시물의 좋아요를 지운다.
            loveRepository.deleteLoveByUserAndBoard(click.get().getUser(), board);
            boardRepository.downLoveCount(board.getId());
            return false;
        } else {
            //좋아요
            Love love = new Love(board, user);
            loveRepository.save(love);
            boardRepository.upLoveCount(board.getId());
            return true;
        }
    }
}

