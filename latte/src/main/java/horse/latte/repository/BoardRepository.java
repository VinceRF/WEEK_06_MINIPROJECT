package horse.latte.repository;

import horse.latte.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //Board 전체 조회 최신순으로 가져오기
    List<Board> findAllByOrderByModifiedAtDesc();

    //일단 year 값으로 된 모든걸 가져오는거라고 생각하자 : 최신순으로
    List<Board> findByYearByModifiedAtDesc(Long year);
}
