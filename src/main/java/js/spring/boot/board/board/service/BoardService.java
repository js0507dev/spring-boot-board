package js.spring.boot.board.board.service;

import js.spring.boot.board.board.model.Board;
import js.spring.boot.board.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
  @Autowired
  private BoardRepository boardRepository;

  @Transactional(readOnly = true)
  public Page<Board> findAll(Pageable pageable) {
    return boardRepository.findAll(pageable);
  }
}
