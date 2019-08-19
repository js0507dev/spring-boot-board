package js.spring.boot.board.user.repository;

import js.spring.boot.board.user.model.User;
import js.spring.boot.board.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private List<User> userList;

    @Before
    public void setup() throws Exception {
        userList = new ArrayList<>();

        User user1 = User.builder()
                .id("test1")
                .name("testuser")
                .password("test")
                .emailAddr("test@email.com")
                .build();

        User user2 = User.builder()
                .id("test2")
                .name("testuser22")
                .password("test22")
                .emailAddr("test2@email.com")
                .build();

        userList.add(user1);
        userList.add(user2);
    }

    @Test
    public void saveAndFindAllTest() throws Exception {
        userRepository.saveAll(userList);

        List<User> checkUserList = userRepository.findAll();
        Assert.assertEquals(checkUserList.size(), userList.size());

        int checkIdx = 0;
        for(; checkIdx < checkUserList.size(); checkIdx++) {
            Assert.assertEquals(checkUserList.get(checkIdx).toString(),
                    userList.get(checkIdx).toString());
        }
    }

    @Test
    public void saveAndUpdateTest() throws Exception {
        User changeUser = new User(userList.get(0));
        userRepository.saveAll(userList);

        changeUser.setName("changeTest1");
        changeUser.setEmailAddr("changeTest@test.com");
        userRepository.save(changeUser);

        User orgUser = userList.get(0);
        User afterUser = userRepository.findById(orgUser.getId()).orElse(null);

        Assert.assertNotNull(afterUser);
        Assert.assertNotEquals(afterUser.getName(), orgUser.getName());
        Assert.assertNotEquals(afterUser.getEmailAddr(), orgUser.getEmailAddr());
    }

    @Test
    public void saveAndDeleteTest() throws Exception {
        List<User> checkList1 = userRepository.saveAll(userList);
        userRepository.delete(userList.get(0));

        List<User> checkList2 = userRepository.findAll();

        Assert.assertNotEquals(checkList1.size(), checkList2.size());
    }
}
