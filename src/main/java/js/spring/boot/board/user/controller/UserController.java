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
  private static final String TEMPLATE_PREFIX = "user/";
  @Autowired
  UserService userService;

  @PostMapping("")
  public String join(User user) {
    System.out.println("test : " + user.toString());
    userService.createUser(user);
    return "redirect:/";
  }

  @GetMapping("")
  public String joinForm() {
    return TEMPLATE_PREFIX + "join";
  }

  @GetMapping("/login")
  public String loginForm(HttpServletRequest req) {
    return TEMPLATE_PREFIX + "login";
  }
}
