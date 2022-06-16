package horse.latte.service;


import horse.latte.model.Board;
import horse.latte.model.Love;
import horse.latte.model.User;
import horse.latte.repository.BoardRepository;
import horse.latte.repository.LoveRepository;
import horse.latte.repository.UserRepository;
import horse.latte.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class LoveService {

    private final LoveRepository loveRepository;
    private final BoardRepository boardRepository;

    //좋아요
    @Transactional
    public boolean clickLove(Long id, UserDetailsImpl userDetails){
        //로그인한 유저 찾아오자
        User user = userDetails.getUser();
        //post 존재여부 확인 -> 현재 게시글 1,2,3..
        Board board  = boardRepository.findById(id).orElseThrow(() -> new NullPointerException("게시물이 존재하지 않습니다."));
        //로그인한 유저 , 해당 게시글 pk로 좋아요 유무 확인
        //1번 게시글에서 유저가 좋아요 후 취소를 누름 ,
        Optional<Love> click = loveRepository.findLovesByUserAndBoardId(user, id);
        //조건에 해당하는 like 를 찾아온다.
        if(click.isPresent()){
            //클릭이 존재하니 , -> 1번 게시글의 정보와 클릭한 유저의 정보를 받아와 해당하는 좋아요를 지운다.
            loveRepository.deleteLovesByUserAndBoard(click.get().getUser(),board);
            boardRepository.downlikeCount(board.getId());
            return false;
        } else {
            //좋아요 하기
            Love love = new Love(board,user);
            loveRepository.save(love);
            boardRepository.uplikeCount(board.getId());
            return true;
        }

    }


//    @Transactional
//    public ResponseEntity love(Long boardId, String username){
//        User user = userRepository.findByUsername(username).orElseThrow(
//                () -> new ApiRequestException("유저가 없습니다.")
//        );
//
//        Board board = boardRepository.findById(boardId).orElseThrow(
//                () -> new ApiRequestException("글번호가 없습니다.")
//        );
//
//        Love findLove = loveRepository.findByUserAndBoard(user, board).orElse(null);
//
//        if(findLove == null){
//            LoveRequestDto loveRequestDto = new LoveRequestDto(user, board);
//            Love love = new Love(loveRequestDto);
//            loveRepository.save(love);
//            return new ResponseEntity("좋아요",HttpStatus.OK);
//        } else {
//            loveRepository.deleteByboard(findLove.getBoard());
//            return new ResponseEntity("좋아요 취소",HttpStatus.OK);
//        }
//    }
}

//ResponseDto { private T responseData; //여기에 많이 들어가도됨
// private String statusCode;
// private String errorMessage; }
