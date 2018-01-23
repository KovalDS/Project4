package ua.training.controller.command;

import ua.training.model.entity.Role;
import ua.training.util.text.constants.Attributes;
import ua.training.util.text.constants.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        req.getSession().setAttribute(Attributes.USER_ROLE, Role.GUEST);
        return Pages.LOGOUT;
    }
}
