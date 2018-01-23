package ua.training.controller.command;

import ua.training.controller.util.Util;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.util.text.Localization;
import ua.training.util.text.constants.Attributes;
import ua.training.util.text.constants.Messages;
import ua.training.util.text.constants.Pages;
import ua.training.util.text.constants.Parameteres;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReplenishAccountCommand implements Command {  //TODO make this command update all user fields depending on what data has been passed in request
    private UserService userService;

    public ReplenishAccountCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int amount;

        User user = (User) req.getSession().getAttribute(Attributes.USER);
        String amountStr = req.getParameter(Parameteres.AMOUNT);

        if (!Util.priceIsValid(amountStr)) {
            req.setAttribute(Attributes.MESSAGE, Localization.getMessage(Messages.WRONG_PRICE_FORMAT_MESSAGE));
            return Pages.MY_ACCOUNT;
        }

        amountStr = amountStr.replaceAll("[,.]", "");
        amount = Integer.parseInt(amountStr);

        user.setBalance(user.getBalance() + amount);
        userService.updateUser(user);
        return Pages.MY_ACCOUNT;
    }
}
