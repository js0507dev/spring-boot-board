package js.spring.boot.board.board.service;

import js.spring.boot.board.board.model.Board;
import js.spring.boot.board.board.model.Comment;
import js.spring.boot.board.board.repository.BoardRepository;
import js.spring.boot.board.board.repository.CommentRepository;
import js.spring.boot.board.common.exception.BadRequestException;
import js.spring.boot.board.common.exception.ForbiddenException;
import js.spring.boot.board.common.exception.ResourceNotFoundException;
import js.spring.boot.board.common.util.SpringSecurityUtil;
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

  public Board saveBoard(Board board) {
    validBoard(board);

    String loginId = SpringSecurityUtil.getAuthenticationUserId();
    board.setWriterId(loginId);
    return boardRepository.save(board);
  }

  public Board updateBoard(Long id, Board board) {
    validBoard(board);

    Board checkBoard = boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("boardID : " + id + " is not found"));
    if(!checkBoard.getId().equals(board.getId())) {
      throw new ResourceNotFoundException("boardId not matched");
    }
    if(!checkBoard.getWriterId().equals(SpringSecurityUtil.getAuthenticationUserId())) {
      throw new ForbiddenException("권한 없음");
    }
    return boardRepository.save(board);
  }

  public void deleteBoard(Long id) {
    Board board = boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("boardID : " + id + " is not found"));
    if(!board.getWriterId().equals(SpringSecurityUtil.getAuthenticationUserId())) {
      throw new ForbiddenException("권한 없음");
    }
    commentRepository.deleteByBoardId(board.getId());
    boardRepository.delete(board);
  }

  public Comment saveComment(Comment comment) {
    validComment(comment);

    String loginId = SpringSecurityUtil.getAuthenticationUserId();
    comment.setWriterId(loginId);
    return commentRepository.save(comment);
  }

  public Comment updateComment(Long id, Comment comment) {
    validComment(comment);

    Comment checkComment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("commentID : " + id + " is not found"));
    if(!checkComment.getId().equals(comment.getId())) {
      throw new BadRequestException("commentId not matched");
    }
    if(!checkComment.getWriterId().equals(SpringSecurityUtil.getAuthenticationUserId())) {
      throw new ForbiddenException("권한 없음");
    }

    return commentRepository.save(comment);
  }

  public void deleteComment(Long boardId, Long commentId) {
    Board board = boardRepository.findById(boardId).orElseThrow(() -> new ResourceNotFoundException("boardID : " + boardId + " is not found"));
    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("commentID: " + commentId + " is not found"));
    if(!board.getId().equals(comment.getBoardId())) {
      throw new ResourceNotFoundException("boardID : " + boardId + ", commentID : " + commentId + "is not found");
    }
    if(!comment.getWriterId().equals(SpringSecurityUtil.getAuthenticationUserId())) {
      throw new ForbiddenException("권한 없음");
    }
    commentRepository.delete(comment);
  }

  //////////////////////////////////////////////////
  //////////////////////////////////////////////////
  private void validBoard(Board board) {
    if(board == null) {
      throw new BadRequestException("board data is null");
    }
    // TODO: 추가해야함
  }

  private void validComment(Comment comment) {
    if (comment == null) {
      throw new BadRequestException("no comment data");
    }
    if(comment.getBoardId() == null || comment.getBoardId() < 0) {
      throw new BadRequestException("boardID error");
    }
    if(!boardRepository.findById(comment.getBoardId()).isPresent()) {
      throw new ResourceNotFoundException("not found board");
    }
  }
}
