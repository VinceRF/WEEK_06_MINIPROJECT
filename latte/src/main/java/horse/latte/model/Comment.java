package horse.latte.model;

import horse.latte.dto.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String comment;

//    @ManyToOne
//    @JoinColumn(name = "board_id")
//    private Board board;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user; // 작성자

    public Comment(CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
    }

    public void update(CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
    }
}
