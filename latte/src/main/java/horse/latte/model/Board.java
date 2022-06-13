package horse.latte.model;

import horse.latte.dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Board extends Timestamped{
//게시물 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                //테이블 id

    @Column(nullable = false)
    private String nickname;        //작성 닉네임

    @Column(nullable = false)
    private String content;         //작성 내용

    @Column(nullable = false)
    private Long year;              //연도별 구분

    @Column(nullable = false)
    private String url;             //사진 url

    @Column
    private int loveCount;          //좋아요

    public Board(String nickname, String content, Long year, String url) {
        this.nickname = nickname;
        this.content = content;
        this.year = year;
        this.url = url;
    }

    public Board(BoardDto boardDto){
        this.nickname = boardDto.getNickname();
        this.content = boardDto.getContent();
        this.year = boardDto.getYear();
        this.url = boardDto.getUrl();
    }
}
