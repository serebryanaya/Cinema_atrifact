package edu.school21.cinema.servlets;

import edu.school21.cinema.services.UsersService;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private ApplicationContext springContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        springContext = (ApplicationContext)config.getServletContext().getAttribute("springContext");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/signUp.html");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersService usersService = springContext.getBean("usersService", UsersService.class);
        if (usersService.signUp(request.getParameter("email"), request.getParameter("password"), request.getRemoteAddr()))
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", usersService.getUserByEmail(request.getParameter("email")));
            session.setAttribute("auth", usersService.getAuthHistoryByEmail(request.getParameter("email")));
            response.sendRedirect("index");
        } else response.sendError(HttpServletResponse.SC_FORBIDDEN, "Decline with code 403. Try again!");

    }
}
