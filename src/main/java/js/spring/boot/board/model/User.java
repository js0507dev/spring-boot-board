package js.spring.boot.board.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER")
public class User {
    @Id
    private Integer id;
    private String name;
    private String password;
    private String emailAddr;
}
