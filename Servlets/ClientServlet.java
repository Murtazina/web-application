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

@WebServlet({"/client/*"})
public class ClientServlet extends HttpServlet {

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
            case "/update-client": {
                this.updateClient(request, response);
            }
            break;

            case "/insert-client": {
                this.insertClient(request, response);
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
                case "/edit-client": {
                    this.showEditForm(request, response);
                }
                break;
                case "/delete-client": {
                    this.deleteClient(request, response);
                }
                break;
                case "/new-client": {
                    this.showNewForm(request, response);
                }
                break;

                default: {
                    this.listClient(request, response);
                }
                break;
            }
        } catch (Throwable ex) {
            throw new ServletException(ex);
        }
    }

    private void listClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Client> listClient = MyUtils.getAllClient();
        List<Coach> coaches = new LinkedList<>();
        request.setAttribute("listClient", listClient);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Client.jsp");
        dispatcher.forward(request, response);}


    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userFormClient.jsp");
        List<Coach> listCoach = MyUtils.getAllCoach();
        request.setAttribute("listCoach", listCoach);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userFormClient.jsp");
        int clientId = Integer.parseInt(request.getParameter("clientId"));
        Client client = MyUtils.selectClient(clientId);
        int i=client.getCoachIdFk();
        Coach coach = new Coach();
        coach=MyUtils.selectCoach(i);
        int id=coach.getCoachId();
        String nameCoach = coach.getCoachFIO();
        request.setAttribute("client", client);
        request.setAttribute("id",id);
        request.setAttribute("nameCoach", nameCoach);
        List<Coach> listCoach = MyUtils.getAllCoach();
        for (ListIterator<Coach> iterator = listCoach.listIterator(); iterator.hasNext();) {
              Coach coach1 = iterator.next();
                if (coach1.getCoachId()==i) {
                    iterator.remove();
                }}

        request.setAttribute("listCoach", listCoach);
        dispatcher.forward(request, response);
    }

    private void insertClient(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String clientName = request.getParameter("clientName");
        String clientFio = request.getParameter("clientFio");
        String phone = request.getParameter("phone");
        int coachIdFk=Integer.parseInt( request.getParameter("coachId"));
        Client newClient = new Client(clientFio,phone,coachIdFk);
        MyUtils.insertClient(newClient);
        response.sendRedirect("list-client");
    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int clientId = Integer.parseInt(request.getParameter("clientId"));
        String clientFio = request.getParameter("clientFio");
        String phone = request.getParameter("phone");
        int coachIdFk =Integer.parseInt( request.getParameter("coachId"));
        Client client = new Client (clientId, clientFio,phone,coachIdFk);
        MyUtils.updateClient(client);
        response.sendRedirect("list-client");
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int clientId = Integer.parseInt(request.getParameter("clientId"));
        MyUtils.deleteClient(clientId);
        response.sendRedirect("list-client");
    }
}

