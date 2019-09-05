package js.spring.boot.board.board.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class BoardCategory {
    @EmbeddedId
    private BoardCategoryId id;
    private String categoryName;
}
