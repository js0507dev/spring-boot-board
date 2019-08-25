package js.spring.boot.board.user.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.regex.Pattern;

@Entity
@Getter
@Setter
@ToString
public class User {
    @Id
    @Column(length = 40)
    private String id;
    @Column(length = 100)
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
        if(this.id == null || this.id.length() == 0 ||
                !Pattern.compile("^([a-zA-Z])[0-9a-zA-Z]+$").matcher(this.id).find()) {
            return false;
        }
        if(this.name == null || this.name.length() == 0 ||
                !Pattern.compile("[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]+").matcher(this.name).find()) {
            return false;
        }
        if(this.emailAddr != null &&
                !Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$").matcher(this.emailAddr).find()) {
            return false;
        }
        if(this.password == null || this.password.length() == 0 ||
                !Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$").matcher(this.password).find()) {
            return false;
        }
        return true;
    }
}
