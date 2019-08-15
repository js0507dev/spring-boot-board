package js.spring.boot.board.repository;

import js.spring.boot.board.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
