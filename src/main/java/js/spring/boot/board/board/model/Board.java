package js.spring.boot.board.board.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
public class Board {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "boardId")
  private Long id;
  private String title;
  private String content;
  private LocalDateTime regDate;
  private LocalDateTime changeDate;
  private Long viewCount;
  private String writerId;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "boardId")
  private List<BoardFile> boardFiles;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "boardId")
  private List<BoardCategory> boardCategories;

  @Transient
  private List<Comment> comments;
}
