package ua.training.controller.command.role;

import ua.training.controller.command.*;
import ua.training.model.service.*;

import java.util.HashMap;

public class AdminCommands extends CommandHolder {

    public AdminCommands() {
        initCommands();
    }

    @Override
    public void initCommands() {
        commands = new HashMap<>();

        addCommand("/", new ShowPeriodicalsList(new PeriodicalService()));
        addCommand("/home", new ShowPeriodicalsList(new PeriodicalService()));
        addCommand("/logout", new LogoutCommand());
        addCommand("/periodical", new ShowArticlesListCommand(new PeriodicalService()));
        addCommand("/periodical/article", new ShowArticleCommand(new ArticleService()));
        addCommand("/add_to_basket", new AddToBasketCommand(new PeriodicalService()));
        addCommand("/delete_from_basket", new DeleteFromBasket(new PeriodicalService()));
        addCommand("/subscribe", new SubscribeCommand(new OrderService()));
        addCommand("/add_periodical", new CreatePeriodicalPageCommand());
        addCommand("/add_periodical/create_periodical", new CreatePeriodical(new AdminService()));
        addCommand("/add_article", new CreateArticlePage(new PeriodicalService()));
        addCommand("/add_article/create_article", new CreateArticle(new AdminService()));
        addCommand("/my_account", new MyAccountCommand());
        addCommand("/my_subscriptions", new MySubscriptionsCommand(new PeriodicalService()));
        addCommand("/my_account/update", new ReplenishAccountCommand(new UserService()));
        addCommand("/select_lang", new SelectLanguageCommand());
    }

    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }
}
