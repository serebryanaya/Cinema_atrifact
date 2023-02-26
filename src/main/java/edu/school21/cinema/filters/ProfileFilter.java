//package edu.school21.cinema.filters;
//
//import javax.servlet.*;
//import javax.servlet.annotation.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = {"/profile/\\d"})
//public class ProfileFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest requestServlet, ServletResponse responseServlet, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest request = (HttpServletRequest)requestServlet;
//        HttpServletResponse response = (HttpServletResponse)responseServlet;
//
//        if (request.getRequestURI().matches("/profile/\\d")) {
//            chain.doFilter(requestServlet, responseServlet);
//        } else {
//            response.sendError(HttpServletResponse.SC_FORBIDDEN);
//        }
//    }
//}
