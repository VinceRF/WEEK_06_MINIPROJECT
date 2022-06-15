package horse.latte.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import horse.latte.dto.request.BoardRequestDto;
import horse.latte.security.UserDetailsImpl;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Board extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String url;

    @Column(nullable = false)
    private Long year;

    @Builder
    public Board(String title, String contents, String nickname, String url, Long year) {
        this.title = title;
        this.contents = contents.replace("\r\n","<br>");
        this.nickname = nickname;
        this.url = url;
        this.year = year;
    }


    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments;


    public Board(BoardRequestDto requestDto, UserDetailsImpl userDetails) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents().replace("\r\n","<br>");
        this.nickname = userDetails.getUser().getNickname();
        this.url = requestDto.getUrl();
        this.year = requestDto.getYear();
        this.comments = getComments();
    }

    public void update(BoardRequestDto requestDto, Long id) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.url = requestDto.getUrl();
        this.year = requestDto.getYear();
        this.id = id;
    }

    public void delete(Long id) {
        this.id = id;
    }
}