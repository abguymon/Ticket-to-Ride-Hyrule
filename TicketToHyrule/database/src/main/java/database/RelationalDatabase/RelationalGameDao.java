package database.RelationalDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import cs240.lib.Model.Game;

/**
 * Created by David on 4/7/2018.
 */

public class RelationalGameDao implements IRelationalDatabase {
    private Connection connection;

    public RelationalGameDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean create(Object object) {
        Game toCreate = null;
        try{
            toCreate = (Game) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as Game");
            return false;
        }
        boolean created = createGame(toCreate);

        return created;
    }

    private boolean createGame(Game toAdd) {
        Statement statement = null;
        try{
            statement = connection.createStatement();
            String sql = "create table if not exists Games " +
                    "(" +
                    "UserID integer primary key autoincrement," +
                    "Game_Name text not null," +
                    "Password_ text not null," +
                    "Email text not null," +
                    "First_Name text not null," +
                    "Last_Name text not null," +
                    "Gender text not null," +
                    "PersonID text" +
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

    @Override
    public boolean insert(Object object) {
        Game toAdd = null;
        try{
            toAdd = (Game) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as Game");
            return false;
        }
        boolean inserted = insertGame(toAdd);
        return inserted;
    }

    private boolean insertGame(Game toAdd) {
        PreparedStatement statement = null;
        try{
            String sql = "insert into Games (Game_Name, ) " +
                    "values (?, )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, toAdd.getGameName());
            /*statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirst_name());
            statement.setString(5, user.getLast_name());
            statement.setString(6, user.getGender());
            statement.setString(7, user.getPersonID());*/

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

    @Override
    public Object read(String toRead) {
        Game readInGame = null;
        String gameName = toRead;
        readInGame = findGame(gameName);
        return readInGame;
    }

    private Game findGame(String gameName) {
        //TODO: readInGame assigned to game found by gameName in database
        return null;
    }

    @Override
    public boolean update(Object object) {
        Game toUpdate = null;
        try{
            toUpdate = (Game) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as Game");
            return false;
        }
        boolean updated = updateGame(toUpdate);
        return updated;
    }

    private boolean updateGame(Game toUpdate) {
        //TODO: update toUpdate in database, returning true if no errors
        return false;
    }

    @Override
    public boolean delete(Object object) {
        Game toDelete = null;
        try{
            toDelete = (Game) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as Game");
            return false;
        }
        boolean deleted = deleteGame(toDelete);
        return deleted;
    }

    private boolean deleteGame(Game toDelete) {
        //TODO: delete toDelete in database, returning true if no error
        return false;
    }
}
