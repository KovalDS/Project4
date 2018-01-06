package ua.training.controller.command.role;

import ua.training.controller.command.*;
import ua.training.model.service.ArticleService;
import ua.training.model.service.PeriodicalService;

import java.util.HashMap;

public class UserCommands extends CommandHolder {

    public UserCommands() {
        initCommands();
    }

    @Override
    public void initCommands() {
        commands = new HashMap<>();

        addCommand("default_command", new DefaultCommand(new PeriodicalService()));
        addCommand("logout_command", new LogoutCommand());
        addCommand("show_articles_list_command", new ShowArticlesListCommand(new PeriodicalService()));
        addCommand("show_article_command", new ShowArticleCommand(new ArticleService()));
        addCommand("add_to_basket_command", new AddToBasketCommand(new PeriodicalService()));
    }

    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }
}
