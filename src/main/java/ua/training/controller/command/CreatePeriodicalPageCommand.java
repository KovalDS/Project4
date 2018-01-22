package ua.training.controller.command;

import ua.training.util.constants.Attributes;
import ua.training.util.constants.Commands;
import ua.training.util.constants.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatePeriodicalPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute(Attributes.PREVIOUS_PAGE, Commands.ADD_PERIODICAL_PAGE);
        return Pages.ADD_PERIODICAL;
    }
}
