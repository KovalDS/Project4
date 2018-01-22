package ua.training.controller.command;

import ua.training.util.constants.Commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyAccountCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("previous_page", Commands.MY_ACCOUNT);
        return "WEB-INF/view/my_account.jsp";
    }
}
