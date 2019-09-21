package js.spring.boot.board.board.controller;

import js.spring.boot.board.board.model.Board;
import js.spring.boot.board.board.model.Comment;
import js.spring.boot.board.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
  public Page<Board> selectAll(final Pageable pageable, HttpServletResponse res) throws Exception {
    return boardService.findAll(pageable);
  }

  @PostMapping("")
  @ResponseBody
  public String saveBoard(Board board) throws Exception {
    if(boardService.saveBoard(board) == null) {
      return "error";
    }
    return "success";
  }

  @PutMapping("/{id}")
  @ResponseBody
  public String updateBoard(@PathVariable Long id,
                            Board board) throws Exception {
    if(boardService.updateBoard(id, board) == null) {
      return "error";
    }
    return "success";
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public String deleteBoard(@PathVariable Long id) throws Exception {
    boardService.deleteBoard(id);
    return "success";
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

  @DeleteMapping("/{boardId}/comment/{commentId}")
  @ResponseBody
  public String deleteComment(@PathVariable Long boardId,
                              @PathVariable Long commentId) throws Exception {
    boardService.deleteComment(boardId, commentId);
    return "success";
  }
}
