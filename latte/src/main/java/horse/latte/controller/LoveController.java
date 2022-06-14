package horse.latte.controller;

import horse.latte.dto.response.LoveResponseDto;
import horse.latte.security.UserDetailsImpl;
import horse.latte.service.LoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoveController {
    private final LoveService loveService;

    @PostMapping("/api/board/{boardId}/like")
    public LoveResponseDto postLove(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails){

        System.out.println("좋아요 한 Id: " + userDetails.getUsername());

        return loveService.love(boardId, userDetails.getUsername());
    }

}
