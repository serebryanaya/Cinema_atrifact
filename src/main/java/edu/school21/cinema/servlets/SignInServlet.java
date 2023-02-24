package edu.school21.cinema.servlets;

import edu.school21.cinema.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private ApplicationContext springContext;
    private PasswordEncoder passwordEncoder;
    @Override
    public void init(ServletConfig config) throws ServletException {
        springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/signIn.html");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersService usersService = springContext.getBean("usersService", UsersService.class);
        if (usersService.signIn(request.getParameter("email"), request.getParameter("password"),
        //        if (usersService.signIn(request.getParameter("email"), request.getParameter("password").toCharArray(),
                request.getRemoteAddr())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", usersService.getUserByEmail(request.getParameter("email")));
//            session.setAttribute();
            response.sendRedirect("profile");
        }
        else
            response.sendRedirect("signIn");
    }
}
