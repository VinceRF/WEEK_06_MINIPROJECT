package horse.latte.service;

import horse.latte.dto.CommentRequestDto;
import horse.latte.model.Comment;
import horse.latte.repository.CommentRepository;
import horse.latte.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<Comment> find(Long boardId){
//        List<Board> boards = commentRepository.findAById(boardId).orElseThrow(
//                () -> new NullPointerException("게시판이 조회되지 않습니다."));
        //comment에 저장된 board의 id로 검색을 해야하는데
        //지금 이대로 가면 board 객체는 board의 모든 정보를 다 가지고 있어서
        //boardId로만 검색하기 어려울 것 같은데?
        //위에서 선언한 board로 해야 하는 구나
        // findCommentsByBoard??

        //user의 정보를 전부 보내주니까 문제가 되지 않을까?
        //user의 nickname만 보내주는 방법이 뭐가 있을까?
        //jsonIgnore?

//        return commentRepository.findAllById(boardId);
        return commentRepository.findAll();
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
