package js.spring.boot.board.board.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Board {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "boardId")
  private Long id;
  private String title;
  private String content;
  private LocalDate regDate;
  private LocalDate changeDate;
  private Long viewCount;
  private String writerId;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "boardId")
  private List<BoardFile> boardFiles;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "boardId")
  private List<BoardCategory> boardCategories;
}
