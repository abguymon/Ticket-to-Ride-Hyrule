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

import cs240.lib.Model.LobbyGame;

/**
 * Created by David on 4/13/2018.
 */

public class RelationalLobbyGameDao {
    private Connection connection;
    private Gson gson = new Gson();
    public RelationalLobbyGameDao(Connection connection){
        this.connection = connection;
    }
    public RelationalLobbyGameDao(){connection = null;}

    public void setConnection(Connection connection) {this.connection = connection;}

    public boolean create() {
        Statement statement = null;
        try{
            statement = connection.createStatement();
            String sql = "create table if not exists Lobby_Games " +
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

    public boolean insert(LobbyGame lobbyGame) {
        String gameJson = gson.toJson(lobbyGame);
        PreparedStatement statement = null;
        try{
            String sql = "insert into Lobby_Games (Game, GameName) " +
                    "values (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, gameJson);
            statement.setString(2, lobbyGame.getGameName());

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

    public Object read(String gameName) {
        LobbyGame game = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from Lobby_Games where GameName = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, gameName);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                game = gson.fromJson(resultSet.getString(1),LobbyGame.class);
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
        LobbyGame game = null;
        ArrayList<LobbyGame> gameList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from Lobby_Games ";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                game = gson.fromJson(resultSet.getString(1), LobbyGame.class);
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
        LobbyGame[] games = new LobbyGame[gameList.size()];
        for (int i = 0; i < gameList.size(); ++i){
            games[i] = gameList.get(i);
        }
        return games;
    }

    public boolean update(LobbyGame toUpdate) {
        PreparedStatement statement = null;
        String gameJson = gson.toJson(toUpdate);
        try {
            String sql = "update Lobby_Games " +
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

    public boolean delete(LobbyGame toDelete) {
        PreparedStatement statement = null;
        try{
            String sql = "delete from Lobby_Games where GameName = ?";
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
            String sql = "delete from Lobby_Games";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
