package Servlets;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



import DBConnection.MyUtils;
import Entity.Client;
import Entity.Hall;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/hall/*"})
public class HallServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String input = request.getParameter("search");
        if(input!=null) {
            List<Client> listClient = null;
            try {
                listClient = MyUtils.searchClient(input);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("listClient", listClient);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Client.jsp");
            dispatcher.forward(request, response);
        }
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/update-hall": {
                    this.updateHall(request, response);
                }
                break;

                case "/insert-hall": {
                    this.insertHall(request, response);
                }
                break;

            }
        } catch (Throwable ex) {
            throw new ServletException(ex);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, UnsupportedEncodingException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getPathInfo();
              try {
            switch(action) {
               case "/new-hall":
                    {
                        this.showNewForm(request, response);
                    }
                    break;
                case "/edit-hall":
                   {
                        this.showEditForm(request, response);

                    }
                    break;
                case "/delete-hall":
                    {
                        this.deleteHall(request, response);
                    }
                    break;
                default:
                {
                    this.listHall(request, response);
                }
                break;
            }
        } catch (Throwable ex) {
            throw new ServletException(ex);
        }

    }

    private void listHall(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Hall> listHall = MyUtils.getAllHall();
        request.setAttribute("listHall", listHall);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Hall.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userFormHall.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("hallId"));
        Hall hall = MyUtils.selectHall(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userFormHall.jsp");
        request.setAttribute("hall", hall);
        dispatcher.forward(request, response);
    }

    private void insertHall(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String name = request.getParameter("hallName");
        Hall newHall = new Hall(name);
        MyUtils.insertHall(newHall);
        response.sendRedirect("list-hall");
    }

    private void updateHall(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int id = Integer.parseInt(request.getParameter("hallId"));
        String name = request.getParameter("hallName");
        Hall  hall = new Hall (id, name);
        MyUtils.updateHall(hall);
        response.sendRedirect("list-hall");
    }

    private void deleteHall(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int id = Integer.parseInt(request.getParameter("hallId"));
       MyUtils.deleteHall(id);
        response.sendRedirect("list-hall");
    }
}
