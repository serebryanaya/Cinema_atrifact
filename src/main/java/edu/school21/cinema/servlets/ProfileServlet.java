package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/profile/*")
public class ProfileServlet extends HttpServlet {
    private ApplicationContext springContext;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
        userRepository = springContext.getBean(UserRepository.class);
        passwordEncoder = springContext.getBean(PasswordEncoder.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        User user = null;
        if (request.getRequestURI().equals("/profile")) {
            user = (User) request.getSession().getAttribute("user");
        } else {
//            Long id = Long.parseLong(request.getRequestURI().substring("/profile/".length()));
            Long id = Long.parseLong(request.getRequestURI().substring(request.getContextPath().length()));
            user = userRepository.findUserById(id);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
