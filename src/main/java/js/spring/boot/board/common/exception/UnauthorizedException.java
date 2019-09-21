package js.spring.boot.board.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException{
  String message;

  public UnauthorizedException() {
    this.message = "로그인 필요";
  }

  public UnauthorizedException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}
