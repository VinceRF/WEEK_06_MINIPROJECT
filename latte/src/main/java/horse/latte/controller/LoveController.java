package horse.latte.controller;

import horse.latte.dto.LoveResponseDto;
import horse.latte.security.UserDetailsImpl;
import horse.latte.service.LoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoveController {

    private final LoveService loveService;

    @PostMapping("/api/board/{id}/like")
    public LoveResponseDto postLove(@PathVariable long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("ID 체크 " + userDetails.getUsername());

        return loveService.postLove(id, userDetails.getUser().getNickname());
    }
}