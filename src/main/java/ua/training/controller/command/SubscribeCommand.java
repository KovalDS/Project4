package ua.training.controller.command;

import ua.training.controller.Util;
import ua.training.model.entity.Order;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.exception.TransactionFailedException;
import ua.training.model.service.OrderService;
import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SubscribeCommand implements Command {
    OrderService orderService;

    public SubscribeCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Order order = null;
        List<Periodical> purchasedPeriodical;

        Set<Periodical> basket = (Set) req.getSession().getAttribute("basket");
        User user = (User) req.getSession().getAttribute("user");
        Role role = (Role) req.getSession().getAttribute("user_role");

        if (role.equals(Role.GUEST)) {
            req.setAttribute("message", "<div class=\"alert alert-danger\">You must be <a href=\"#\" class=\"alert-link\" data-toggle=\"modal\" data-target=\"#login_modal\">logged in</a> to subscribe</div>");
            return new DefaultCommand(new PeriodicalService()).execute(req, resp);
        }

        purchasedPeriodical = orderService.periodicalsPurchased(user.getId());
        if (!Util.listIntersection(purchasedPeriodical, new ArrayList<Periodical>(basket)).isEmpty()) { //TODO instead of this better to catch duplication exception from user_has_periodical
            req.setAttribute("message", "<div class=\"alert alert-danger\">You are already subscribed to this periodical!</div>");
            return new DefaultCommand(new PeriodicalService()).execute(req, resp);
        }

        try  {
            orderService.createOrder(req);
        } catch (TransactionFailedException e) {
            req.setAttribute("message", "<div class=\"alert alert-danger\">Not enough money on your balance</div>");
            return new DefaultCommand(new PeriodicalService()).execute(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        req.getSession().setAttribute("user", order.getUser());
        req.getSession().removeAttribute("basket_badge");
        req.getSession().removeAttribute("total_basket_price");
        req.getSession().removeAttribute("basket");
        req.getSession().removeAttribute("basket_size");

        return new DefaultCommand(new PeriodicalService()).execute(req, resp);
    }
}
