package database.RelationalDatabase;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cs240.lib.Model.Game;

/**
 * Created by David on 4/7/2018.
 */

public class RelationalGameDao{
    private Connection connection;
    private Gson gson = new Gson();

    public RelationalGameDao(Connection connection){
        this.connection = connection;
    }
    public RelationalGameDao() {connection = null;}

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean create() {
        boolean created = createGame();
        return created;
    }

    private boolean createGame() {
        Statement statement = null;
        try{
            statement = connection.createStatement();
            String sql = "create table if not exists Games " +
                    "(" +
                    "Game text not null," +
                    "GameName text not null primary key" +
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

    public boolean insert(Game game) {
        Game toAdd = game;
        boolean inserted = insertGame(toAdd);
        return inserted;
    }

    private boolean insertGame(Game toAdd) {
        String gameJson = gson.toJson(toAdd);
        PreparedStatement statement = null;
        try{
            String sql = "insert into Games (Game, GameName) " +
                    "values (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, gameJson);
            statement.setString(2, toAdd.getGameName());

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

    private InputStream writeToIS(Game toAdd) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            oos.writeObject(toAdd);

            oos.flush();
            oos.close();

            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            return is;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public Object read(String toRead) {
        Game readInGame = null;
        String gameName = toRead;
        readInGame = findGame(gameName);
        return readInGame;
    }

    private Game findGame(String gameName) {
        Game game = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from Games where GameName = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, gameName);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                game = gson.fromJson(resultSet.getString(1), Game.class) ;
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
        return game;
    }

    public Object[] readAll() {
        Game game = null;
        ArrayList<Game> gameList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from Games ";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                game = gson.fromJson(resultSet.getString(1), Game.class);
                gameList.add(game);
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
        Game[] games = new Game[gameList.size()];
        for (int i = 0; i < gameList.size(); ++i){
            games[i] = gameList.get(i);
        }
        return games;
    }

    public boolean update(Game game) {
        Game toUpdate = game;
        boolean updated = updateGame(toUpdate);
        return updated;
    }

    private boolean updateGame(Game toUpdate) {
        PreparedStatement statement = null;
        String gameJson = gson.toJson(toUpdate);
        try {
            String sql = "update Games " +
                    "set Game = ?" +
                    "where GameName = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, gameJson);
            statement.setString(2, toUpdate.getGameName());

            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            try{
                if (statement != null) {
                    statement.close();
                    return true;
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

        }
        return false;
    }

    public boolean delete(Game game) {
        Game toDelete = game;
        boolean deleted = deleteGame(toDelete);
        return deleted;
    }

    private boolean deleteGame(Game toDelete) {
        PreparedStatement statement = null;
        try{
            String sql = "delete from Games where GameName = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, toDelete.getGameName());
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
            String sql = "delete from Games";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
