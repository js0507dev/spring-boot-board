package js.spring.boot.board.board.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class BoardFile {
    @EmbeddedId
    private BoardFileId id;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private String fileExt;
}