package js.spring.boot.board.user.repository;

import js.spring.boot.board.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
