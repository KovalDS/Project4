package ua.training.controller.command;

import ua.training.controller.util.Util;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.exception.NotUniqueEmailException;
import ua.training.model.service.PeriodicalService;
import ua.training.model.service.UserService;
import ua.training.util.constants.Parameteres;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {
    private UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter(Parameteres.EMAIL);
        String password = req.getParameter(Parameteres.PASSWORD);
        String firstName = req.getParameter("first_name");
        String secondName = req.getParameter("second_name"); //TODO choose role while register (add publisher role first)

        if (!Util.emailIsValid(email)) {
            req.setAttribute("show_register_modal", "$(\"#register_modal\").modal(\"show\");");
            req.setAttribute("register_message", "Invalid email");
            return (String) req.getSession().getAttribute("previous_page");
        } else if (!Util.passwordIsValid(password)) {
            req.setAttribute("show_register_modal", "$(\"#register_modal\").modal(\"show\");");
            req.setAttribute("register_message", "Password must be longer than 8 characters");
            return (String) req.getSession().getAttribute("previous_page");
        }

        try {
            userService.registerUser(new User.UserBuilder()
                    .buildEmail(email)
                    .buildPassword(password)
                    .buildFirstName(firstName)
                    .buildSecondName(secondName)
                    .buildBalance(0)
                    .buildRole(Role.USER)
                    .buildUser());
        } catch (NotUniqueEmailException e) {
            req.setAttribute("show_register_modal", "$(\"#register_modal\").modal(\"show\");");
            req.setAttribute("register_message", "Email already registered");
            return (String) req.getSession().getAttribute("previous_page");
        }

        req.setAttribute("message", "<div class=\"alert alert-success\">You are registered! Now you can <a href=\"#\" class=\"alert-link\" data-toggle=\"modal\" data-target=\"#login_modal\">login</a></div>");
        return (String) req.getSession().getAttribute("previous_page");

    }
}
