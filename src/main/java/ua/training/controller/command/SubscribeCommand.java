package ua.training.controller.command;

import ua.training.controller.util.Util;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.exception.NotEnoughBalanceException;
import ua.training.model.exception.SubscriptionDuplicationException;
import ua.training.model.service.OrderService;

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

        Role role = (Role) req.getSession().getAttribute("user_role");

        if (role.equals(Role.GUEST)) {
            req.setAttribute("message", "<div class=\"alert alert-danger\">You must be <a href=\"#\" class=\"alert-link\" data-toggle=\"modal\" data-target=\"#login_modal\">logged in</a> to subscribe</div>");
            return (String) req.getSession().getAttribute("previous_page");
        }

        try  {
            orderService.createOrder(req);
            req.getSession().removeAttribute("basket_badge");
            req.getSession().removeAttribute("total_basket_price");
            req.getSession().removeAttribute("basket");
            req.getSession().removeAttribute("basket_size");
        } catch (NotEnoughBalanceException e) {
            req.setAttribute("message", "<div class=\"alert alert-danger\">Not enough money on your balance</div>");
        } catch (SubscriptionDuplicationException e) {
            req.setAttribute("message", "<div class=\"alert alert-danger\">You are already subscribed to this periodical!</div>");
        }

        return (String) req.getSession().getAttribute("previous_page");
    }
}
