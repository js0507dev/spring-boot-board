package js.spring.boot.board.user.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private List<UserRole> roles;
}
