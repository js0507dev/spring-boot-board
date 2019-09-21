package js.spring.boot.board.board.repository;

import js.spring.boot.board.board.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  @Query(value = "SELECT comment_id, board_id, lookup_comment_id, content, reg_date, change_date, writer_id " +
                 "FROM comment " +
                 "WHERE board_id = :board_id " +
                 "ORDER BY NVL(lookup_comment_id, comment_id), IF(lookup_comment_id IS NULL, -1, comment_id)",
          nativeQuery = true)
  List<Comment> findByBoardId(@Param("board_id") Long boardId);
  @Query(value = "DELETE FROM comment " +
                 "WHERE board_id = :board_id",
          nativeQuery = true)
  void deleteByBoardId(@Param("board_id") Long boardId);
}
