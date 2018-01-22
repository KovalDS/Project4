package ua.training.controller.command.role;

import ua.training.controller.command.*;
import ua.training.model.service.*;
import ua.training.util.constants.Commands;

import java.util.HashMap;

public class AdminCommands extends CommandHolder {

    public AdminCommands() {
        initCommands();
    }

    @Override
    public void initCommands() {
        commands = new HashMap<>();

        addCommand(Commands.DEFAULT_COMMAND, new ShowPeriodicalsList(new PeriodicalService()));
        addCommand(Commands.HOME, new ShowPeriodicalsList(new PeriodicalService()));
        addCommand(Commands.LOGOUT, new LogoutCommand());
        addCommand(Commands.PERIODICAL, new ShowArticlesListCommand(new ArticleService()));
        addCommand(Commands.ARTICLE, new ShowArticleCommand(new ArticleService()));
        addCommand(Commands.ADD_TO_BASKET, new AddToBasketCommand(new PeriodicalService()));
        addCommand(Commands.DELETE_FROM_BASKET, new DeleteFromBasket(new PeriodicalService()));
        addCommand(Commands.SUBSCRIBE, new SubscribeCommand(new OrderService()));
        addCommand(Commands.ADD_PERIODICAL_PAGE, new CreatePeriodicalPageCommand());
        addCommand(Commands.CREATE_PERIODICAL, new CreatePeriodical(new AdminService()));
        addCommand(Commands.ADD_ARTICLE_PAGE, new CreateArticlePage(new PeriodicalService()));
        addCommand(Commands.CREATE_ARTICLE, new CreateArticle(new AdminService()));
        addCommand(Commands.MY_ACCOUNT, new MyAccountCommand());
        addCommand(Commands.MY_SUBSCRIPTIONS, new MySubscriptionsCommand(new PeriodicalService()));
        addCommand(Commands.UPDATE_ACCOUNT, new ReplenishAccountCommand(new UserService()));
        addCommand(Commands.SELECT_LANGUAGE, new SelectLanguageCommand());
    }

    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }
}
