package ua.training.controller.command;

import ua.training.controller.util.Util;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.exception.NotUniqueEmailException;
import ua.training.model.service.UserService;
import ua.training.util.Localization;
import ua.training.util.constants.Attributes;
import ua.training.util.constants.Messages;
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
        String firstName = req.getParameter(Parameteres.FIRST_NAME);
        String secondName = req.getParameter(Parameteres.SECOND_NAME); //TODO choose role while register (add publisher role first)

        if (!Util.emailIsValid(email)) { //FIXME you can register without first and last name.
            setErrorMessage(req, Localization.getMessage(Messages.INVALID_EMAIL));
            return (String) req.getSession().getAttribute(Attributes.PREVIOUS_PAGE);
        } else if (!Util.passwordIsValid(password)) {
            setErrorMessage(req, Localization.getMessage(Messages.INVALID_PASSWORD));
            return (String) req.getSession().getAttribute(Attributes.PREVIOUS_PAGE);
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
            setErrorMessage(req, Localization.getMessage(Messages.NOT_UNIQUE_EMAIL));
            return (String) req.getSession().getAttribute(Attributes.PREVIOUS_PAGE);
        }

        req.setAttribute(Attributes.MESSAGE, Localization.getMessage(Messages.REGISTER_SUCCESSFUL));
        return (String) req.getSession().getAttribute(Attributes.PREVIOUS_PAGE);

    }

    private void setErrorMessage(HttpServletRequest req, String message) {
        req.setAttribute(Attributes.SHOW_REGISTER_MODAL, Messages.SHOW_REGISTER_MODAL);
        req.setAttribute(Attributes.REGISTER_MESSAGE, message);
    }
}
