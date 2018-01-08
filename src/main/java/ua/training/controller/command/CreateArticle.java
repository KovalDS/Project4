package ua.training.controller.command;

import ua.training.controller.Util;
import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;
import ua.training.model.service.AdminService;
import ua.training.model.service.ArticleService;
import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class CreateArticle implements Command {
    AdminService adminService;

    public CreateArticle(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("article_name");
        String text = req.getParameter("article_text");
        int periodicalId = Integer.parseInt(req.getParameter("periodical_id"));

        Periodical periodical = adminService.getPeriodicalById(periodicalId);

        text = Util.parseStringToHtml(text);
        adminService.createArticle(new Article.ArticleBuilder()
                                            .buildName(name)
                                            .buildText(text)
                                            .buildDateOfPublication(LocalDate.now())
                                            .buildPeriodical(periodical)
                                            .buildArticle());

        req.setAttribute("periodical", periodical);

        return "/WEB-INF/view/add_article.jsp";
    }
}
