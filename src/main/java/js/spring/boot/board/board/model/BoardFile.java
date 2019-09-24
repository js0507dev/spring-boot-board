package js.spring.boot.board.board.model;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BoardFile {
    @EmbeddedId
    private BoardFileId id;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private String fileExt;
}
