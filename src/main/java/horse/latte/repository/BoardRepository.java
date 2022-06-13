package horse.latte.repository;

import horse.latte.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();
    List<Board> findAllByYear(Long year);
//    List<Board> findAllByUserId(Long userId);
    Optional<Board> findById(Long id);
}