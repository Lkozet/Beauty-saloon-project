package commands;

import com.example.demo_beauty_saloon.commands.RegisterCommand;
import com.example.demo_beauty_saloon.dao.UserDao;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterCommandTest {
    private RegisterCommand registerCommand = new RegisterCommand();

    private HttpSession session = mock(HttpSession.class);

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);

    private String login = "testUser";
    private String password = "test_pass";
    private String email = "test_email@test.com";
    private String phone = "+380123456789";

    @Test
    public void shouldRegisterUserIfDataIsValid() throws IOException, ServletException {
        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("phone")).thenReturn(phone);
        when(request.getSession()).thenReturn(session);
        assertEquals("/index.jsp", registerCommand.execute(request, response));
        UserDao userDao = new UserDao();
        userDao.deleteUser(userDao.findUserByLogin(login));
    }

}
