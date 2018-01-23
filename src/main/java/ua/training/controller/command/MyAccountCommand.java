package ua.training.controller.command;

import ua.training.util.text.constants.Attributes;
import ua.training.util.text.constants.Commands;
import ua.training.util.text.constants.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyAccountCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute(Attributes.PREVIOUS_PAGE, Commands.MY_ACCOUNT);
        return Pages.MY_ACCOUNT;
    }
}
