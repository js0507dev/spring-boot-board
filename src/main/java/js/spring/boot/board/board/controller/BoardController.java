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
  public String saveBoard(Board board, Model model) throws Exception {
    return TEMPLATE_PREFIX + "singlePage";
  }

  @GetMapping("/{id}")
  public String selectOne(@PathVariable Long id, Model model) throws Exception {
    model.addAttribute("board", boardService.findById(id));
    return TEMPLATE_PREFIX + "singlePage";
  }

  @PostMapping("/{boardId}/comment")
  @ResponseBody
  public String saveComment(@PathVariable(name = "boardId") Long boardId,
                              @PathVariable(name = "id") Long id,
                              Comment comment) throws Exception {
    Board board = boardService.findById(boardId);
    Comment saveComment = boardService.saveComment(comment);
    if (saveComment == null) {
      return "error";
    }
    return "success";
  }
}
