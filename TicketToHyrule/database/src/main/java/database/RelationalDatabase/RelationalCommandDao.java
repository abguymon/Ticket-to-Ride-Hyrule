package database.RelationalDatabase;

import java.sql.Connection;

import cs240.lib.common.Command;

/**
 * Created by David on 4/7/2018.
 */

public class RelationalCommandDao implements IRelationalDatabase {

    private Connection connection;

    public RelationalCommandDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean create(Object object) {
        Command toCreate = null;
        try{
            toCreate = (Command) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as Command");
            return false;
        }
        boolean created = createCommand(toCreate);
        return created;
    }

    @Override
    public boolean insert(Object object) {
        return false;
    }

    private boolean createCommand(Command toCreate) {
        //TODO: add command to database, returning true if no errors
        return false;
    }

    @Override
    public Object read(String toRead) {
        Command command = null;
        String commandIdentifyer = toRead;
        command = findCommand(commandIdentifyer);
        return command;
    }

    private Command findCommand(String commandIdentifyer) {
        //TODO: might need to make different read command to get sequencial commands rather than by identifyer
        return null;
    }

    @Override
    public boolean update(Object object) {
        Command toUpdate = null;
        try{
            toUpdate = (Command) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as Command");
            return false;
        }
        boolean updated = updateCommand(toUpdate);
        return updated;
    }

    private boolean updateCommand(Command toUpdate) {
        //TODO: might not need to update commands
        return false;
    }

    @Override
    public boolean delete(Object object) {
        Command toDelete = null;
        try{
            toDelete = (Command) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as Command");
            return false;
        }
        boolean deleted = deleteCommand(toDelete);
        return deleted;
    }

    private boolean deleteCommand(Command toDelete) {
        //TODO: delete toDelete in database, returning true if no errors
        return false;
    }
}
