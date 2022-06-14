package horse.latte.service;

import horse.latte.dto.LoveRequestDto;
import horse.latte.dto.response.LoveResponseDto;
import horse.latte.exceptionhandler.ApiRequestException;
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
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public LoveResponseDto love(Long boardId, String username){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ApiRequestException("유저가 없습니다.")
        );

        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ApiRequestException("글번호가 없습니다.")
        );

        Love findLove = loveRepository.findByUserAndBoard(user, board).orElse(null);

        if(findLove == null){
            LoveRequestDto loveRequestDto = new LoveRequestDto(user, board);
            Love love = new Love(loveRequestDto);
            loveRepository.save(love);
        } else {
            loveRepository.deleteByboard(findLove.getBoard());
        }

        return new LoveResponseDto(boardId, loveRepository.countByBoard(board));
    }
}

//ResponseDto { private T responseData; //여기에 많이 들어가도됨
// private String statusCode;
// private String errorMessage; }
