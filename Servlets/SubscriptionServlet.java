package Servlets;

import DBConnection.MyUtils;
import Entity.Client;
import Entity.Coach;
import Entity.Hall;
import Entity.Subscription;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

@WebServlet(urlPatterns = "/subscription/*")
public class SubscriptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/update-subscription": {
                    this.updateSubscription(request, response);
                }
                break;

                case "/insert-subscription": {
                    this.insertSubscription(request, response);
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
                case "/edit-subscription": {
                    this.showEditForm(request, response);
                }
                break;
                case "/delete-subscription": {
                    this.deleteSubscription(request, response);
                }
                break;
                case "/new-subscription": {
                    this.showNewForm(request, response);
                }
                break;
                case "/asc-subscription": {
                    this.ascSubscription(request, response);
                }
                break;
                case "/desc-subscription": {
                    this.descSubscription(request, response);
                }
                break;
                default: {
                    this.listSubscription(request, response);
                }
                break;
            }
        } catch (Throwable ex) {
            throw new ServletException(ex);
        }
    }

    private void listSubscription(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Subscription> listSubscription = MyUtils.getAllSubscription();
        request.setAttribute("listSubscription", listSubscription);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Subscription.jsp");
        dispatcher.forward(request, response);}

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userFormSubscription.jsp");
        List<Hall> listHall = MyUtils.getAllHall();
        request.setAttribute("listHall", listHall);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userFormSubscription.jsp");
        int subscriptionId = Integer.parseInt(request.getParameter("subscriptionId"));
        Subscription subscription = MyUtils.selectSubscription(subscriptionId);
        int i=subscription.getHallIdFk();
        Hall hall = MyUtils.selectHall(i);
        int id=hall.getHallId();
        String nameHall = hall.getHallName();
        request.setAttribute("subscription", subscription);
        request.setAttribute("id",id);
        request.setAttribute("nameHall", nameHall);
        List<Hall> listHall = MyUtils.getAllHall();
        for (ListIterator<Hall> iterator = listHall.listIterator(); iterator.hasNext();) {
           Hall hall1 = iterator.next();
          if (hall1.getHallId()==i) {
               iterator.remove();
          }}
        request.setAttribute("listHall", listHall);
        dispatcher.forward(request, response);
    }


    private void insertSubscription(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int price = Integer.parseInt(request.getParameter("price"));
        String description = request.getParameter("description");
        int hallId=Integer.parseInt( request.getParameter("hallId"));
        Subscription newSubscription = new Subscription(price,description,hallId);
        MyUtils.insertSubscription(newSubscription);
        response.sendRedirect("list-subscription");
    }

    private void updateSubscription(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int subscriptionId = Integer.parseInt(request.getParameter("subscriptionId"));
        int price = Integer.parseInt(request.getParameter("price"));
        String description = request.getParameter("description");
        int hallId=Integer.parseInt( request.getParameter("hallId"));
        Subscription subscription = new Subscription (subscriptionId,price,description,hallId);
        MyUtils.updateSubscription(subscription);
        response.sendRedirect("list-subscription");
    }

    private void deleteSubscription(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int subscriptionId = Integer.parseInt(request.getParameter("subscriptionId"));
        MyUtils.deleteSubscription(subscriptionId);
        response.sendRedirect("list-subscription");
    }
    private void ascSubscription(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        List<Subscription> listSubscription=MyUtils.getAllSubscriptionAsc();
        request.setAttribute("listSubscription",  listSubscription);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Subscription.jsp");
        dispatcher.forward(request, response);

        }
    private void descSubscription(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        List<Subscription> listSubscription=MyUtils.getAllSubscriptionDesc();
        request.setAttribute("listSubscription",  listSubscription);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Subscription.jsp");
        dispatcher.forward(request, response);

    }

    }

