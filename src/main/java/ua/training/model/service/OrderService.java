package ua.training.model.service;

import ua.training.dao.OrderDao;
import ua.training.dao.PeriodicalDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.Order;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.User;
import ua.training.model.exception.TransactionFailedException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class OrderService {
    public Order createOrder(HttpServletRequest req) throws Exception {
        Set<Periodical> periodicals = (Set<Periodical>) req.getSession().getAttribute("basket");
        int totalPrice = (int) req.getSession().getAttribute("total_basket_price");
        User user = (User) req.getSession().getAttribute("user");
        Order order = new Order.OrderBuilder()
                            .buildTotalPrice(totalPrice)
                            .buildDate(LocalDate.now())
                            .buildUser(user)
                            .buildPeriodicals(periodicals)
                            .buildOrder();

        try (OrderDao orderDao = DaoFactory.getInstance().createOrderDao()) {
            orderDao.create(order);
            user.setBalance(user.getBalance() - order.getTotalPrice());
            return order;
        }
    }

    public List<Periodical> periodicalsPurchased(int userId) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            return periodicalDao.findByUser(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
