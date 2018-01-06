package ua.training.controller.command.role;

import ua.training.controller.command.*;
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

        addCommand("default_command", new DefaultCommand(new PeriodicalService()));
        addCommand("login_command", new LoginCommand(new UserService()));
        addCommand("register_command", new RegisterCommand(new UserService()));
        addCommand("add_to_basket_command", new AddToBasketCommand(new PeriodicalService()));
        addCommand("delete_from_basket", new DeleteFromBasket(new PeriodicalService()));
    }
    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }
}
