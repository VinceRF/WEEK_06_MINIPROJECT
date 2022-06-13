package horse.latte.repository;

import horse.latte.model.Board;
import horse.latte.model.Love;
import horse.latte.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoveRepository extends JpaRepository<Love, Long> {
    Optional<Love> findLikesByUserAndBoardId(User user, Long id);
    void deleteLoveByUserAndBoard(User user, Board board);
}
