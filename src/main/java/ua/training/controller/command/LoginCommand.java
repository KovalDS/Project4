package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.PeriodicalService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            req.getSession().setAttribute("user_role", user.getRoles().get(0)); //TODO very wrong here
            return new DefaultCommand(new PeriodicalService()).execute(req, resp);
        }

        req.setAttribute("message", "No such user");  //TODO
        return new DefaultCommand(new PeriodicalService()).execute(req, resp);


    }
}
