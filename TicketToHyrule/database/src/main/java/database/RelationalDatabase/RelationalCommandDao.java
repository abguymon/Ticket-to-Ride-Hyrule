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

    private boolean createCommand(Command toCreate) {
        Statement statement = null;
        try{
            statement = connection.createStatement();
            String sql = "create table if not exists Commands " +
                    "(" +
                    "Method_Name text not null," +
                    "Parameter_Types BLOB not null" +
                    "Parameters BLOB not null" +
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
        Command toInsert = null;
        try{
            toInsert = (Command) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as Command");
            return false;
        }
        boolean inserted = insertCommand(toInsert);
        return inserted;
    }

    private boolean insertCommand(Command toInsert) {
        InputStream parameterTypesIS = writeToIS(toInsert.getParameterTypeNames());
        InputStream parametersIS = writeToIS(toInsert.getParameters());
        PreparedStatement statement = null;
        try{
            String sql = "insert into Commands (Method_Name, Parameter_Types, Parameters) " +
                    "values (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, toInsert.getMethodName());
            statement.setBlob(2, parameterTypesIS);
            statement.setBlob(3, parametersIS);

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

    private InputStream writeToIS(Object[] toWrite) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            oos.writeObject(toWrite);

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
