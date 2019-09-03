package js.spring.boot.board.board.repository;

import js.spring.boot.board.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {//} extends PagingAndSortingRepository {
}
