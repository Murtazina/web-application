package Servlets;

import DBConnection.MyUtils;
import Entity.Client;
import Entity.Coach;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

@WebServlet(urlPatterns = "/coach/*")
public class CoachServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String input = request.getParameter("search");
        if (input != null) {
            List<Coach> listCoach = null;
            try {
                listCoach = MyUtils.searchCoach(input);
            } catch (SQLException e) {

                e.printStackTrace();
            }
            request.setAttribute("listCoach", listCoach);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Coach.jsp");
            dispatcher.forward(request, response);
        }
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/update-coach": {
                    this.updateCoach(request, response);
                }
                break;

                case "/insert-coach": {
                    this.insertCoach(request, response);

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
            switch (action) {
                case "/edit-coach": {
                    this.showEditForm(request, response);
                }
                break;
                case "/delete-coach": {
                    this.deleteCoach(request, response);
                }
                break;
                case "/new-coach": {
                    this.showNewForm(request, response);
                }
                break;

                default: {
                    this.listCoach(request, response);
                }
                break;
            }
        } catch (Throwable ex) {
            throw new ServletException(ex);
        }
    }

    private void listCoach(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Coach> listCoach = MyUtils.getAllCoach();
        request.setAttribute("listCoach", listCoach);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Coach.jsp");
        dispatcher.forward(request, response);
    }


    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userFormCoach.jsp");
        List<Coach> listCoach = MyUtils.getAllCoach();
        request.setAttribute("listCoach", listCoach);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userFormCoach.jsp");
        int coachId = Integer.parseInt(request.getParameter("coachId"));
        Coach coach = MyUtils.selectCoach(coachId);
        request.setAttribute("coach", coach);
        dispatcher.forward(request, response);
    }

    private void insertCoach(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String coachFioName = request.getParameter("coachFio");
        int coachSalary = Integer.parseInt(request.getParameter("coachSalary"));
        Coach newCoach = new Coach(coachFioName, coachSalary);
        MyUtils.insertCoach(newCoach);
        response.sendRedirect("list-coach");
    }

    private void updateCoach(HttpServletRequest request, HttpServletResponse response) throws Throwable {

        int coachId = Integer.parseInt(request.getParameter("coachId"));
        String coachFio = request.getParameter("coachFio");
        int coachSalary = Integer.parseInt(request.getParameter("coachSalary"));
        Coach coach = new Coach(coachId, coachFio, coachSalary);
        MyUtils.updateCoach(coach);
        response.sendRedirect("list-coach");
    }

    private void deleteCoach(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int coachId = Integer.parseInt(request.getParameter("coachId"));
        MyUtils.deleteCoach(coachId);
        response.sendRedirect("list-coach");
    }
}
