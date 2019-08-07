package js.spring.boot.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/sec")
    public String securityTest() {
        return "test";
    }
}
