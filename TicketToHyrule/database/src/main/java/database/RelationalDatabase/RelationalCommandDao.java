package database.RelationalDatabase;

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

import cs240.lib.common.Command;

/**
 * Created by David on 4/7/2018.
 */

public class RelationalCommandDao{

    private Connection connection;

    public RelationalCommandDao(Connection connection){
        this.connection = connection;
    }


    public boolean create() {
        boolean created = createCommand();
        return created;
    }

    private boolean createCommand() {
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

    public boolean insert(Command command) {
        Command toInsert = command;
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

    public Object[] readAll() {
        Command command = null;
        ArrayList<Command> commandList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from Commands ";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String methodName = resultSet.getString(1);
                InputStream parameterTypesIS = (InputStream)resultSet.getBlob(2);
                InputStream parametersIS = (InputStream)resultSet.getBlob(3);
                String[] parameterTypes = writeToStringArray(parameterTypesIS);
                Object[] parameters = writeToObjectArray(parametersIS);
                command = new Command(methodName, parameterTypes, parameters);
                commandList.add(command);
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
        Command[] commands = new Command[commandList.size()];
        for (int i = 0; i < commandList.size(); ++i){
            commands[i] = commandList.get(i);
        }
        return commands;
    }

    private Object[] writeToObjectArray(InputStream parametersIS) {
        return new Object[0];
    }

    private String[] writeToStringArray(InputStream parameterTypesIS) {
        return new String[0];
    }

    public boolean update(Command command) {
        Command toUpdate = command;
        boolean updated = updateCommand(toUpdate);
        return updated;
    }

    private boolean updateCommand(Command toUpdate) {
        //TODO: might not need to update commands
        /*InputStream parameterTypesIS = writeToIS(toUpdate.getParameterTypeNames());
        InputStream parametersIS = writeToIS(toUpdate.getParameters());
        PreparedStatement statement = null;
        try{
            String sql = "update Commands " +
                    "set Method_Name = ?, Parameter_Types = ?, Parameters = ?" +
                    "where Method_Name = ?;"; //might want to find different key to look by
            statement = connection.prepareStatement(sql);
            statement.setString(1, toUpdate.getMethodName());
            statement.setBlob(2, parameterTypesIS);
            statement.setBlob(3, parametersIS);
            statement.setString(4, toUpdate.getMethodName());

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
            }
        }*/
        return false;
    }

    public boolean delete(Command command) {
        Command toDelete = command;
        boolean deleted = deleteCommand(toDelete);
        return deleted;
    }

    private boolean deleteCommand(Command toDelete) {
        //TODO: delete toDelete in database, returning true if no errors. Might not need
        return false;
        /*PreparedStatement statement = null;
        try{
            String sql = "delete from Commands where Method_Name = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, toDelete.getMethodName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;*/
    }

    public void clear() {
        Statement statement = null;
        try{
            statement = connection.createStatement();
            String sql = "delete from Commands";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
