package js.spring.boot.board.board.controller;

import js.spring.boot.board.board.model.Board;
import js.spring.boot.board.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/board")
public class BoardController {
  @Autowired
  private BoardService boardService;

  @GetMapping("")
  public Page<Board> selectAll(final Pageable pageable, HttpServletResponse res) throws Exception {
    return boardService.findAll(pageable);
  }

  @GetMapping("/test")
  public String test() {
    return "test";
  }
}
