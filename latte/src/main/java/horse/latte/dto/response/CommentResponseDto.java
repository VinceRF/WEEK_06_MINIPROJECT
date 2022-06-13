package horse.latte.dto.response;

import horse.latte.dto.CommentRequestDto;
import horse.latte.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;

    private String comment;

    private String userNickname;

    private LocalDateTime createdAt;
}
