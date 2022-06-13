package horse.latte.controller;

import horse.latte.security.UserDetailsImpl;
import horse.latte.service.LoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class LoveController {

    private final LoveService loveService;

    @PostMapping("/{boardId}/like")
    public boolean clickLove (@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("좋아요 성공");
        return loveService.lovePosting(boardId, userDetails);
    }
}