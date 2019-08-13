package js.spring.boot.board.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 40)
    private String id;
    @Column(name = "NAME", length = 30)
    private String name;
    @Column(name = "PASSWORD", length = 50)
    private String password;
    @Column(name = "EMAIL_ADDR", length = 100)
    private String emailAddr;
}
