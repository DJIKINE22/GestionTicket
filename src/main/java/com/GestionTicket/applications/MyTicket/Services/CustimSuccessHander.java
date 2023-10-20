package com.GestionTicket.applications.MyTicket.Services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustimSuccessHander implements AuthenticationSuccessHandler {
    /**
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var authorities = authentication.getAuthorities();
        var roles = authorities.stream().map(r->r.getAuthority()).findFirst();
        if (roles.orElse("").equals("Admin")){
            response.sendRedirect("/admin-page");

        } else if (roles.orElse("").equals("SimpleUser")){
            response.sendRedirect("/userDashboard");}

        else {
            response.sendRedirect("/ErrorPage");
        }}
}