package js.spring.boot.board.common.util;

import js.spring.boot.board.common.exception.UnauthorizedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SpringSecurityUtil {
  public static String getAuthenticationUserId() throws SecurityException {
    if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
      throw new UnauthorizedException();
    }
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserDetails userDetails = (UserDetails) principal;
    if(userDetails.isAccountNonExpired()) {
      throw new UnauthorizedException("세션 만료");
    }
    return userDetails.getUsername();
  }
}
