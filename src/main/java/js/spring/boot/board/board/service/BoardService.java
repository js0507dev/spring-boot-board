package js.spring.boot.board.board.service;

import js.spring.boot.board.board.model.Board;
import js.spring.boot.board.board.model.Comment;
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
  private final BoardRepository boardRepository;
  private final CommentRepository commentRepository;

  @Autowired
  public BoardService(BoardRepository boardRepository, CommentRepository commentRepository) {
    this.boardRepository = boardRepository;
    this.commentRepository = commentRepository;
  }

  @Transactional(readOnly = true)
  public Page<Board> findAll(Pageable pageable) {
    return boardRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public Board findById(Long id) {
    Board board = boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("boardID : " + id + " is not found"));
    board.setComments(commentRepository.findByBoardId(id));
    return board;
  }

  public Comment saveComment(Comment comment) {
    if (!validComment(comment)) {
      return null;
    }
    return commentRepository.save(comment);
  }

  private boolean validComment(Comment comment) {
    if (comment == null) {
      return false;
    }
    return comment.getBoardId() != null && comment.getBoardId() >= 0;
  }
}
