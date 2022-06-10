package horse.latte.controller;

import horse.latte.dto.CommentRequestDto;
import horse.latte.model.Comment;
import horse.latte.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/board/{boardId}/detail/comment/write")
    public String createComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long boardId) {
        return commentService.save(requestDto);
    }
    @GetMapping("/api/board/{boardId}/detail/comments")
    public List<Comment> getComment(@PathVariable Long boardId) {
        return commentService.find();
    }

    @PutMapping("/api/board/{boardId}/detail/comment/{commentId}")
    public String editComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long boardId, @PathVariable Long commentId){

        return commentService.edit(requestDto, commentId);
    }

    @DeleteMapping("/api/board/{boardId}/detail/comment/{commentId}")
    public String deleteComment(@PathVariable Long boardId, @PathVariable Long commentId){

        return commentService.delete(commentId);
    }
}
