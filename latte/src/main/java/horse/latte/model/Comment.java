package horse.latte.model;

import horse.latte.dto.CommentRequestDto;
import horse.latte.security.UserDetailsImpl;
import horse.latte.security.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Comment extends Timestamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String comment;

//    @ManyToOne
//    @JoinColumn(name = "board_id")
//    private Board board;
//
    @ManyToOne
    @JoinColumn(name = "user_nickname")
    private User user;

    public Comment(CommentRequestDto requestDto, UserDetailsImpl user){
        this.comment = requestDto.getComment();
        this.user = user.getUser();
    }

    public void update(CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
    }
}
