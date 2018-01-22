package ua.training.controller.command;

import ua.training.controller.util.Util;
import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;
import ua.training.model.service.AdminService;
import ua.training.util.constants.Attributes;
import ua.training.util.constants.Parameteres;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class CreateArticle implements Command {
    private AdminService adminService;

    public CreateArticle(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter(Parameteres.ARTICLE_NAME);
        String text = req.getParameter(Parameteres.ARTICLE_TEXT);
        int periodicalId = Integer.parseInt(req.getParameter(Parameteres.PERIODICAL_ID));

        Periodical periodical = adminService.getPeriodicalById(periodicalId);

        text = Util.parseStringToHtml(text);
        adminService.createArticle(new Article.ArticleBuilder()
                                            .buildName(name)
                                            .buildText(text)
                                            .buildDateOfPublication(LocalDate.now())
                                            .buildPeriodical(periodical)
                                            .buildArticle());

        req.setAttribute(Attributes.PERIODICAl, periodical);

        return "/WEB-INF/view/add_article.jsp";
    }
}
