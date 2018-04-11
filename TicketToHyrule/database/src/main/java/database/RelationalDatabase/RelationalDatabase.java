package database.RelationalDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cs240.lib.Model.Game;
import cs240.lib.Model.User;
import cs240.lib.common.Command;
import cs240.lib.server.IDatabase;

/**
 * Created by David on 4/10/2018.
 */

public class RelationalDatabase implements IDatabase {
    private RelationalGameDao gameDao;
    private RelationalCommandDao commandDao;
    private RelationalUserDao userDao;

    final String URL = "jdbc:sqlite:TTH.db"; //TODO: make sure this is the right file
    private Connection connection = null;

    static{
        try{
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public RelationalDatabase(){
        gameDao = new RelationalGameDao(connection);
        commandDao = new RelationalCommandDao(connection);
        userDao = new RelationalUserDao(connection);
    }

    public void openConnection() {
        try{
            connection = DriverManager.getConnection(URL);
            connection.setAutoCommit(false);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(boolean commit){
        try{
            if (commit){
                connection.commit();
            }else {
                connection.rollback();
            }
            connection.close();
            connection = null;
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*@Override
    public boolean create(Object object) {
        if(object.getClass().equals(Game.class)){
            return gameDao.create();
        }else if(object.getClass().equals(Command.class)){
            return commandDao.create();
        }else if (object.getClass().equals(User.class)){
            return userDao.create();
        }else{
            return false;
        }
    }

    @Override
    public boolean insert(Object object) {
        if(object.getClass().equals(Game.class)){
            Game game = (Game)object;
            return gameDao.insert(game);
        }else if(object.getClass().equals(Command.class)){
            Command command = (Command)object;
            return commandDao.insert(command);
        }else if (object.getClass().equals(User.class)){
            User user = (User)object;
            return userDao.insert(user);
        }else{
            return false;
        }
    }*/
    public boolean create(User user) {return userDao.create();}
    public boolean create(Game game) {return gameDao.create();}
    public boolean create(Command command) {return commandDao.create();}
    public boolean insert(User user) {return userDao.insert(user);}
    public boolean insert(Game game) {return gameDao.insert(game);}
    public boolean insert(Command command) {return commandDao.insert(command);}

    public Object read(Object object, String toRead) {
        if(object.getClass().equals(Game.class)){
            return gameDao.read(toRead);
        }else if(object.getClass().equals(Command.class)){
            return commandDao.read(toRead);
        }else if (object.getClass().equals(User.class)){
            return userDao.read(toRead);
        }else{
            return null;
        }
    }

    public Object[] readAll(User user) {return userDao.readAll();}
    public Object[] readAll(Game game) {return gameDao.readAll();}
    public Object[] readAll(Command command) {return commandDao.readAll();}
    public boolean update(User user) {return userDao.update(user);}
    public boolean update(Game game) {return gameDao.update(game);}
    public boolean update(Command command) {return commandDao.update(command);}
    public boolean delete(User user) {return userDao.delete(user);}
    public boolean delete(Game game) {return gameDao.delete(game);}
    public boolean delete(Command command) {return commandDao.delete(command);}
    public void clear(User user) {userDao.clear();}
    public void clear(Game game) {gameDao.clear();}
    public void clear(Command command) {commandDao.clear();}

    /*@Override
    public Object[] readAll(Object object) {
        if(object.getClass().equals(Game.class)){
            return gameDao.readAll();
        }else if(object.getClass().equals(Command.class)){
            return commandDao.readAll();
        }else if (object.getClass().equals(User.class)){
            return userDao.readAll();
        }else{
            return null;
        }
    }

    @Override
    public boolean update(Object object) {
        if(object.getClass().equals(Game.class)){
            Game game = (Game)object;
            return gameDao.update(game);
        }else if(object.getClass().equals(Command.class)){
            Command command = (Command)object;
            return commandDao.update(command);
        }else if (object.getClass().equals(User.class)){
            User user = (User)object;
            return userDao.update(user);
        }else{
            return false;
        }
    }

    @Override
    public boolean delete(Object object) {
        if(object.getClass().equals(Game.class)){
            Game game = (Game)object;
            return gameDao.delete(game);
        }else if(object.getClass().equals(Command.class)){
            Command command = (Command)object;
            return commandDao.delete(command);
        }else if (object.getClass().equals(User.class)){
            User user = (User)object;
            return userDao.delete(user);
        }else{
            return false;
        }
    }

    @Override
    public void clear(Object object) {
        if(object.getClass().equals(Game.class)){
            Game game = (Game)object;
            gameDao.clear();
        }else if(object.getClass().equals(Command.class)){
            Command command = (Command)object;
            commandDao.clear();
        }else if (object.getClass().equals(User.class)){
            userDao.clear();
        }
    }*/

}
