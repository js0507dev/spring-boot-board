package js.spring.boot.board.board.controller;

import js.spring.boot.board.board.model.Board;
import js.spring.boot.board.board.model.Comment;
import js.spring.boot.board.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {
  private static final String TEMPLATE_PREFIX = "board/";
  private final BoardService boardService;

  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping("")
  @ResponseBody
  public ResponseEntity<Page<Board>> selectAll(final Pageable pageable) throws Exception {
    HttpHeaders headers = new HttpHeaders();
    return new ResponseEntity<>(boardService.findAll(pageable), headers, HttpStatus.OK);
  }

  @PostMapping("")
  @ResponseBody
  public ResponseEntity<Board> saveBoard(Board board) throws Exception {
    Board saveBoard = boardService.saveBoard(board);
    HttpHeaders httpHeaders = new HttpHeaders();
    return new ResponseEntity<>(saveBoard, httpHeaders, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Board> updateBoard(@PathVariable Long id,
                                           Board board) throws Exception {
    Board updateBoard = boardService.updateBoard(id, board);
    HttpHeaders httpHeaders = new HttpHeaders();
    return new ResponseEntity<>(updateBoard, httpHeaders, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Board> deleteBoard(@PathVariable Long id) throws Exception {
    boardService.deleteBoard(id);
    HttpHeaders httpHeaders = new HttpHeaders();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public String selectOne(@PathVariable Long id, Model model) throws Exception {
    model.addAttribute("board", boardService.findById(id));
    return TEMPLATE_PREFIX + "singlePage";
  }

  @PostMapping("/{boardId}/comment")
  @ResponseBody
  public String saveComment(@PathVariable(name = "boardId") Long boardId,
                              Comment comment) throws Exception {
    if (!boardId.equals(comment.getBoardId()) || boardService.saveComment(comment) == null) {
      return "error";
    }
    return "success";
  }

  @PutMapping("/{boardId}/comment/{commentId}")
  @ResponseBody
  public String updateComment(@PathVariable(name = "boardId") Long boardId,
                              @PathVariable(name = "commentId") Long commentId,
                              Comment comment) throws Exception {
    if (!boardId.equals(comment.getBoardId()) || boardService.updateComment(commentId,comment) == null) {
      return "error";
    }
    return "success";
  }

  @DeleteMapping("/{boardId}/comment/{commentId}")
  @ResponseBody
  public String deleteComment(@PathVariable Long boardId,
                              @PathVariable Long commentId) throws Exception {
    boardService.deleteComment(boardId, commentId);
    return "success";
  }
}
