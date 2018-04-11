package database.FileFormatDatabase;


import cs240.lib.Model.Game;
import database.RelationalDatabase.IRelationalDatabase;

/**
 * Created by David on 4/7/2018.
 */

public class FileFormatGameDao {

    public boolean create(Object object) {
        Game toAdd = null;
        try{
            toAdd = (Game) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as Game");
            return false;
        }
        boolean created = createGame(toAdd);

        return created;
    }


    public boolean insert(Object object) {
        return false;
    }

    private boolean createGame(Game toAdd) {
        //TODO: create toAdd in file database, returning true if no errors
        return false;
    }


    public Object read(String toRead) {
        Game readInGame = null;
        String gameName = toRead;
        readInGame = findGame(gameName);
        return readInGame;
    }


    public Object[] readAll(String toRead) {
        return new Object[0];
    }

    private Game findGame(String gameName) {
        //TODO: readInGame assigned to game found by gameName in file database
        return null;
    }


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
        //TODO: update toUpdate in file database, returning true if no errors
        return false;
    }


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


    public void clear() {

    }

    private boolean deleteGame(Game toDelete) {
        //TODO: delete toDelete in file database, returning true if no error
        return false;
    }
}
