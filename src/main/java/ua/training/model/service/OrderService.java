package ua.training.model.service;

import ua.training.dao.OrderDao;
import ua.training.dao.PeriodicalDao;
import ua.training.dao.UserArticleDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.*;
import ua.training.util.constants.Attributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


public class OrderService {
    @SuppressWarnings("unchecked")
    public void createOrder(HttpServletRequest req) {
        Set<Periodical> periodicals = (Set<Periodical>) req.getSession().getAttribute(Attributes.BASKET);
        int totalPrice = (int) req.getSession().getAttribute(Attributes.BASKET_PRICE);
        User user = (User) req.getSession().getAttribute(Attributes.USER);
        Order order = new Order.OrderBuilder()
                            .buildTotalPrice(totalPrice)
                            .buildDate(LocalDate.now())
                            .buildUser(user)
                            .buildPeriodicals(periodicals)
                            .buildOrder();

        try (OrderDao orderDao = DaoFactory.getInstance().createOrderDao();
             UserArticleDao userArticleDao = DaoFactory.getInstance().createUserArticleDao()) {
            orderDao.create(order);
            user.setBalance(user.getBalance() - order.getTotalPrice());
            for (Periodical periodical : periodicals) {
                for (Article article : periodical.getArticles()) {
                    userArticleDao.create(new UserArticle.UserArticleBuilder()
                                                        .buildUser(user)
                                                        .buildArticle(article)
                                                        .buildRead(true)
                                                        .buildUserArticle());
                }
            }
        }
    }

    public List<Periodical> periodicalsPurchased(int userId) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            return periodicalDao.findByUser(userId);
        }
    }
}
