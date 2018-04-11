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
        return true;
    }


    public boolean insert(Object object) {
        return false;
    }

    public Object read(String toRead) {
        Game readInGame = null;
        String gameName = toRead;
        readInGame = findGame(gameName);
        return readInGame;
    }


    public Object[] readAll() {
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
       return true;
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
        return true;
    }


    public void clear() {

    }
}
