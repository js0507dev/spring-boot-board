package js.spring.boot.board.user.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class User {
    @Id
    @Column(length = 40)
    private String id;
    @Column(length = 30)
    private String name;
    @Column(length = 50)
    private String password;
    @Column(length = 100)
    private String emailAddr;
    private LocalDate regDate;

    @Builder
    public User(String id, String name, String password, String emailAddr) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.emailAddr = emailAddr;
        this.regDate = LocalDate.now();
    }

    public User(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.emailAddr = user.getEmailAddr();
        this.regDate = user.getRegDate();
    }

    public User() {
        this("", "", "", "");
    }

    public boolean validate() {
        return true;
    }
}
