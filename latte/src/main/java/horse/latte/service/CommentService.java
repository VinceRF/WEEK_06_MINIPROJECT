package horse.latte.service;

import horse.latte.dto.request.CommentRequestDto;
import horse.latte.dto.response.CommentResponseDto;
import horse.latte.exceptionhandler.BoardNotFoundException;
import horse.latte.exceptionhandler.NotAuthorizedException;
import horse.latte.model.Board;
import horse.latte.model.Comment;
import horse.latte.repository.BoardRepository;
import horse.latte.repository.CommentRepository;
import horse.latte.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    @Transactional
    public ResponseEntity save(CommentRequestDto requestDto, UserDetailsImpl userDetails, Long boardId){
//        Comment comment = new Comment(requestDto, userDetails);
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new BoardNotFoundException("존재하지 않는 게시글입니다.")
        );

        Comment comment = new Comment();
        comment = Comment.builder()
                .id(null)
                .comment(requestDto.getComment())
                .user(userDetails.getUser())
                .board(board)
                .build();

        commentRepository.save(comment);
        return new ResponseEntity("댓글 등록 성공", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity find(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new BoardNotFoundException("존재하지 않는 게시글입니다.")
        );

        List<Comment> comments = commentRepository.findAllByBoardId(boardId);
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

        for(Comment comment : comments){
            CommentResponseDto commentResponseDto = new CommentResponseDto(
                    comment.getId(),
                    comment.getComment(),
                    comment.getUser().getNickname(),
                    comment.getCreatedAt()
            );
            commentResponseDtos.add(commentResponseDto);
        }

        return new ResponseEntity(commentResponseDtos, HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity edit(CommentRequestDto requestDto, Long commentId, Long boardId, UserDetailsImpl userDetails){
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new BoardNotFoundException("존재하지 않는 게시글입니다.")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        if(!userDetails.getUser().getNickname().equals(comment.getUser().getNickname()))
            throw new NotAuthorizedException("수정할 권한이 없는 댓글입니다.");

        comment.update(requestDto);
        commentRepository.save(comment);
        return new ResponseEntity("댓글 수정 성공", HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity delete(Long commentId, Long boardId, UserDetailsImpl userDetails){
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new BoardNotFoundException("존재하지 않는 게시글입니다.")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        if(!userDetails.getUser().getNickname().equals(comment.getUser().getNickname()))
            throw new NotAuthorizedException("삭제할 권한이 없는 댓글입니다.");

        commentRepository.deleteById(commentId);

        return new ResponseEntity("댓글 삭제 성공", HttpStatus.OK);
    }
}
