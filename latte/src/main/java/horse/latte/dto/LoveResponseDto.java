package horse.latte.dto;

import lombok.Getter;

@Getter
public class LoveResponseDto {
    private Long boardId;
    private Long totalLove;

    public LoveResponseDto(Long boardId, Long totalLove) {
        this.boardId = boardId;
        this.totalLove = totalLove;
    }
}
