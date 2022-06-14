package horse.latte.service;

import horse.latte.dto.CommentRequestDto;
import horse.latte.dto.response.CommentResponseDto;
import horse.latte.model.Board;
import horse.latte.model.Comment;
import horse.latte.repository.BoardRepository;
import horse.latte.repository.CommentRepository;
import horse.latte.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    @Transactional
    public String save(CommentRequestDto requestDto, UserDetailsImpl userDetails, Long boardId){
//        Comment comment = new Comment(requestDto, userDetails);
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );

        Comment comment = new Comment();
        comment = Comment.builder()
                        .id(null)
                        .comment(requestDto.getComment())
                        .user(userDetails.getUser())
                        .board(board)
                        .build();

        commentRepository.save(comment);
        return "등록 성공";
    }

    @Transactional
    public List<CommentResponseDto> find(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );

        List<Comment> comments = commentRepository.findAll();
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

        for(Comment comment : comments){
            CommentResponseDto commentResponseDto = new CommentResponseDto(
                    comment.getId(),
                    comment.getUser().getNickname(),
                    comment.getComment(),
                    comment.getCreatedAt()
            );
            commentResponseDtos.add(commentResponseDto);
        }

        return commentResponseDtos;
    }


    @Transactional
    public String edit(CommentRequestDto requestDto, Long commentId, Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        comment.update(requestDto);
        commentRepository.save(comment);
        return "수정 성공";
    }


    @Transactional
    public String delete(Long commentId, Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );

        commentRepository.deleteById(commentId);
        return "삭제 성공";
    }
}
