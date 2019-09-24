package js.spring.boot.board.board.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
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
