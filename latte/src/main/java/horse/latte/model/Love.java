package horse.latte.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Love {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column         //왜 컬럼화 할까
    private Long id;

    //게시물과 묶음
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    //유저와 묶음
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Love(Board board, User user) {
        this.board = board;
        this.user = user;
    }
}
