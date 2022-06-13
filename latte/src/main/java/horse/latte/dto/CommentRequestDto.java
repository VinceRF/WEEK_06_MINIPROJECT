package horse.latte.dto;

import horse.latte.model.User;
import lombok.*;


//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class CommentRequestDto {
    private String comment;
    private User user;
}
