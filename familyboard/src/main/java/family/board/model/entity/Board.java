package family.board.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.File;

@AllArgsConstructor
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("file")
    private File file;
    @JsonProperty("member_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Member member;


    public Board(String title,String content,File file) {
        this.title = title;
        this.content = content;
        this.file = file;
    }
}
