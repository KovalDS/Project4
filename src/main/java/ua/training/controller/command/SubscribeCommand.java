package ua.training.controller.command;

import ua.training.model.entity.Role;
import ua.training.model.exception.NotEnoughBalanceException;
import ua.training.model.exception.SubscriptionDuplicationException;
import ua.training.model.service.OrderService;
import ua.training.util.constants.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscribeCommand implements Command {
    private OrderService orderService;

    public SubscribeCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        Role role = (Role) req.getSession().getAttribute(Attributes.USER_ROLE);

        if (role.equals(Role.GUEST)) {
            req.setAttribute(Attributes.MESSAGE, "<div class=\"alert alert-danger\">You must be <a href=\"#\" class=\"alert-link\" data-toggle=\"modal\" data-target=\"#login_modal\">logged in</a> to subscribe</div>");
            return (String) req.getSession().getAttribute(Attributes.PREVIOUS_PAGE);
        }

        try  {
            orderService.createOrder(req);
            req.getSession().removeAttribute(Attributes.BASKET_BADGE);
            req.getSession().removeAttribute(Attributes.BASKET_PRICE);
            req.getSession().removeAttribute(Attributes.BASKET);
        } catch (NotEnoughBalanceException e) {
            req.setAttribute(Attributes.MESSAGE, "<div class=\"alert alert-danger\">Not enough money on your balance</div>");
        } catch (SubscriptionDuplicationException e) {
            req.setAttribute(Attributes.MESSAGE, "<div class=\"alert alert-danger\">You are already subscribed to this periodical!</div>");
        }

        return (String) req.getSession().getAttribute(Attributes.PREVIOUS_PAGE);
    }
}
