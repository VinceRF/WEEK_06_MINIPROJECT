package horse.latte.service;


import horse.latte.dto.request.BoardRequestDto;
import horse.latte.dto.response.BoardResponseDto;
import horse.latte.dto.response.CommentResponseDto;
import horse.latte.model.Board;
import horse.latte.model.Comment;
import horse.latte.repository.BoardRepository;
import horse.latte.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public ResponseEntity createBoard(BoardRequestDto requestDto, UserDetailsImpl userDetails) {
        //요정받은 DTO로 DB에 저장할 객체 만들기
        Board board = Board.builder()
                .title(requestDto.getTitle())
                .contents(requestDto.getContents().replace("\r\n","<br>"))
                .nickname(userDetails.getUser().getNickname())
                .url(requestDto.getUrl())
                .year(requestDto.getYear())
                .build();
        boardRepository.save(board);
        return new ResponseEntity("등록 성공", HttpStatus.OK);
    }

    @Transactional  //update 할때 필수
    public ResponseEntity update(Long id, String nickname, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        if (board.getNickname().equals(nickname)) {
            board.update(requestDto, id);
            boardRepository.save(board);
            return new ResponseEntity("수정 완료", HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("수정 불가능한 게시글입니다.");
        }
    }

    @Transactional
    public ResponseEntity delete(Long id, String nickname) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        if (board.getNickname().equals(nickname)) {
            boardRepository.delete(board);
            return new ResponseEntity("삭제완료", HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("삭제 불가능한 게시글입니다.");
        }
    }

    @Transactional
    public ResponseEntity getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment : board.getComments()) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(
                    comment.getId(),
                    comment.getUser().getNickname(),
                    comment.getComment(),
                    comment.getCreatedAt()
            );
            commentResponseDtos.add(commentResponseDto);
        }
        new BoardResponseDto(
                board.getId(),
                board.getNickname(),
                board.getTitle(),
                board.getContents(),
                board.getUrl(),
                board.getYear(),
                board.getCreatedAt(),
                board.getModifiedAt(),
                commentResponseDtos
        );
        return new ResponseEntity(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity <List<BoardResponseDto>> getAllBoards() {
        List<Board> boards = boardRepository.findAllByOrderByModifiedAtDesc();
        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();

        for (Board board : boards) {
            List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
            for (Comment comment : board.getComments()) {
                CommentResponseDto commentResponseDto = new CommentResponseDto(
                        comment.getId(),
                        comment.getUser().getNickname(),
                        comment.getComment(),
                        comment.getCreatedAt()
                );
                commentResponseDtos.add(commentResponseDto);
            }
            BoardResponseDto boardResponseDto = new BoardResponseDto(
                    board.getId(),
                    board.getNickname(),
                    board.getTitle(),
                    board.getContents(),
                    board.getUrl(),
                    board.getYear(),
                    board.getCreatedAt(),
                    board.getModifiedAt(),
                    commentResponseDtos
                    );
            boardResponseDtos.add(boardResponseDto);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity <List<BoardResponseDto>> getBoardsByYear(Long year) {
        List<Board> boards = boardRepository.findAllByYear(year);
        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();

        for (Board board : boards) {
            List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
            for (Comment comment : board.getComments()) {
                CommentResponseDto commentResponseDto = new CommentResponseDto(
                        comment.getId(),
                        comment.getUser().getNickname(),
                        comment.getComment(),
                        comment.getCreatedAt()
                );
                commentResponseDtos.add(commentResponseDto);
            }
            BoardResponseDto boardResponseDto = new BoardResponseDto(
                    board.getId(),
                    board.getNickname(),
                    board.getTitle(),
                    board.getContents(),
                    board.getUrl(),
                    board.getYear(),
                    board.getCreatedAt(),
                    board.getModifiedAt(),
                    commentResponseDtos
            );
            boardResponseDtos.add(boardResponseDto);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
