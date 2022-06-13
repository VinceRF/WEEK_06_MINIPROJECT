package horse.latte.service;

import horse.latte.dto.CommentRequestDto;
import horse.latte.dto.response.CommentResponseDto;
import horse.latte.model.Comment;
import horse.latte.repository.CommentRepository;
import horse.latte.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public String save(CommentRequestDto requestDto, UserDetailsImpl userDetails){
//        Comment comment = new Comment(requestDto, userDetails);
        Comment comment = new Comment();
        comment = Comment.builder()
                        .id(null)
                        .comment(requestDto.getComment())
                        .user(userDetails.getUser())
                        .build();

        commentRepository.save(comment);
        return "등록 성공";
    }

    @Transactional
    public List<CommentResponseDto> find(Long boardId){

//        Posts posts = postsRepository.findById(postId).orElseThrow(
//                ()-> new NullPointerException("해당 게시물이 존재하지 않습니다.")
//        );

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
    public String edit(CommentRequestDto requestDto, Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        comment.update(requestDto);
        commentRepository.save(comment);
        return "수정 성공";
    }


    @Transactional
    public String delete(Long commentId){
        commentRepository.deleteById(commentId);
        return "삭제 성공";
    }
}
