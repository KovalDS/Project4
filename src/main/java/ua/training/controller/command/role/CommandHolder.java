package ua.training.controller.command.role;

import ua.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public abstract class CommandHolder {
    Map<String, Command> commands;

    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) {
        String commandName = req.getParameter("command");
        if (commandName == null) {
            return commands.get("default_command").execute(req, resp);
        }
        Command command = commands.getOrDefault(commandName,
                (r, rsp) -> {throw new UnsupportedOperationException(commandName);});
        return command.execute(req, resp);
    }

    Map<String, Command> getCommands() {
        return this.commands;
    }

    abstract void initCommands();

}
