package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateArticlePage implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/view/add_article.jsp";
    }
}
