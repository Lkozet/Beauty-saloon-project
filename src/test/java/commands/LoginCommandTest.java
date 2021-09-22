package commands;

import com.example.demo_beauty_saloon.commands.LoginCommand;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginCommandTest {
    private LoginCommand loginCommand = new LoginCommand();

    private HttpSession session = mock(HttpSession.class);

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);

    private String login = "admin";
    private String correctPassword = "admin";
    private String incorrectPassword = "---";


    @Test
    public void shouldLoginIfCorrectLoginPass() throws IOException, ServletException {

        when(request.getParameter("username")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(correctPassword);
        when(request.getSession()).thenReturn(session);
        assertEquals("/index.jsp", loginCommand.execute(request, response));
    }

    @Test
    public void shouldNotLoginIfCorrectLoginPass() throws IOException, ServletException {

        when(request.getParameter("username")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(incorrectPassword);
        when(request.getSession()).thenReturn(session);
        assertEquals("/login.jsp", loginCommand.execute(request, response));
    }

}
