package ua.training.controller.command.role;

import ua.training.controller.command.Command;
import ua.training.controller.command.DefaultCommand;
import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AdminCommands extends CommandHolder {

    public AdminCommands() {
        initCommands();
    }

    @Override
    public void initCommands() {
        commands = new HashMap<>();

        addCommand("default_command", new DefaultCommand(new PeriodicalService()));
    }

    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }
}
