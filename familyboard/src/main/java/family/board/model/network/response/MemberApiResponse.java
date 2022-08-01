package family.board.model.network.response;

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
public class MemberApiResponse{

    private Long id;
    private String name;
    private String account;
    private String auth;
    private String password;

    private String email;


}
