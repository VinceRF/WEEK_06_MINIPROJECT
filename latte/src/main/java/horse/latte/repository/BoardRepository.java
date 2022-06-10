package horse.latte.repository;

import horse.latte.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //일단 year 값으로 가져오는거라고 생각하자
    Page<Board> findByYear(int year);

}
