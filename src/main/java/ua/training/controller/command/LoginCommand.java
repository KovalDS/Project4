package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.PeriodicalService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("user_role", user.getRole());
            return (String) req.getSession().getAttribute("previous_page");
        }

        req.setAttribute("show_login_modal", "$(\"#login_modal\").modal(\"show\");");
        req.setAttribute("login_message", "No such user");  //TODO i18n
        return (String) req.getSession().getAttribute("previous_page");
    }
}
