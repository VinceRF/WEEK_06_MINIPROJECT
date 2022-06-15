package horse.latte.controller;

import horse.latte.dto.SignupRequestDto;
import horse.latte.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user/signup")  //체크 완료
    public ResponseEntity signup(@RequestBody @Valid SignupRequestDto requestDto) {
        return ResponseEntity.ok().body(userService.signup(requestDto));
        // 바디를 내려주려면 .body() , ok가 스테이터스 코드
    }
}