package js.spring.boot.board.board.model;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BoardCategory {
    @EmbeddedId
    private BoardCategoryId id;
    private String categoryName;
}
