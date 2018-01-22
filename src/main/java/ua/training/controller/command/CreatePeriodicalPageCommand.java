package ua.training.controller.command;

import ua.training.util.constants.Commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatePeriodicalPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("previous_page", Commands.ADD_PERIODICAL_PAGE);
        return "/WEB-INF/view/add_periodical.jsp";
    }
}
