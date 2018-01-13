package ua.training.controller.filter;

import ua.training.controller.command.role.AdminCommands;
import ua.training.controller.command.role.CommandHolder;
import ua.training.controller.command.role.GuestCommands;
import ua.training.controller.command.role.UserCommands;
import ua.training.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthFilter implements Filter {
    private Map<Role, CommandHolder> commandsByRole;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        commandsByRole = new HashMap<>();
        commandsByRole.put(Role.ADMIN, new AdminCommands());
        commandsByRole.put(Role.USER, new UserCommands());
        commandsByRole.put(Role.GUEST, new GuestCommands());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Role userRole = ((Role)req.getSession().getAttribute("user_role"));
        if (userRole == null) {
            userRole = Role.GUEST;
            req.getSession().setAttribute("user_role", userRole);
        }

        CommandHolder commands = commandsByRole.get(userRole);
        req.getSession().setAttribute("available_commands", commands);
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
