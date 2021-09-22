package commands;

import com.example.demo_beauty_saloon.commands.AdminCommand;
import org.junit.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AdminCommandTest {
    private AdminCommand adminCommand = new AdminCommand();

    private HttpSession session = mock(HttpSession.class);

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void adminTest() throws ServletException, IOException {
        assertEquals("/admin/admin_panel.jsp", adminCommand.execute(request, response));
    }
}
