package Servlets;

import DBConnection.MyUtils;
import Entity.Accounting;
import Entity.Client;
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
import java.util.List;

@WebServlet(urlPatterns = "/accounting/*")
public class AccountingServlet extends HttpServlet {
    Accounting accounting1=new Accounting();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/update-accounting": {
                    this.updateAccounting(request, response);
                }
                break;

                case "/insert-accounting": {
                    this.insertAccounting(request, response);
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
                case "/new-accounting":
                {
                    this.showNewForm(request, response);
                }
                break;
                case "/edit-accounting":
                {
                    this.showEditForm(request, response);
                }
                break;
                case "/delete-accounting":
                {
                    this.deleteAccounting(request, response);
                }
                break;
                default:
                {
                    this.listAccounting(request, response);
                }
                break;
            }
        } catch (Throwable ex) {
            throw new ServletException(ex);
        }
    }

    private void listAccounting(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Accounting> listAccounting = MyUtils.getAllAccounting();
        request.setAttribute("listAccounting", listAccounting);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Accounting.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Subscription> subscriptionList= MyUtils.getAllSubscription();
        request.setAttribute("subscriptionList", subscriptionList);
        List<Client> clientList=MyUtils.getAllClient();
        request.setAttribute("clientList",clientList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userFormAccounting.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id1 = Integer.parseInt(request.getParameter("subId"));
        String month = request.getParameter("month");
        int id2 = Integer.parseInt(request.getParameter("clId"));
         accounting1 = MyUtils.selectAccounting(id1,id2,month);
        int i = accounting1.getSubscriptionIdFk();
        Subscription subscription=MyUtils.selectSubscription(i);
        int subPrice=subscription.getPrice();
        int subId=subscription.getSubscriptionId();
        request.setAttribute("subPrice",subPrice);
        request.setAttribute("subId",subId);
        List<Subscription> subscriptionList= MyUtils.getAllSubscription();
        request.setAttribute("accounting", accounting1);
        request.setAttribute("subscriptionList", subscriptionList);
        int j=accounting1.getClientIdFk();
        Client client=MyUtils.selectClient(j);
        String clientFio=client.getClientFio();
        int clId=client.getClientId();
        request.setAttribute("clientFio",clientFio);
        request.setAttribute("clId",clId);
        List<Client> clientList=MyUtils.getAllClient();
        request.setAttribute("clientList",clientList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/userFormAccounting.jsp");
        dispatcher.forward(request, response);
    }

    private void insertAccounting(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int id1 = Integer.parseInt(request.getParameter("subId1"));
        String month = request.getParameter("month1");
        String paymentMade = request.getParameter("paymentMade");
        int id2 = Integer.parseInt(request.getParameter("clId1"));
        Accounting accountingNew= new Accounting(id1,month,paymentMade,id2);
        MyUtils.insertAccounting(accountingNew);
        response.sendRedirect("list-accounting");
    }

    private void updateAccounting(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int id1 = Integer.parseInt(request.getParameter("subId1"));
        String month = request.getParameter("month1");
        String paymentMade = request.getParameter("paymentMade");
        int id2 = Integer.parseInt(request.getParameter("clId1"));
        int id11 = accounting1.getSubscriptionIdFk();
        String month1 = accounting1.getMonth();
        int id22 = accounting1.getClientIdFk();
       Accounting  accounting = new Accounting (id1,month,paymentMade,id2);
        MyUtils.updateAccounting(accounting, id11, id22, month1);
        response.sendRedirect("list-accounting");
    }

    private void deleteAccounting(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        int id1 = Integer.parseInt(request.getParameter("subId"));
        String month = request.getParameter("month");
        int id2 = Integer.parseInt(request.getParameter("clId"));
        MyUtils.deleteAccounting(id1,id2,month);
        response.sendRedirect("list-accounting");
    }
}
