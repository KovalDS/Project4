package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalService;
import ua.training.util.constants.Attributes;
import ua.training.util.constants.Parameteres;

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
        req.getSession().setAttribute(Attributes.PREVIOUS_PAGE, "/add_article?periodical_id=" + periodicalId);
        return "/WEB-INF/view/add_article.jsp";
    }
}
