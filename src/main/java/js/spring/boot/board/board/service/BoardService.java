package js.spring.boot.board.board.service;

import js.spring.boot.board.board.model.Board;
import js.spring.boot.board.board.repository.BoardRepository;
import js.spring.boot.board.board.repository.CommentRepository;
import js.spring.boot.board.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
  @Autowired
  private BoardRepository boardRepository;
  @Autowired
  private CommentRepository commentRepository;

  @Transactional(readOnly = true)
  public Page<Board> findAll(Pageable pageable) {
    return boardRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public Board findById(Long id) {
    Board board = boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("boardID : "+id+" is not found"));
    board.setComments(commentRepository.findByBoardId(id));
    return board;
  }
}
