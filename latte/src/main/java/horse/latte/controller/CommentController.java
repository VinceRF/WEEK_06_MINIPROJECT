package horse.latte.controller;

import horse.latte.dto.request.CommentRequestDto;
import horse.latte.dto.response.CommentResponseDto;
import horse.latte.security.UserDetailsImpl;
import horse.latte.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/board/{id}/comment/write")
    public ResponseEntity createComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //Error: Exceeded maxRedirects. Probably stuck in a
        // redirect loop http://localhost:8080/api/user/loginView
        //로그인 없이 접근 했을 때 경우, 인텔리제이 콘솔에는 안 찍히고, postman console에 찍힘

        return ResponseEntity.ok().body(commentService.save(requestDto, userDetails, id));
    }

    @GetMapping("/api/board/{id}/comments")
    public ResponseEntity getComment(@PathVariable Long id) {
        return ResponseEntity.ok().body(commentService.find(id));
    }

    @PutMapping("/api/board/{id}/comment/{commentId}")
    public ResponseEntity editComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long id,
                                      @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return ResponseEntity.ok().body(commentService.edit(requestDto, commentId, id, userDetails));
    }

    @DeleteMapping("/api/board/{id}/comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long id, @PathVariable Long commentId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails){

        return ResponseEntity.ok().body(commentService.delete(commentId, id, userDetails));
    }
}