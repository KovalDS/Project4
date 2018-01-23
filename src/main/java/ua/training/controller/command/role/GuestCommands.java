package ua.training.controller.command.role;

import ua.training.controller.command.*;
import ua.training.model.service.OrderService;
import ua.training.model.service.PeriodicalService;
import ua.training.model.service.UserService;
import ua.training.util.text.constants.Commands;

import java.util.HashMap;

public class GuestCommands extends CommandHolder {

    public GuestCommands() {
        initCommands();
    }

    @Override
    public void initCommands() {
        commands = new HashMap<>();

        addCommand(Commands.DEFAULT_COMMAND, new ShowPeriodicalsList(new PeriodicalService()));
        addCommand(Commands.HOME, new ShowPeriodicalsList(new PeriodicalService()));
        addCommand(Commands.LOGIN, new LoginCommand(new UserService()));
        addCommand(Commands.REGISTER, new RegisterCommand(new UserService()));
        addCommand(Commands.ADD_TO_BASKET, new AddToBasketCommand(new PeriodicalService()));
        addCommand(Commands.DELETE_FROM_BASKET, new DeleteFromBasket(new PeriodicalService()));
        addCommand(Commands.SUBSCRIBE, new SubscribeCommand(new OrderService()));
        addCommand(Commands.SELECT_LANGUAGE, new SelectLanguageCommand());
    }
    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }
}
