package js.spring.boot.board.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SecurityUser extends org.springframework.security.core.userdetails.User {
    private static final String ROLE_PREFIX = "ROLE_";
    private static final long serialVersionUID = 1L;

    public SecurityUser(js.spring.boot.board.user.model.User user) {
        super(user.getId(), user.getPassword(), makeGrantedAuthority(user.getRoles()));
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<UserRole> roles){
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
        return list;
    }
}
