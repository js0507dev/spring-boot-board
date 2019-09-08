package js.spring.boot.board.board.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Data
@Embeddable
public class CommentId implements Serializable {
    @Column(name = "commentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long boardId;
}
