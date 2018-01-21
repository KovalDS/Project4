package ua.training.controller.filter;

import ua.training.model.entity.Article;
import ua.training.model.entity.User;
import ua.training.model.service.ArticleService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO think about new name
public class ArticleObserverFilter implements Filter {
    private ArticleService articleService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        articleService = new ArticleService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        List<Article> unreadArticles = new ArrayList<>();

        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            unreadArticles = articleService.getUnreadArticles(user.getId());
        }

        req.getSession().setAttribute("unread_articles", unreadArticles);
        if (unreadArticles.size() == 0) {
            req.getSession().removeAttribute("unread_articles_badge");
        } else {
            req.getSession().setAttribute("unread_articles_badge", "<span class=\"badge progress-bar-danger\">" + unreadArticles.size() + "</span>");
        }

        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
