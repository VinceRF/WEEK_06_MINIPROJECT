package horse.latte.dto.response;

import horse.latte.dto.CommentRequestDto;
import horse.latte.model.User;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private String comment;
    private String userNickname;

    CommentResponseDto(CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
        this.userNickname = requestDto.getUser().getNickname();
    }
}
