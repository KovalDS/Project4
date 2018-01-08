package ua.training.controller.command.role;

import ua.training.controller.command.*;
import ua.training.model.service.ArticleService;
import ua.training.model.service.OrderService;
import ua.training.model.service.PeriodicalService;

import java.util.HashMap;

public class UserCommands extends CommandHolder {

    public UserCommands() {
        initCommands();
    }

    @Override
    public void initCommands() {
        commands = new HashMap<>();

        addCommand("/", new DefaultCommand(new PeriodicalService()));
        addCommand("/home", new DefaultCommand(new PeriodicalService()));
        addCommand("/logout", new LogoutCommand());
        addCommand("/periodical", new ShowArticlesListCommand(new PeriodicalService()));
        addCommand("/periodical/article", new ShowArticleCommand(new ArticleService()));
        addCommand("/add_to_basket", new AddToBasketCommand(new PeriodicalService()));
        addCommand("/delete_from_basket", new DeleteFromBasket(new PeriodicalService()));
        addCommand("/subscribe", new SubscribeCommand(new OrderService()));
    }

    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }
}
