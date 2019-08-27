package js.spring.boot.board.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class UserRole {
    @Id
    private String id;
    private String roleName;

    public UserRole() {
        this("","");
    }

    public UserRole(String id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
}
