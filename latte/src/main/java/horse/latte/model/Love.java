package horse.latte.model;

import horse.latte.dto.request.LoveRequestDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Love {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Love(LoveRequestDto loveRequestDto) {
        this.board = loveRequestDto.getBoard();
        this.user = loveRequestDto.getUser();
    }
}
