package ua.training.controller;

import ua.training.controller.command.role.CommandHolder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String page;

        CommandHolder commands = (CommandHolder)httpServletRequest.getSession().getAttribute("available_commands");
        page = commands.executeCommand(httpServletRequest, httpServletResponse);

        httpServletRequest.getRequestDispatcher(page).forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String page;

        CommandHolder commands = (CommandHolder)httpServletRequest.getSession().getAttribute("available_commands");
        page = commands.executeCommand(httpServletRequest, httpServletResponse);

        httpServletRequest.getRequestDispatcher(page).forward(httpServletRequest, httpServletResponse);
    }
}
