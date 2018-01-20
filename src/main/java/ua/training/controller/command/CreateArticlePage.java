package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateArticlePage implements Command {
    PeriodicalService periodicalService;

    public CreateArticlePage(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int periodicalId = Integer.parseInt(req.getParameter("periodical_id"));
        Periodical periodical = periodicalService.getPeriodicalById(periodicalId);
        req.setAttribute("periodical", periodical);
        req.getSession().setAttribute("previous_page", "/add_article?periodical_id=" + periodicalId);
        return "/WEB-INF/view/add_article.jsp";
    }
}
