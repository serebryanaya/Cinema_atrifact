package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private ApplicationContext springContext;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        springContext = (ApplicationContext)config.getServletContext().getAttribute("springContext");
        userRepository = springContext.getBean(UserRepository.class);
        passwordEncoder = springContext.getBean(PasswordEncoder.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/signUp.html");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        UsersService usersService = springContext.getBean("usersService", UsersService.class);
//        if (usersService.signUp(request.getParameter("email"), request.getParameter("password"), request.getRemoteAddr()))
//        {
//            HttpSession session = request.getSession();
//            session.setAttribute("user", usersService.getUserByEmail(request.getParameter("email")));
//            session.setAttribute("auth", usersService.getAuthHistoryByEmail(request.getParameter("email")));
//            response.sendRedirect("index");
//        } else response.sendError(HttpServletResponse.SC_FORBIDDEN, "Decline with code 403. Try again!");
//
//    }
//}
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setPhoneNumber(request.getParameter("phoneNumber"));
        user.setPassword(passwordEncoder.encode(request.getParameter("password")));

        try {
            user = userRepository.saveUser(user);
            response.sendRedirect("/profile/" + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}