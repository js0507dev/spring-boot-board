package js.spring.boot.board.repository;

import js.spring.boot.board.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private User tempUser;

    @Before
    private void setup() throws Exception {
        tempUser.builder()
                .id("test1")
                .name("testuser")
                //.password()
                .emailAddr("test@email.com");
    }

    @Test
    public void addUserTest() throws Exception {
        userRepository.save(tempUser);
        List<User> list = userRepository.findAll();

        Assert.assertNotEquals(list.size(), 1);

        User checkUser = list.get(0);
        Assert.assertNotEquals(checkUser.getId(), tempUser.getId());
        Assert.assertNotEquals(checkUser.getName(), tempUser.getName());
        Assert.assertNotEquals(checkUser.getPassword(), tempUser.getPassword());
        Assert.assertNotEquals(checkUser.getEmailAddr(), tempUser.getEmailAddr());
    }

    @Test
    public void listUserTest() throws Exception {

    }

    @Test
    public void updateUserTest() throws Exception {

    }

    @Test
    public void deleteUserTest() throws Exception {

    }
}
