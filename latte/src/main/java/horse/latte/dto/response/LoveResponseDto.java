package horse.latte.dto.response;

import lombok.Getter;

@Getter
public class LoveResponseDto {
    private Long boardId;
    private Long totalLike;

    public LoveResponseDto(Long boardId, Long totalLike) {
        this.boardId = boardId;
        this.totalLike = totalLike;
    }
}
