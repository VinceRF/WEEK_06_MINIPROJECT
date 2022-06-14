package horse.latte.model;

import horse.latte.dto.BoardRequestDto;
import horse.latte.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity

public class Board extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Long year;

//    public Board(String title, String contents, String username, String url, Long year) {
//        this.title = title;
//        this.contents = contents.replace("\r\n","<br>");
//        this.username = username;
//        this.url = url;
//        this.year = year;
//    }

    public Board(BoardRequestDto requestDto, UserDetailsImpl userDetails) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents().replace("\r\n","<br>");
        this.username = userDetails.getUsername();
        this.url = requestDto.getUrl();
        this.year = requestDto.getYear();
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