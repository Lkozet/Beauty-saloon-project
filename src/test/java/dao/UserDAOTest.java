package dao;

import com.example.demo_beauty_saloon.dao.UserDao;
import com.example.demo_beauty_saloon.entity.User;
import org.junit.*;

import static org.junit.Assert.*;

public class UserDAOTest {
    static UserDao userDAO;
    static User user;

    @Test
    public void findUserByLogin() {
        userDAO = new UserDao();
        user = new User();
        user.setName("login");
        user.setPassword("password");
        user.setEmail("email");
        user.setPhone("phone");
        userDAO.registerUser(user);
        User newUser = new User();
        newUser = userDAO.findUserByLogin(user.getName());
        userDAO.deleteUser(newUser);
        assertEquals(user.getName(), newUser.getName());
    }

    @Test
    public void deleteUser() {
        User newUser = new User();
        newUser.setName("_");
        newUser.setPassword("_");
        newUser.setEmail("_");
        newUser.setPhone("_");
        userDAO.registerUser(newUser);
        userDAO.deleteUser(userDAO.findUserByLogin(newUser.getName()));
        assertNull(userDAO.findUserByLogin(newUser.getName()));
    }

}