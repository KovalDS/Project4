package ua.training.controller.filter;

import ua.training.model.entity.Article;
import ua.training.model.entity.User;
import ua.training.model.service.ArticleService;
import ua.training.util.constants.Attributes;
import ua.training.util.constants.Messages;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        User user = (User) req.getSession().getAttribute(Attributes.USER);

        if (user != null) {
            unreadArticles = articleService.getUnreadArticles(user.getId());
        }

        req.getSession().setAttribute(Attributes.UNREAD_ARTICLES, unreadArticles);
        if (unreadArticles.size() == 0) {
            req.getSession().removeAttribute(Attributes.UNREAD_ARTICLES_BADGE);
        } else {
            req.getSession().setAttribute(Attributes.UNREAD_ARTICLES_BADGE, Messages.BADGE_OPEN_TAG +
                                                                    unreadArticles.size() + Messages.BADGE_CLOSE_TAG);
        }

        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
