package family.board.model.network.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.sql.Blob;

@Data
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class BoardDto {
    private Long id;

    private String title;

    private String content;

    private Blob file;


}
