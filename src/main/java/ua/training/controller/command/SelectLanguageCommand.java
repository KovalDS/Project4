package ua.training.controller.command;

import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String language = req.getParameter("language");

        req.getSession().setAttribute("language", language);
        return "/home";
    }
}
