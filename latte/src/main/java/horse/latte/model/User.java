package horse.latte.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@Builder
@AllArgsConstructor
public class User extends Timestamped {
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

//    @OneToMany(mappedBy = "user_id", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    private List<Comment> comments;

    public User(String username,String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }
}