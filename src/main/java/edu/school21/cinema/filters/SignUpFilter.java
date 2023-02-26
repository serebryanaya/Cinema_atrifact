//package edu.school21.cinema.filters;
//
//import edu.school21.cinema.repositories.UserRepository;
//import org.springframework.context.ApplicationContext;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.servlet.*;
//import javax.servlet.annotation.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = {"/signIn"})
//public class SignUpFilter implements Filter {
//    private ApplicationContext springContext;
//    private PasswordEncoder passwordEncoder;
//    private UserRepository userRepository;
//
//    public void init(FilterConfig config) throws ServletException {
//        Filter.super.init(config);
//        ServletContext servletContext = config.getServletContext();
//        springContext = (ApplicationContext) servletContext.getAttribute("springContext");
//        userRepository = springContext.getBean(UserRepository.class);
//        passwordEncoder = springContext.getBean(PasswordEncoder.class);
//    }
//
//    @Override
//    public void doFilter(ServletRequest requestServlet, ServletResponse responseServlet, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest request = (HttpServletRequest) requestServlet;
//        HttpServletResponse response = (HttpServletResponse) responseServlet;
//
//        if (!request.getMethod().equals("POST")) {
//            chain.doFilter(requestServlet, responseServlet);
//            return;
//        }
//        if (request.getParameter("firstName").isEmpty()
//                || request.getParameter("lastName").isEmpty()
//                || request.getParameter("email").matches("/^\\S+@\\S+\\.\\S+$/")
//                || !request.getParameter("phoneNumber").matches("\\d+")
//                || request.getParameter("password").isEmpty()) {
//            response.sendRedirect(request.getRequestURI());
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//}