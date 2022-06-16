package horse.latte.repository;

import horse.latte.model.Board;
import horse.latte.model.Love;
import horse.latte.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LoveRepository extends JpaRepository<Love, Long> {

    Optional<Love> findLovesByUserAndBoardId(User user, Long id);
    void deleteLovesByUserAndBoard(User user, Board board);



//    // 게시글에 user 가 누른 좋아요 조회
//    Optional<Love> findByUserAndBoard(User user, Long id);
//
//    // 게시글에 좋아요 총 개수 조회
//    Long countByBoard(Board board);
//
//    // 게시글에 좋아요 삭제
//    Long deleteByboard(Board board);


}
