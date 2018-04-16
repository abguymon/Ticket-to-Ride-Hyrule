package database.RelationalDatabase;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
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
    private Gson gson = new Gson();

    public RelationalCommandDao(Connection connection){
        this.connection = connection;
    }
    public RelationalCommandDao() {connection = null;}

    public void setConnection(Connection connection) {
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
                    "Command text not null," +
                    "Game_Name text" +
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
        String commandJson = gson.toJson(toInsert);
        String gameName = findGameName(toInsert);
        PreparedStatement statement = null;
        try{
            String sql = "insert into Commands (Command, Game_Name) " +
                    "values (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, commandJson);
            statement.setString(2, gameName);

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

    private String findGameName(Command toInsert) {
        String gameName = null;
        if (toInsert.getParameterTypeNames().length == 1){
            gameName = gson.fromJson(toInsert.getParametersAsJsonStrings()[0], String.class);
        }else {
            String methodName = toInsert.getMethodName().toLowerCase();
            if (methodName.equals("login") || methodName.equals("register")){
                gameName = null;
            }else if(methodName.equals("chat")){
                gameName = gson.fromJson(toInsert.getParametersAsJsonStrings()[2], String.class);
            }else{
                gameName = gson.fromJson(toInsert.getParametersAsJsonStrings()[1], String.class);
            }
        }
        return gameName;
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
                command = gson.fromJson(resultSet.getString(1), Command.class);
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
        } finally{
            try{
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


    public boolean clearbyGame(String gameName) {
        PreparedStatement statement = null;
        try{
            String sql = "delete from Commands where Game_Name = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, gameName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
}
