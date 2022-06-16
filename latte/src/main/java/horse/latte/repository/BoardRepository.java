package horse.latte.repository;

import horse.latte.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();
    List<Board> findAllByYear(String year);
//    List<Board> findAllByUserId(Long userId);
    Optional<Board> findById(Long id);


    @Transactional
    @Modifying
    @Query("update Board m set m.loveCount = m.loveCount+1 where m.id = :id")
    int uplikeCount(@Param("id") Long id);
    //해당 아이디의 카운터를 1 업,

    @Transactional
    @Modifying
    @Query("update Board m set m.loveCount = m.loveCount-1 where m.id = :id")
    int downlikeCount(@Param("id") Long id);
    //해당 아이디의 카운터를 1 다운.,


}
