package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalService;
import ua.training.util.text.constants.Attributes;
import ua.training.util.text.constants.Commands;
import ua.training.util.text.constants.Pages;
import ua.training.util.text.constants.Parameteres;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateArticlePage implements Command {
    private PeriodicalService periodicalService;

    public CreateArticlePage(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int periodicalId = Integer.parseInt(req.getParameter(Parameteres.PERIODICAL_ID));
        Periodical periodical = periodicalService.getPeriodicalById(periodicalId);
        req.setAttribute(Attributes.PERIODICAl, periodical);
        req.getSession().setAttribute(Attributes.PREVIOUS_PAGE, Commands.ADD_ARTICLE_PAGE + "?" +
                                                                        Parameteres.PERIODICAL_ID + "=" + periodicalId);
        return Pages.ADD_ARTICLE;
    }
}
