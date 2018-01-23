package ua.training.controller;

import ua.training.controller.command.role.CommandHolder;
import ua.training.util.text.constants.Attributes;
import ua.training.util.text.constants.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        processRequest(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        processRequest(httpServletRequest, httpServletResponse);
    }

    private void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String page;

        try {
            CommandHolder commands = (CommandHolder) httpServletRequest.getSession().getAttribute(Attributes.AVAILABLE_COMMANDS);
            page = commands.executeCommand(httpServletRequest, httpServletResponse);
            httpServletRequest.getRequestDispatcher(page).forward(httpServletRequest, httpServletResponse);
        } catch (RuntimeException e) {
            httpServletRequest.getRequestDispatcher(Pages.ERROR_404).forward(httpServletRequest, httpServletResponse);
        }
    }
}
