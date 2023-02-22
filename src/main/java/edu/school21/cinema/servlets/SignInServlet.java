package edu.school21.cinema.servlets;

import edu.school21.cinema.services.UsersService;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private ApplicationContext springContext;
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
        if (usersService.signIn(request.getParameter("email"), request.getParameter("password").toCharArray(),
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
