package js.spring.boot.board.user.service;

import js.spring.boot.board.user.model.SecurityUser;
import js.spring.boot.board.user.model.User;
import js.spring.boot.board.user.model.UserRole;
import js.spring.boot.board.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {
  private static final String ROLE_PREFIX = "ROLE_";

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public User createUser(User user) {
    try {
      validateUser(user);
    } catch (Exception e) {
      e.printStackTrace();
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRegDate(LocalDate.now());
    user.setRoles(Arrays.asList(new UserRole(user.getId(), ROLE_PREFIX + "USER")));
    return userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

    return Optional.ofNullable(userRepository.findById(id))
      .filter(u -> u != null)
      .map(u -> new SecurityUser(u.get())).get();
  }

  private boolean validateUser(User user) throws Exception {
    if (user == null) {
      throw new Exception("user 정보 없음");
    }
    String id = user.getId();
    String name = user.getName();
    String emailAddr = user.getEmailAddr();
    String password = user.getPassword();
    if (id == null || id.length() == 0 ||
      !Pattern.compile("^([a-zA-Z])[0-9a-zA-Z]+$").matcher(id).find()) {
      throw new Exception("id규칙 오류");
    }
    if (name == null || name.length() == 0 ||
      !Pattern.compile("[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]+").matcher(name).find()) {
      throw new Exception("name규칙 오류");
    }
    if (emailAddr != null &&
      !Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$").matcher(emailAddr).find()) {
      throw new Exception("email규칙 오류");
    }
    if (password == null || password.length() == 0 ||
      !Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$").matcher(password).find()) {
      throw new Exception("password규칙 오류");
    }
    return true;
  }
}
