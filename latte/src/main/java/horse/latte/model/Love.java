package horse.latte.model;

import horse.latte.dto.LoveRequestDto;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Love {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardLoveId;

    //어떤 유저가 좋아요 하는지
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    //어떤 게시물을 좋아요 하는지
    @ManyToOne
    @JoinColumn(nullable = false)
    private Board board;

    public Love(LoveRequestDto loveRequestDto) {
        this.user = loveRequestDto.getUser();
        this.board = loveRequestDto.getBoard();
    }
}
