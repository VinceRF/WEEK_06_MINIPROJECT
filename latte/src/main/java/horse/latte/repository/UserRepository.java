package horse.latte.repository;

import horse.latte.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByNickname(String nickname);

    // 닉네임으로 멤버 존재 여부 확인
    boolean existsByNickname(String nickname);

}
