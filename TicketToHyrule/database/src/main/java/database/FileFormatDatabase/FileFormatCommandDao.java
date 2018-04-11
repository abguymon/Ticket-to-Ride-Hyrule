package database.FileFormatDatabase;

import cs240.lib.common.Command;
import database.RelationalDatabase.IRelationalDatabase;

/**
 * Created by David on 4/7/2018.
 */

public class FileFormatCommandDao {

    public boolean create(Object object) {
        Command toCreate = null;
        try{
            toCreate = (Command) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as Command");
            return false;
        }
       return true;
    }


    public boolean insert(Object object) {
        return false;
    }

    public Object read(String toRead) {
        Command command = null;
        String commandIdentifyer = toRead;
        command = findCommand(commandIdentifyer);
        return command;
    }


    public Object[] readAll() {
        return new Object[0];
    }

    private Command findCommand(String commandIdentifyer) {
        //TODO: might need to make different read command to get sequencial commands rather than by identifyer
        return null;
    }


    public boolean update(Object object) {
        Command toUpdate = null;
        try{
            toUpdate = (Command) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as Command");
            return false;
        }
        return true;
    }

    public boolean delete(Object object) {
        Command toDelete = null;
        try{
            toDelete = (Command) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as Command");
            return false;
        }
        return true;
    }


    public void clear() {

    }
}
