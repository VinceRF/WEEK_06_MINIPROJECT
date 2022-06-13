package horse.latte.controller;

import horse.latte.dto.CommentRequestDto;
import horse.latte.dto.response.CommentResponseDto;
import horse.latte.model.Comment;
import horse.latte.repository.CommentRepository;
import horse.latte.security.UserDetailsImpl;
import horse.latte.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    private final CommentRepository commentRepository;
    @PostMapping("/api/board/{boardId}/detail/comment/write")
    public String createComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //Error: Exceeded maxRedirects. Probably stuck in a
        // redirect loop http://localhost:8080/api/user/loginView
        //로그인 없이 접근 했을 때 경우, 인텔리제이 콘솔에는 안 찍히고, postman console에 찍힘
        if(userDetails != null){
            return commentService.save(requestDto, userDetails);
        }
        return "로그인이 필요한 기능입니다.";
    }

    @GetMapping("/api/board/{boardId}/detail/comments")
    public List<CommentResponseDto> getComment(@PathVariable Long boardId) {
        return commentService.find(boardId);
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
