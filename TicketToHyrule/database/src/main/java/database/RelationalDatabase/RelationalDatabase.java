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
        //openConnection();
        gameDao = new RelationalGameDao();
        lobbyGameDao = new RelationalLobbyGameDao();
        commandDao = new RelationalCommandDao();
        userDao = new RelationalUserDao();
        create(new Game("creation"));
        create(new User("create", "create"));
        create(new LobbyGame(0,0,"create"));
        create(new Command("create", new String[1], new Object[1]));
    }

    public Connection openConnection() {
        try{
            connection = DriverManager.getConnection(URL);
            connection.setAutoCommit(false);
            return connection;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
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
        Connection connection = openConnection();
        userDao.setConnection(connection);
        boolean result = userDao.create();
        //userDao.setConnection(null);
        closeConnection(true);
        return result;
    }
    public boolean create(Game game) {
        Connection connection = openConnection();
        gameDao.setConnection(connection);
        boolean result = gameDao.create();
        //gameDao.setConnection(null);
        closeConnection(true);
        return result;
    }
    public boolean create(Command command) {
        Connection connection = openConnection();
        commandDao.setConnection(connection);
        boolean result = commandDao.create();
        //commandDao.setConnection(null);
        closeConnection(true);
        return result;
    }


    public boolean create(LobbyGame lobbyGame) {
        Connection connection = openConnection();
        lobbyGameDao.setConnection(connection);
        boolean result = lobbyGameDao.create();
        //lobbyGameDao.setConnection(null);
        closeConnection(true);
        return result;
    }

    public boolean insert(User user) {
        Connection connection = openConnection();
        userDao.setConnection(connection);
        boolean result = userDao.insert(user);
        //userDao.setConnection(null);
        closeConnection(true);
        return result;
    }
    public boolean insert(Game game) {
        Connection connection = openConnection();
        gameDao.setConnection(connection);
        boolean result = gameDao.insert(game);
        closeConnection(true);
        return result;
    }
    public boolean insert(Command command) {
        Connection connection = openConnection();
        commandDao.setConnection(connection);
        boolean result = commandDao.insert(command);
        closeConnection(true);
        return result;
    }


    public boolean insert(LobbyGame lobbyGame) {
        Connection connection = openConnection();
        lobbyGameDao.setConnection(connection);
        boolean result = lobbyGameDao.insert(lobbyGame);
        closeConnection(true);
        return result;
    }

    public Object read(Object object, String toRead) {
        if(object.getClass().equals(Game.class)){
            Connection connection = openConnection();
            gameDao.setConnection(connection);
            Object result = gameDao.read(toRead);
            closeConnection(true);
            return result;
        }else if(object.getClass().equals(Command.class)){
            Connection connection = openConnection();
            commandDao.setConnection(connection);
            Object result = commandDao.read(toRead);
            closeConnection(true);
            return result;
        }else if (object.getClass().equals(User.class)){
            Connection connection = openConnection();
            userDao.setConnection(connection);
            Object result = userDao.read(toRead);
            closeConnection(true);
            return result;
        }else if(object.getClass().equals(LobbyGame.class)){
            Connection connection = openConnection();
            lobbyGameDao.setConnection(connection);
            Object result = lobbyGameDao.read(toRead);
            closeConnection(true);
            return result;
        }else{
            return null;
        }
    }

    public Object[] readAll(User user) {
        Connection connection = openConnection();
        userDao.setConnection(connection);
        Object[] result = userDao.readAll();
        closeConnection(true);
        return result;
    }
    public Object[] readAll(Game game) {
        Connection connection = openConnection();
        gameDao.setConnection(connection);openConnection();
        Object[] result = gameDao.readAll();
        closeConnection(true);
        return result;
    }
    public Object[] readAll(Command command) {
        Connection connection = openConnection();
        commandDao.setConnection(connection);
        Object[] result = commandDao.readAll();
        closeConnection(true);
        return result;
    }


    public Object[] readAll(LobbyGame lobbyGame) {
        Connection connection = openConnection();
        lobbyGameDao.setConnection(connection);
        Object[] result = lobbyGameDao.readAll();
        closeConnection(true);
        return result;
    }

    public boolean update(User user) {
        Connection connection = openConnection();
        userDao.setConnection(connection);
        boolean result = userDao.update(user);
        closeConnection(true);
        return result;
    }
    public boolean update(Game game) {
        Connection connection = openConnection();
        gameDao.setConnection(connection);
        boolean result = gameDao.update(game);
        closeConnection(true);
        return result;
    }
    public boolean update(Command command) {
        Connection connection = openConnection();
        commandDao.setConnection(connection);
        boolean result = commandDao.update(command);
        closeConnection(true);
        return result;
    }


    public boolean update(LobbyGame lobbyGame) {
        Connection connection = openConnection();
        lobbyGameDao.setConnection(connection);
        boolean result = lobbyGameDao.update(lobbyGame);
        closeConnection(true);
        return result;
    }

    public boolean delete(User user) {
        Connection connection = openConnection();
        userDao.setConnection(connection);
        boolean result = userDao.delete(user);
        closeConnection(true);
        return result;
    }
    public boolean delete(Game game) {
        Connection connection = openConnection();
        gameDao.setConnection(connection);
        boolean result = gameDao.delete(game);
        closeConnection(true);
        return result;
    }
    public boolean delete(Command command) {
        Connection connection = openConnection();
        commandDao.setConnection(connection);
        boolean result = commandDao.delete(command);
        closeConnection(true);
        return result;
    }


    public boolean delete(LobbyGame lobbyGame) {
        Connection connection = openConnection();
        lobbyGameDao.setConnection(connection);
        boolean result = lobbyGameDao.delete(lobbyGame);
        closeConnection(true);
        return result;
    }


    public boolean clearCommandByGame(String gameName) {
        Connection connection = openConnection();
        commandDao.setConnection(connection);
        boolean result = commandDao.clearbyGame(gameName);
        closeConnection(true);
        return result;
    }

    public void clear(User user) {
        Connection connection = openConnection();
        userDao.setConnection(connection);
        userDao.clear();
        closeConnection(true);
    }
    public void clear(Game game) {
        Connection connection = openConnection();
        gameDao.setConnection(connection);
        gameDao.clear();
        closeConnection(true);
    }
    public void clear(Command command) {
        Connection connection = openConnection();
        commandDao.setConnection(connection);
        commandDao.clear();
        closeConnection(true);
    }

    public void clear(LobbyGame lobbyGame) {
        Connection connection = openConnection();
        lobbyGameDao.setConnection(connection);
        lobbyGameDao.clear();
        closeConnection(true);
    }

    public void clearAll() {
        clear(new User("user", "pass"));
        clear(new Game("clearing"));
        clear(new LobbyGame(1, 1, "clearing"));
        clear(new Command("clearing", new String[1], new Object[1]));
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
