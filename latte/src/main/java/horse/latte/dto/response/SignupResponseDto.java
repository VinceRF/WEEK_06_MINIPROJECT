package horse.latte.dto.response;

import lombok.Getter;

@Getter
public class SignupResponseDto {
    private String username;
    private String nickname;
    private String profileUrl;
    private String message;

    public SignupResponseDto(String username, String nickname, String profileUrl, String message) {
        this.username = username;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.message = message;
    }
}
