package horse.latte.service;

import horse.latte.dto.SignupRequestDto;
import horse.latte.exceptionhandler.*;
import horse.latte.model.User;
import horse.latte.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    //회원가입
    @Transactional
    public ResponseEntity signup(SignupRequestDto requestDto) {
        String pattern = "^[a-zA-Z0-9]*$";
        String password2 = requestDto.getPassword2();
        String password = requestDto.getPassword();
        String profileUrl = requestDto.getProfileUrl();

        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new DuplicateUsernameException();
        }
        String nickname = requestDto.getNickname();
        Optional<User> found1 = userRepository.findByNickname(nickname);
        if (found1.isPresent()) {
            throw new DuplicationNicknameException();
        }

//         회원가입 조건
        if (username.length() < 4) {
            throw new UsernameLengthException();
        }
        if (!Pattern.matches(pattern, username)) {
            throw new UsernamePatternException();
        }
        if (!password.equals(password2)) {
            throw new PasswordNotMatchException();
        }
        if (password.length() < 8) {
            throw new PasswordLengthException();
        }
        if (password.contains(username)) {
            throw new PasswordContainsUsernameException();
        }
        if (nickname.equals("")) {
            throw new NicknameEmptyException();
        }
        if (nickname.equals(null)) {
            throw new NicknameEmptyException();
        }

        password = passwordEncoder.encode(requestDto.getPassword());//비번 인코딩

        User user = new User(username, nickname, password, profileUrl);
        userRepository.save(user);
        return new ResponseEntity("회원가입 성공", HttpStatus.OK);
    }
}


////    // 로그인
////    public Boolean login(LoginRequestDto loginRequestDto) {
////        User user = userRepository.findByUsername(loginRequestDto.getUsername())
////                .orElse(null);
////        if (user != null) {
////            if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
////                return false;
////            }
////        } else {
////            return false;
////        }
////        return true;
////    }
//
//    // 회원가입
//    public String registerUser(SignupRequestDto requestDto) {
//        String error = "";
//        String username = requestDto.getUsername();
//        String nickname = requestDto.getNickname();
//        String password = requestDto.getPassword();
//        String password2 = requestDto.getPassword2();
//        String pattern = "^[a-zA-Z0-9]*$";
//
//        // 회원 ID 중복 확인
//        Optional<User> foundUsername = userRepository.findByUsername(username);
//        if (foundUsername.isPresent()) {
//            return "중복된 id 입니다.";
//        }
//
//        // 회원 닉네임 중복 확인
//        Optional<User> foundNickname = userRepository.findByNickname(nickname);
//        if (foundNickname.isPresent()) {
//            return "중복된 닉네임 입니다.";
//        }
//
//        // 회원가입 조건
//        if (username.length() < 4) {
//            return "ID를 4자 이상 입력하세요";
//        } else if (!Pattern.matches(pattern, username)) {
//            return "알파벳 대소문자와 숫자로만 입력하세요";
//        } else if (!password.equals(password2)) {
//            return "비밀번호가 일치하지 않습니다";
//        } else if (password.length() < 8) {
//            return "비밀번호를 8자 이상 입력하세요";
//        } else if (password.contains(username)) {
//            return "비밀번호에 ID를 포함할 수 없습니다.";
//        } else if (nickname.equals("")) {
//            return "닉네임을 입력해 주세요.";
//        } else if (nickname.equals(null)) {
//            return "닉네임을 입력해 주세요.";
//        }
//
//        // 패스워드 인코딩
//        password = passwordEncoder.encode(password);
//        requestDto.setPassword(password);
//
//        // 유저 정보 저장
//        User user = new User(username, nickname, password);
//        userRepository.save(user);
//        return error;
//    }
//}
