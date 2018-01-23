package ua.training.controller.command.role;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.dao.impl.JDBCUserArticleDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public abstract class CommandHolder {
    protected Map<String, Command> commands;
    private static final Logger logger = LogManager.getLogger(JDBCUserArticleDao.class);

    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) {
        String commandName = req.getRequestURI();

        Command command = commands.getOrDefault(commandName,
                (r, rsp) -> {throw new UnsupportedOperationException(commandName);});
        logger.info(command);
        return command.execute(req, resp);
    }

    Map<String, Command> getCommands() {
        return this.commands;
    }

    abstract void initCommands();

}
