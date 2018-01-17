package ua.training.controller.command.role;

import ua.training.controller.command.*;
import ua.training.model.service.OrderService;
import ua.training.model.service.PeriodicalService;
import ua.training.model.service.UserService;

import java.util.HashMap;

public class GuestCommands extends CommandHolder {

    public GuestCommands() {
        initCommands();
    }

    @Override
    public void initCommands() {
        commands = new HashMap<>();

        addCommand("/", new ShowPeriodicalsList(new PeriodicalService()));
        addCommand("/home", new ShowPeriodicalsList(new PeriodicalService()));
        addCommand("/home/login", new LoginCommand(new UserService()));
        addCommand("/home/register", new RegisterCommand(new UserService()));
        addCommand("/add_to_basket", new AddToBasketCommand(new PeriodicalService()));
        addCommand("/delete_from_basket", new DeleteFromBasket(new PeriodicalService()));
        addCommand("/subscribe", new SubscribeCommand(new OrderService()));
        addCommand("/select_lang", new SelectLanguageCommand());
    }
    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }
}
