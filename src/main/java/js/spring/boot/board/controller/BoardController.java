package js.spring.boot.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/board")
public class BoardController {
    final static String BOARD_TEMPLATE_PREFIX = "board/";
    @GetMapping("/")
    public String list(HttpServletRequest req,
                       ModelAndView model) throws Exception {
        String template = BOARD_TEMPLATE_PREFIX + "list";
        return template;
    }
}
