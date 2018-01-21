package ua.training.controller.command;

import ua.training.controller.util.Util;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

public class ReplenishAccountCommand implements Command {  //TODO make this command update all user fields depending on what data has been passed in request
    private UserService userService;

    public ReplenishAccountCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int amount;

        User user = (User) req.getSession().getAttribute("user");
        String amountStr = req.getParameter("amount");

        if (!Util.priceIsValid(amountStr)) {
            req.setAttribute("message", "<div class=\"alert alert-danger\">Please, input price in format X.XX</div>");
            return "/WEB-INF/view/my_account.jsp";
        }

        amountStr = amountStr.replaceAll("[,.]", "");
        amount = Integer.parseInt(amountStr);

        user.setBalance(user.getBalance() + amount);
        userService.updateUser(user);
        return "/WEB-INF/view/my_account.jsp";
    }
}
