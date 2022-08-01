package family.board.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class MemberApiRequest {
    private Long id;
    private String name;
    private String auth;
    private String account;
    private String password;

    private String email;


}
