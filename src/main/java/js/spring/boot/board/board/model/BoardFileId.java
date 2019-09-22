package js.spring.boot.board.board.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Data
@Embeddable
public class BoardFileId implements Serializable {
    @Column(name = "fileId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long boardId;

    public BoardFileId(Long boardId, Long id) {
        this.boardId = boardId;
        this.id = id;
    }
}
