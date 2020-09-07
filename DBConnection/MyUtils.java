package DBConnection;

import Entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyUtils extends DBConnection {
    //hall
    public static boolean deleteHall(int id) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Hall WHERE hall_id=?");
        preparedStatement.setInt(1, id);
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;

    }

    public static List<Hall> getAllHall() throws SQLException {
        List<Hall> list = new ArrayList<Hall>();
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Hall");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Hall hall = new Hall();
            hall.setHallId(resultSet.getInt("hall_id"));
            hall.setHallName(resultSet.getString("hall_name"));
            list.add(hall);
        }

        return list;

    }

    public static boolean insertHall(Hall hall) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Hall(hall_name) VALUES ( ?)");
        preparedStatement.setString(1, hall.getHallName());
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;

    }

    public static Hall selectHall(int id) throws SQLException {
        Hall hall = null;
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT hall_id, hall_name FROM Hall WHERE hall_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            hall = new Hall();
            hall.setHallId(resultSet.getInt("hall_id"));
            hall.setHallName(resultSet.getString("hall_name"));
        }

        if (connection != null) {
            connection.close();
        }
        return hall;
    }

    public static boolean updateHall(Hall hall) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Hall SET hall_name = ? WHERE hall_id = ?;");
        preparedStatement.setString(1, hall.getHallName());
        preparedStatement.setInt(2, hall.getHallId());
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;

    }

    //client
    public static List<Client> searchClient(String input) throws SQLException {
        List<Client> list = new ArrayList<Client>();
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT cl.client_id,cl.client_fio,cl.phone,c.coach_fio FROM Client as cl INNER JOIN Coach as c On cl.coach_id_fk = c.coach_id WHERE cl.client_fio LIKE ? or cl.phone LIKE ?");
        preparedStatement.setString(1, "%" + input + "%");
        preparedStatement.setString(2, "%" + input + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Client client = new Client();
            client.setClientId(resultSet.getInt("client_id"));
            client.setClientFio(resultSet.getString("client_fio"));
            client.setPhone(resultSet.getString("phone"));
            client.setCoachName(resultSet.getString("coach_fio"));
            list.add(client);
        }
        return list;
    }

    public static boolean deleteClient(int id) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Client WHERE client_id=?");
        preparedStatement.setInt(1, id);
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;

    }

    public static List<Client> getAllClient() throws SQLException {
        List<Client> list = new ArrayList<Client>();
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Select cl.client_id,cl.client_fio,cl.phone,c.coach_fio\n" +
                "From Client as cl Inner Join Coach as c \n" +
                "On cl.coach_id_fk = c.coach_id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Client client = new Client();
            client.setClientId(resultSet.getInt("client_id"));
            client.setClientFio(resultSet.getString("client_fio"));
            client.setPhone(resultSet.getString("phone"));
            client.setCoachName(resultSet.getString("coach_fio"));
            list.add(client);
        }
        return list;
    }

    public static boolean insertClient(Client client) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Client(client_fio,phone,coach_id_fk) VALUES (?,?,?)");
        preparedStatement.setString(1, client.getClientFio());
        preparedStatement.setString(2, client.getPhone());
        preparedStatement.setInt(3, client.getCoachIdFk());
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;

    }

    public static Client selectClient(int id) throws SQLException {
        Client client = null;
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT client_id,client_fio,phone,coach_id_fk FROM Client WHERE client_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            client = new Client();
            client.setClientId(resultSet.getInt("client_id"));
            client.setClientFio(resultSet.getString("client_fio"));
            client.setPhone(resultSet.getString("phone"));
            client.setCoachIdFk(resultSet.getInt("coach_id_fk"));
        }

        if (connection != null) {
            connection.close();
        }
        return client;
    }

    public static boolean updateClient(Client client) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Client SET client_fio=?,phone=?,coach_id_fk=? WHERE client_id=?;");
        preparedStatement.setString(1, client.getClientFio());
        preparedStatement.setString(2, client.getPhone());
        preparedStatement.setInt(3, client.getCoachIdFk());
        preparedStatement.setInt(4, client.getClientId());
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;

    }

    //coach
    public static List<Coach> getAllCoach() throws SQLException {
        List<Coach> list = new ArrayList<Coach>();
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Coach");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Coach coach = new Coach();
            coach.setCoachId(resultSet.getInt("coach_id"));
            coach.setCoachFIO(resultSet.getString("coach_fio"));
            coach.setSalary(resultSet.getInt("salary"));
            list.add(coach);
        }
        return list;
    }

    public static Coach selectCoach(int id) throws SQLException {
        Coach coach = null;
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT coach_id,coach_fio,salary FROM Coach WHERE coach_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            coach = new Coach();
            coach.setCoachId(resultSet.getInt("coach_id"));
            coach.setCoachFIO(resultSet.getString("coach_fio"));
            coach.setSalary(resultSet.getInt("salary"));
        }

        if (connection != null) {
            connection.close();
        }
        return coach;
    }

    public static boolean insertCoach(Coach coach) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Coach(coach_fio,salary) VALUES (?,?)");
        preparedStatement.setString(1, coach.getCoachFIO());
        preparedStatement.setInt(2, coach.getSalary());
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;
    }

    public static boolean updateCoach(Coach coach) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Coach SET coach_fio=?,salary=? WHERE coach_id=?");
        preparedStatement.setString(1, coach.getCoachFIO());
        preparedStatement.setInt(2, coach.getSalary());
        preparedStatement.setInt(3, coach.getCoachId());
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;

    }

    public static boolean deleteCoach(int id) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Coach WHERE coach_id=?");
        preparedStatement.setInt(1, id);
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;

    }

    public static List<Coach> searchCoach(String input) throws SQLException {
        List<Coach> list = new ArrayList<Coach>();
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT coach_id,coach_fio,salary FROM Coach WHERE coach_fio LIKE ?");
        preparedStatement.setString(1, "%" + input + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Coach coach = new Coach();
            coach.setCoachId(resultSet.getInt("coach_id"));
            coach.setCoachFIO(resultSet.getString("coach_fio"));
            coach.setSalary(resultSet.getInt("salary"));
            list.add(coach);
        }
        return list;
    }

    //subscription

    public static boolean deleteSubscription(int id) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Subscription WHERE subscription_id=?");
        preparedStatement.setInt(1, id);
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;

    }

    public static List<Subscription> getAllSubscriptionDesc() throws SQLException {
        List<Subscription> list = new ArrayList<Subscription>();
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Select sub.subscription_id,sub.price,sub.description,h.hall_name\n" +
                "From Subscription as sub Inner Join Hall as h \n" +
                "On sub.hall_id_fk = h.hall_id ORDER BY sub.price DESC");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Subscription subscription = new Subscription();
            subscription.setSubscriptionId(resultSet.getInt("subscription_id"));
            subscription.setPrice(resultSet.getInt("price"));
            subscription.setDescription(resultSet.getString("description"));
            subscription.setHallName(resultSet.getString("hall_name"));
            list.add(subscription);
        }
        return list;
    }

    public static List<Subscription> getAllSubscriptionAsc() throws SQLException {
        List<Subscription> list = new ArrayList<Subscription>();
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Select sub.subscription_id,sub.price,sub.description,h.hall_name From Subscription as sub Inner Join Hall as h On sub.hall_id_fk = h.hall_id ORDER BY sub.price Asc");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Subscription subscription = new Subscription();
            subscription.setSubscriptionId(resultSet.getInt("subscription_id"));
            subscription.setPrice(resultSet.getInt("price"));
            subscription.setDescription(resultSet.getString("description"));
            subscription.setHallName(resultSet.getString("hall_name"));
            list.add(subscription);
        }
        return list;
    }

    public static List<Subscription> getAllSubscription() throws SQLException {
        List<Subscription> list = new ArrayList<Subscription>();
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Select sub.subscription_id,sub.price,sub.description,h.hall_name\n" +
                "From Subscription as sub Inner Join Hall as h \n" +
                "On sub.hall_id_fk = h.hall_id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Subscription subscription = new Subscription();
            subscription.setSubscriptionId(resultSet.getInt("subscription_id"));
            subscription.setPrice(resultSet.getInt("price"));
            subscription.setDescription(resultSet.getString("description"));
            subscription.setHallName(resultSet.getString("hall_name"));
            list.add(subscription);
        }
        return list;
    }

    public static boolean insertSubscription(Subscription subscription) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Subscription(price,description,hall_id_fk) VALUES (?,?,?)");
        preparedStatement.setInt(1, subscription.getPrice());
        preparedStatement.setString(2, subscription.getDescription());
        preparedStatement.setInt(3, subscription.getHallIdFk());
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;

    }

    public static Subscription selectSubscription(int id) throws SQLException {
        Subscription subscription = null;
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT subscription_id,price,description,hall_id_fk FROM Subscription WHERE subscription_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            subscription = new Subscription();
            subscription.setSubscriptionId(resultSet.getInt("subscription_id"));
            subscription.setPrice(resultSet.getInt("price"));
            subscription.setDescription(resultSet.getString("description"));
            subscription.setHallIdFk(resultSet.getInt("hall_id_fk"));
        }
        if (connection != null) {
            connection.close();
        }
        return subscription;
    }

    public static boolean updateSubscription(Subscription subscription) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Subscription SET price=?,description=?,hall_id_fk=? WHERE subscription_id=?");
        preparedStatement.setInt(1, subscription.getPrice());
        preparedStatement.setString(2, subscription.getDescription());
        preparedStatement.setInt(3, subscription.getHallIdFk());
        preparedStatement.setInt(4, subscription.getSubscriptionId());
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;

    }

    //accounting

    public static boolean deleteAccounting(int id1, int id2, String month) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Accounting as ac WHERE ac.subscription_id_fk=? AND ac.client_id_fk=? AND ac.month=?");
        preparedStatement.setInt(1, id1);
        preparedStatement.setInt(2, id2);
        preparedStatement.setString(3, month);
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;
    }

    public static boolean insertAccounting(Accounting accounting) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Accounting (subscription_id_fk, month, payment_made, client_id_fk) VALUES (?,?,?,?)");
        preparedStatement.setInt(1, accounting.getSubscriptionIdFk());
        preparedStatement.setString(2, accounting.getMonth());
        preparedStatement.setString(3, accounting.getPaymentMade());
        preparedStatement.setInt(4, accounting.getClientIdFk());
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;
    }

    public static List<Accounting> getAllAccounting() throws SQLException {
        List<Accounting> list = new ArrayList<Accounting>();
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Select sub.subscription_id,sub.price, ac.month, ac.payment_made,cl.client_fio,cl.client_id\n" +
                "From Subscription as sub inner JOIN Accounting as ac On sub.subscription_id=ac.subscription_id_fk inner join Client as cl on ac.client_id_fk=cl.client_id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Accounting accounting = new Accounting();
            accounting.setSubscriptionIdFk(resultSet.getInt("subscription_id"));
            accounting.setSubscription(resultSet.getInt("price"));
            accounting.setMonth(resultSet.getString("month"));
            accounting.setPaymentMade(resultSet.getString("payment_made"));
            accounting.setNameClient(resultSet.getString("client_fio"));
            accounting.setClientIdFk(resultSet.getInt("client_id"));
            list.add(accounting);
        }
        return list;
    }

    public static Accounting selectAccounting(int id1, int id2, String month) throws SQLException {
        Accounting accounting = null;
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(" Select sub.subscription_id,sub.price, ac.month, ac.payment_made, cl.client_fio,cl.client_id\n" +
                "From Subscription as sub inner JOIN Accounting as ac On sub.subscription_id=ac.subscription_id_fk inner join Client as cl on ac.client_id_fk=cl.client_id\n" +
                "Where  ac.subscription_id_fk=? AND ac.client_id_fk=? AND ac.month=?");
        preparedStatement.setInt(1, id1);
        preparedStatement.setInt(2, id2);
        preparedStatement.setString(3, month);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            accounting = new Accounting();
            accounting.setSubscriptionIdFk(resultSet.getInt("subscription_id"));
            accounting.setSubscription(resultSet.getInt("price"));
            accounting.setMonth(resultSet.getString("month"));
            accounting.setPaymentMade(resultSet.getString("payment_made"));
            accounting.setNameClient(resultSet.getString("client_fio"));
            accounting.setClientIdFk(resultSet.getInt("client_id"));
        }
        if (connection != null) {
            connection.close();
        }
        return accounting;
    }

    public static boolean updateAccounting(Accounting accounting, int id1, int id2, String month) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Accounting SET subscription_id_fk=?,month=?,payment_made=?,client_id_fk=? WHERE subscription_id_fk=? AND month=? AND client_id_fk=?");
        preparedStatement.setInt(1, accounting.getSubscriptionIdFk());
        preparedStatement.setString(2, accounting.getMonth());
        preparedStatement.setString(3, accounting.getPaymentMade());
        preparedStatement.setInt(4, accounting.getClientIdFk());
        preparedStatement.setInt(5, id1);
        preparedStatement.setString(6, month);
        preparedStatement.setInt(7, id2);
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        connection.close();
        return rowInserted;
    }
}

