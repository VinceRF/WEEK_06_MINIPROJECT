package horse.latte.dto;

import horse.latte.model.Board;
import horse.latte.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoveRequestDto {
    private User user;
    private Board board;
}
