package family.board.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String role;

    private String account;

    private String password;

    private String email;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "member")
    private List<Board> BoardList;

}
