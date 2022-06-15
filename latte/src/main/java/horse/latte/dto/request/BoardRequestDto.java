package horse.latte.dto.request;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String contents;
    private String url;
    private String year;
}
