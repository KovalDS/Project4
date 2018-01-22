package ua.training.controller.command;

import ua.training.model.entity.Role;
import ua.training.model.exception.NotEnoughBalanceException;
import ua.training.model.exception.SubscriptionDuplicationException;
import ua.training.model.service.OrderService;
import ua.training.util.Localization;
import ua.training.util.constants.Attributes;
import ua.training.util.constants.Messages;

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
            req.setAttribute(Attributes.MESSAGE, Localization.getMessage(Messages.NOT_AUTHORIZED));
            return (String) req.getSession().getAttribute(Attributes.PREVIOUS_PAGE);
        }

        try  {
            orderService.createOrder(req);
            req.getSession().removeAttribute(Attributes.BASKET_BADGE);
            req.getSession().removeAttribute(Attributes.BASKET_PRICE);
            req.getSession().removeAttribute(Attributes.BASKET);
        } catch (NotEnoughBalanceException e) {
            req.setAttribute(Attributes.MESSAGE, Localization.getMessage(Messages.NOT_ENOUGH_MONEY));
        } catch (SubscriptionDuplicationException e) {
            req.setAttribute(Attributes.MESSAGE, Localization.getMessage(Messages.ALREADY_SUBSCRIBED));
        }

        return (String) req.getSession().getAttribute(Attributes.PREVIOUS_PAGE);
    }
}
