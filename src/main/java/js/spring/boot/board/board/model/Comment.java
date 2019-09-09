package js.spring.boot.board.board.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {
    @Id
    @Column(name = "commentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long boardId;
    private String content;
    private Long lookupCommentId;
    private LocalDateTime regDate;
    private LocalDateTime changeDate;
    private String writerId;
}
