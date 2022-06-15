package horse.latte.dto.response;

import lombok.Getter;

@Getter
public class LoveResponseDto {
    private Long boardId;
    private Long totalLove;

    public LoveResponseDto(Long id, Long totalLove) {
        this.boardId = id;
        this.totalLove = totalLove;
    }
}
