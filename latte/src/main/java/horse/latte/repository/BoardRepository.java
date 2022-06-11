package horse.latte.repository;

import horse.latte.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //일단 year 값으로 된 모든걸 가져오는거라고 생각하자
    List<Board> findByYear(Long year);
}
