package database.RelationalDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.management.relation.Relation;

import cs240.lib.Model.User;

/**
 * Created by David on 4/7/2018.
 */

public class RelationalUserDao {

    private Connection connection;

    public RelationalUserDao(Connection connection){
        this.connection = connection;
    }
    public RelationalUserDao() {connection = null;}

    public boolean create() {
        boolean created = createUser();
        return created;
    }

    public void setConnection(Connection connection) {this.connection = connection;}

    private boolean createUser() {
        Statement statement = null;
        try{
            statement = connection.createStatement();
            String sql = "create table if not exists Users " +
                    "(" +
                    "Username text not null," +
                    "Password text not null" +
                    ")";

            statement.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            try{
                if (statement != null){
                    statement.close();
                    return true;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }


    public boolean insert(User user) {
        User toInsert = user;
        boolean inserted = insertUser(toInsert);
        return inserted;
    }

    private boolean insertUser(User toInsert) {
        PreparedStatement statement = null;
        try{
            String sql = "insert into Users (Username, Password) " +
                    "values (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, toInsert.getUsername());
            statement.setString(2, toInsert.getPassword());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally{
            try{
                if (statement != null){
                    statement.close();
                    return true;
                }
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }




    public Object read(String toRead) {
        User user = findUsername(toRead);
        return user;
    }

    private User findUsername(String toRead) {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from Users where Username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, toRead);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString(1);
                String password = resultSet.getString(2);
                user = new User(username, password);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if (resultSet != null){
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return user;
    }

    public Object[] readAll() {
        ArrayList<User> usersList = new ArrayList<>();
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from Users ";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString(1);
                String password = resultSet.getString(2);
                user = new User(username, password);
                usersList.add(user);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if (resultSet != null){
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        User[] users = new User[usersList.size()];
        for (int i = 0; i < usersList.size(); ++i){
            users[i] = usersList.get(i);
        }
        return users;
    }




    public boolean update(User user) {
        User toUpdate = user;
        boolean updated = updateUser(toUpdate);
        return updated;
    }

    private boolean updateUser(User toUpdate) {
        PreparedStatement statement = null;

        try {
            String sql = "update Users " +
                    "set Username = ?, Password = ? " +
                    "where Username = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, toUpdate.getUsername());
            statement.setString(2, toUpdate.getPassword());
            statement.setString(3, toUpdate.getUsername());

            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            try{
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

        }
        return true;
    }

    public boolean delete(User user) {
        User toDelete = user;
        boolean deleted = deleteUser(toDelete);
        return deleted;
    }

    private boolean deleteUser(User toDelete) {
        PreparedStatement statement = null;
        try{
            String sql = "delete from Users where Username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, toDelete.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public void clear() {
        Statement statement = null;
        try{
            statement = connection.createStatement();
            String sql = "delete from Users";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
