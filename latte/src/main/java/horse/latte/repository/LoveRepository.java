package horse.latte.repository;

import horse.latte.model.Board;
import horse.latte.model.Love;
import horse.latte.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoveRepository extends JpaRepository<Love, Long> {

    // 해당 게시물에 user 가 누른 좋아요 조회
    Optional<Love> findLovesByUserAndBoard(User user, Board board);

    // 해당 게시물의 좋아요 총 개수 조회
    Long countByBoard(Board board);

    // 해당 게시물의 연관된 좋아요 삭제
    void deleteByBoard(Board board);
}
