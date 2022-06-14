package horse.latte.service;

import horse.latte.dto.LoveRequestDto;
import horse.latte.dto.LoveResponseDto;
import horse.latte.exceptionhandler.ApiRequestException;
import horse.latte.model.Board;
import horse.latte.model.Love;
import horse.latte.model.User;
import horse.latte.repository.BoardRepository;
import horse.latte.repository.LoveRepository;
import horse.latte.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoveService {

    private final LoveRepository loveRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public LoveResponseDto postLove(Long boardId, String nickname) {
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

        return new LoveResponseDto(boardId, loveRepository.countByBoard(board));
    }
}

