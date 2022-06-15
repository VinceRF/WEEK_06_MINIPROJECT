package horse.latte.controller;


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

    //좋아요 기능구현
    @PostMapping("/api/board/{id}/like")
    public boolean clickLove (@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetail){
        return loveService.clickLove(id, userDetail);
    }
}