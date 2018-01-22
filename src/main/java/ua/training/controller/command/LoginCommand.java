package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.util.constants.Attributes;
import ua.training.util.constants.Messages;
import ua.training.util.constants.Parameteres;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter(Parameteres.EMAIL);
        String password = req.getParameter(Parameteres.PASSWORD);

        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            req.getSession().setAttribute(Attributes.USER, user);
            req.getSession().setAttribute(Attributes.USER_ROLE, user.getRole());
            return (String) req.getSession().getAttribute(Attributes.PREVIOUS_PAGE);
        }

        req.setAttribute(Attributes.SHOW_LOGIN_MODAL, Messages.SHOW_LOGIN_MODAL);
        req.setAttribute(Attributes.LOGIN_MESSAGE, Messages.NO_SUCH_USER);  //TODO i18n
        return (String) req.getSession().getAttribute(Attributes.PREVIOUS_PAGE);
    }
}
