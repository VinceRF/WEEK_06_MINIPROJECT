package horse.latte.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetailBoardResponseDto {
    private Long id;
    private String nickname;
    private String title;
    private String contents;
    private String url;
    private String year;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private boolean love;

    List<CommentResponseDto> commentResponseDto;
}
