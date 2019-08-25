package js.spring.boot.board.user.controller;

import js.spring.boot.board.user.model.User;
import js.spring.boot.board.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("")
    public String create(User user) {
        userService.createUser(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(HttpServletRequest req) {
        return "user/login";
    }
}
