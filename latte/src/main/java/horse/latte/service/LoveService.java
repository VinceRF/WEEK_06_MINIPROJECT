package horse.latte.service;

import horse.latte.dto.request.LoveRequestDto;
import horse.latte.dto.request.LoveResponseDto;
import horse.latte.exceptionhandler.ApiRequestException;
import horse.latte.model.Board;
import horse.latte.model.Love;
import horse.latte.model.User;
import horse.latte.repository.BoardRepository;
import horse.latte.repository.LoveRepository;
import horse.latte.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoveService {

    private final LoveRepository loveRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public ResponseEntity postLove(Long boardId, String nickname) {
        User user = userRepository.findByNickname(nickname).orElseThrow(
                () -> new ApiRequestException("좋아요를 찾는 유저정보가 없습니다."));

        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ApiRequestException("찾는 글이 없습니다."));

        Love findLove = loveRepository.findLovesByUserAndBoard(user, board).orElseThrow(null);

        if(findLove == null){
            LoveRequestDto loveRequestDto = new LoveRequestDto(user, board);
            Love love = new Love(loveRequestDto);
            loveRepository.save(love);
        } else {
            loveRepository.deleteByBoard(findLove.getBoard());
        }
        new LoveResponseDto(boardId, loveRepository.countByBoard(board));
        return new ResponseEntity(HttpStatus.OK);
    }
}

