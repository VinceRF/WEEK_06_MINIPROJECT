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
    public LoveResponseDto postLove(Long id, String nickname) {
        // userRepository 에서 user 가 등록된 nickname 을 가져온다.
        User user = userRepository.findByNickname(nickname).orElseThrow(
                () -> new ApiRequestException("좋아요를 찾는 유저정보가 없습니다."));
        System.out.println(user);

        // boardRepository 에서 board 에 등록된 게시물 id를 가져온다.
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new ApiRequestException("찾는 글이 없습니다."));
        System.out.println(board);

        // user 의 nickname, board 의 게시물 id를 가져와 findLove 객체로 생성, 둘다 없다면 null 로 반환
        Love findLove = loveRepository.findLovesByUserAndBoard(user, board).orElseThrow(null);
        System.out.println(findLove);

        //만약 Null 로 반환했으면
        if(findLove == null){
            LoveRequestDto loveRequestDto = new LoveRequestDto(user, board);
            Love love = new Love(loveRequestDto);
            //user 의 nickname 과 board 의 id 저장
            loveRepository.save(love);
        } else {
            //만약 Null 이 아니라면 게시물이 존재한다는 뜻이니 삭제
            loveRepository.deleteByBoard(findLove.getBoard());
        }

        return new LoveResponseDto(id, loveRepository.countByBoard(board));
    }
}

