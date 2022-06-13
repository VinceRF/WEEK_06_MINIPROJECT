package horse.latte.repository;

import horse.latte.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //Board 전체 조회 최신순으로 가져오기
    List<Board> findAllByOrderByModifiedAtDesc();

    //일단 year 값으로 된 모든걸 가져오는거라고 생각하자 : 최신순으로
    List<Board> findAllByYear(Long year);

    //게시물 좋아요 구현
    @Transactional
    @Modifying
    @Query("update Board m set m.loveCount = m.loveCount+1 where m.id = :id")
    int upLoveCount(Long id);

    @Transactional
    @Modifying
    @Query("update Board m set m.loveCount = m.loveCount-1 where m.id = :id")
    int downLoveCount(Long id);
}
