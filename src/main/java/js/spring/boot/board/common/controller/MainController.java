package js.spring.boot.board.common.controller;

import js.spring.boot.board.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/sec")
    public String securityTest() {
        return "test";
    }
}
