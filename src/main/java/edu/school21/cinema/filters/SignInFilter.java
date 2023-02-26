package edu.school21.cinema.filters;

import edu.school21.cinema.repositories.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/signIn"})
public class SignInFilter implements Filter {
    private ApplicationContext springContext;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ServletContext servletContext = filterConfig.getServletContext();
        springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        userRepository = springContext.getBean(UserRepository.class);
        passwordEncoder = springContext.getBean(PasswordEncoder.class);
    }
//
    @Override
    public void doFilter(ServletRequest requestServlet, ServletResponse responseServlet, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) requestServlet;
        HttpServletResponse response = (HttpServletResponse) responseServlet;

        if (!request.getMethod().equals("POST")) {
            chain.doFilter(requestServlet, responseServlet);
            return;
        }
        if (requestServlet.getParameter("email").isEmpty() || requestServlet.getParameter("password").isEmpty()) {
            response.sendRedirect("/WEB-INF/html/signIn.html");
        } else {
            chain.doFilter(requestServlet, responseServlet);
        }
    }
}
