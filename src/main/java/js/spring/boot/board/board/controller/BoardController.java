package js.spring.boot.board.board.controller;

import js.spring.boot.board.board.model.Board;
import js.spring.boot.board.board.service.BoardService;
import js.spring.boot.board.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/board")
public class BoardController {
  private static final String TEMPLATE_PREFIX = "board/";
  @Autowired
  private BoardService boardService;

  @GetMapping("")
  @ResponseBody
  public Page<Board> selectAll(final Pageable pageable, HttpServletResponse res) throws Exception {
    return boardService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public String selectOne(@PathVariable Long id, Model model) throws Exception {
    model.addAttribute(boardService.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("boardID : "+id+" is not found")));
    return TEMPLATE_PREFIX+"singlePage";
  }
}
