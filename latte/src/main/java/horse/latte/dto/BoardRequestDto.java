package horse.latte.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String contents;
    private String url;
    private Long year;
}
