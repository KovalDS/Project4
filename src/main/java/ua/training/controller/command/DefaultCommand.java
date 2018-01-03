package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DefaultCommand implements Command {
    PeriodicalService periodicalService;

    public DefaultCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = "/WEB-INF/view/periodicals.jsp";

        List<Periodical> periodicalList = periodicalService.getAllPeriodicals();
        req.setAttribute("periodical_list", periodicalList);
        return page;
    }
}
