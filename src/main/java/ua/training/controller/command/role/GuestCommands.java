package ua.training.controller.command.role;

import ua.training.controller.command.Command;
import ua.training.controller.command.DefaultCommand;
import ua.training.controller.command.LoginCommand;
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
    }
    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }
}
