package ua.training.controller.command.role;

import ua.training.controller.command.Command;
import ua.training.controller.command.DefaultCommand;
import ua.training.controller.command.LogoutCommand;
import ua.training.model.service.PeriodicalService;

import java.util.HashMap;

public class AdminCommands extends CommandHolder {

    public AdminCommands() {
        initCommands();
    }

    @Override
    public void initCommands() {
        commands = new HashMap<>();

        addCommand("default_command", new DefaultCommand(new PeriodicalService()));
        addCommand("logout_command", new LogoutCommand());
    }

    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }
}
