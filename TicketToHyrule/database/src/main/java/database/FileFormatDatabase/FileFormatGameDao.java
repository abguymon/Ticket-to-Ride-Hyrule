package database.FileFormatDatabase;

import cs240.lib.Model.Game;
import database.RelationalDatabase.IRelationalDatabase;

/**
 * Created by David on 4/7/2018.
 */

public class FileFormatGameDao implements IFileFormatDatabase {
    @Override
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

    @Override
    public boolean insert(Object object) {
        return false;
    }

    private boolean createGame(Game toAdd) {
        //TODO: create toAdd in file database, returning true if no errors
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
        //TODO: readInGame assigned to game found by gameName in file database
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
        //TODO: update toUpdate in file database, returning true if no errors
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
        //TODO: delete toDelete in file database, returning true if no error
        return false;
    }
}
