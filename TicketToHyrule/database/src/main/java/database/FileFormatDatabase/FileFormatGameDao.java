package database.FileFormatDatabase;


import java.io.File;

import cs240.lib.Model.Game;

/**
 * Created by David on 4/7/2018.
 */

public class FileFormatGameDao {

    private final static String FILE_PATH = "File Format Persistent Storage/Games";
    public FileFormatGameDao() {
        new File(FILE_PATH).mkdirs();
    }

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
