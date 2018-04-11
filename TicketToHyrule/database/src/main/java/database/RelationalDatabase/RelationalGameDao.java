package database.RelationalDatabase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
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
                    "Game BLOB not null," +
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
        InputStream gameIS = writeToIS(toAdd);
        PreparedStatement statement = null;
        try{
            String sql = "insert into Games (Game) " +
                    "values (?)";
            statement = connection.prepareStatement(sql);
            statement.setBlob(1, gameIS);

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
