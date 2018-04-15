package database.RelationalDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cs240.lib.Model.Game;
import cs240.lib.Model.LobbyGame;
import cs240.lib.Model.User;
import cs240.lib.common.Command;
import cs240.lib.server.IDatabase;

/**
 * Created by David on 4/10/2018.
 */

public class RelationalDatabase implements IDatabase {
    private RelationalGameDao gameDao;
    private RelationalLobbyGameDao lobbyGameDao;
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
        openConnection();
        gameDao = new RelationalGameDao(connection);
        lobbyGameDao = new RelationalLobbyGameDao(connection);
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
    public boolean create(User user) {
        openConnection();
        boolean result = userDao.create();
        closeConnection(true);
        return result;
    }
    public boolean create(Game game) {
        openConnection();
        boolean result = gameDao.create();
        closeConnection(true);
        return result;
    }
    public boolean create(Command command) {
        openConnection();
        boolean result = commandDao.create();
        closeConnection(true);
        return result;
    }


    public boolean create(LobbyGame lobbyGame) {

        openConnection();
        boolean result = lobbyGameDao.create();
        closeConnection(true);
        return result;
    }

    public boolean insert(User user) {
        openConnection();
        boolean result = userDao.insert(user);
        closeConnection(true);
        return result;
    }
    public boolean insert(Game game) {
        openConnection();
        boolean result = gameDao.insert(game);
        closeConnection(true);
        return result;
    }
    public boolean insert(Command command) {
        openConnection();
        boolean result = commandDao.insert(command);
        closeConnection(true);
        return result;
    }


    public boolean insert(LobbyGame lobbyGame) {

        openConnection();
        boolean result = lobbyGameDao.insert(lobbyGame);
        closeConnection(true);
        return result;
    }

    public Object read(Object object, String toRead) {
        if(object.getClass().equals(Game.class)){
            openConnection();
            Object result = gameDao.read(toRead);
            closeConnection(true);
            return result;
        }else if(object.getClass().equals(Command.class)){
            openConnection();
            Object result = commandDao.read(toRead);
            closeConnection(true);
            return result;
        }else if (object.getClass().equals(User.class)){
            openConnection();
            Object result = userDao.read(toRead);
            closeConnection(true);
            return result;
        }else if(object.getClass().equals(LobbyGame.class)){
            openConnection();
            Object result = lobbyGameDao.read(toRead);
            closeConnection(true);
            return result;
        }else{
            return null;
        }
    }

    public Object[] readAll(User user) {
        openConnection();
        Object[] result = userDao.readAll();
        closeConnection(true);
        return result;
    }
    public Object[] readAll(Game game) {
        openConnection();
        Object[] result = gameDao.readAll();
        closeConnection(true);
        return result;
    }
    public Object[] readAll(Command command) {
        openConnection();
        Object[] result = commandDao.readAll();
        closeConnection(true);
        return result;
    }


    public Object[] readAll(LobbyGame lobbyGame) {
        openConnection();
        Object[] result = lobbyGameDao.readAll();
        closeConnection(true);
        return result;
    }

    public boolean update(User user) {
        openConnection();
        boolean result = userDao.update(user);
        closeConnection(true);
        return result;
    }
    public boolean update(Game game) {
        openConnection();
        boolean result = gameDao.update(game);
        closeConnection(true);
        return result;
    }
    public boolean update(Command command) {
        openConnection();
        boolean result = commandDao.update(command);
        closeConnection(true);
        return result;
    }


    public boolean update(LobbyGame lobbyGame) {
        openConnection();
        boolean result = lobbyGameDao.update(lobbyGame);
        closeConnection(true);
        return result;
    }

    public boolean delete(User user) {
        openConnection();
        boolean result = userDao.delete(user);
        closeConnection(true);
        return result;
    }
    public boolean delete(Game game) {
        openConnection();
        boolean result = gameDao.delete(game);
        closeConnection(true);
        return result;
    }
    public boolean delete(Command command) {
        openConnection();
        boolean result = commandDao.delete(command);
        closeConnection(true);
        return result;
    }


    public boolean delete(LobbyGame lobbyGame) {
        openConnection();
        boolean result = lobbyGameDao.delete(lobbyGame);
        closeConnection(true);
        return result;
    }


    public boolean clearCommandByGame(String gameName) {
        openConnection();
        boolean result = commandDao.clearbyGame(gameName);
        closeConnection(true);
        return result;
    }

    public void clear(User user) {
        openConnection();
        userDao.clear();
        closeConnection(true);
    }
    public void clear(Game game) {
        openConnection();
        gameDao.clear();
        closeConnection(true);
    }
    public void clear(Command command) {
        openConnection();
        commandDao.clear();
        closeConnection(true);
    }

    public void clear(LobbyGame lobbyGame) {
        openConnection();
        lobbyGameDao.clear();
        closeConnection(true);
    }

    public void clearAll() {
        openConnection();
        userDao.clear();
        gameDao.clear();
        lobbyGameDao.clear();
        commandDao.clear();
        closeConnection(true);
    }

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
