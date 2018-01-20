package ua.training.controller.command.role;

import ua.training.controller.command.*;
import ua.training.model.service.ArticleService;
import ua.training.model.service.OrderService;
import ua.training.model.service.PeriodicalService;
import ua.training.model.service.UserService;

import java.util.HashMap;

public class UserCommands extends CommandHolder {

    public UserCommands() {
        initCommands();
    }

    @Override
    public void initCommands() {
        commands = new HashMap<>();

        addCommand("/", new ShowPeriodicalsList(new PeriodicalService()));
        addCommand("/home", new ShowPeriodicalsList(new PeriodicalService()));
        addCommand("/logout", new LogoutCommand());
        addCommand("/periodical", new ShowArticlesListCommand(new ArticleService()));
        addCommand("/periodical/article", new ShowArticleCommand(new ArticleService()));
        addCommand("/add_to_basket", new AddToBasketCommand(new PeriodicalService()));
        addCommand("/delete_from_basket", new DeleteFromBasket(new PeriodicalService()));
        addCommand("/subscribe", new SubscribeCommand(new OrderService()));
        addCommand("/my_account", new MyAccountCommand());
        addCommand("/my_subscriptions", new MySubscriptionsCommand(new PeriodicalService()));
        addCommand("/my_account/update", new ReplenishAccountCommand(new UserService()));
        addCommand("/select_lang", new SelectLanguageCommand());
    }

    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }
}
