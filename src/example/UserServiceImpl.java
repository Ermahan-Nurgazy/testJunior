package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.sql.*;
import java.util.*;

@WebService(endpointInterface = "example.UserService")
public class UserServiceImpl implements UserService {

    private static Map<Integer,User> users;

    public UserServiceImpl(){
        if (users==null){
            users = new HashMap<Integer, User>();
        }
    }

    Connection conn;

    {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/User", "postgres", "Alma1212");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @WebMethod
    public boolean addUser(User u) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO users(id,username,lastname,iin,login,password) " +
                    "VALUES (?,?,?,?,?,?)");
            preparedStatement.setInt(1,u.getId());
            preparedStatement.setString(2,u.getUserName());
            preparedStatement.setString(3,u.getLastName());
            preparedStatement.setInt(4,u.getIin());
            preparedStatement.setString(5,u.getLogin());
            preparedStatement.setString(6,u.getPassword());
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(u,System.out);
            marshaller.marshal(u, new File("src\\data\\User.xml"));
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows>0;
    }

    @WebMethod
    public boolean deleteUser(int id) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("" +
                    "DELETE FROM users " +
                    "WHERE id = ? ");
            preparedStatement.setInt(1,id);
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    @WebMethod
    public User getUser(int id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("" +
                    "SELECT id, username, lastname, iin, login, password " +
                    "FROM users WHERE id = ? ");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("iin"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(user,System.out);
            marshaller.marshal(user, new File("src\\data\\User.xml"));
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @WebMethod
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("" +
                    "SELECT id, username, lastname, iin, login, password " +
                    "FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.add(new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("iin"),
                        resultSet.getString("login"),
                        resultSet.getString("password")));
            }
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
}

