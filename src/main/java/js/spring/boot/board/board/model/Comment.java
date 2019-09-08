package js.spring.boot.board.board.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class Comment {
    @EmbeddedId
    private CommentId id;
    private String content;
    private CommentId lookupCommentId;
    private LocalDate regDate;
    private LocalDate changeDate;
    private String writerId;
}
