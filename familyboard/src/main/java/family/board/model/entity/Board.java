package family.board.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Blob;

@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Blob file;

    @ManyToOne
    @JsonProperty("member_id")
    private Member member;

}
