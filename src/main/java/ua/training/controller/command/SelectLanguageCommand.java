package ua.training.controller.command;

import ua.training.model.service.PeriodicalService;
import ua.training.util.constants.Parameteres;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String language = req.getParameter(Parameteres.LANGUAGE);
        req.getSession().setAttribute("language", language);

        return (String) req.getSession().getAttribute("previous_page");
    }
}
