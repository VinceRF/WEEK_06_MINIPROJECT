package horse.latte.service;

import horse.latte.dto.CommentRequestDto;
import horse.latte.model.Comment;
import horse.latte.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public String save(CommentRequestDto requestDto){
        Comment comment = new Comment(requestDto);
        commentRepository.save(comment);
        return "등록 성공";
    }

    @Transactional
    public List<Comment> find(){
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
