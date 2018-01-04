package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.exception.NotUniqueEmailException;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {
    private UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = "/WEB-INF/view/periodicals.jsp";

        String email = req.getParameter("email");
        String passsword = req.getParameter("password");
        String firstName = req.getParameter("first_name");
        String secondName = req.getParameter("second_name"); //TODO choose role while register (add publisher role first)

        try {
            userService.registerUser(new User.UserBuilder()
                    .buildEmail(email)
                    .buildPassword(passsword)
                    .buildFirstName(firstName)
                    .buildSecondName(secondName)
                    .buildBalance(0)
                    .buildUser());
        } catch (NotUniqueEmailException e) {
            req.setAttribute("message", "Email already registered");
        }

        return page;

    }
}
