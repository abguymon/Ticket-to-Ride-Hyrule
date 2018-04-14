package database.FileFormatDatabase;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import cs240.lib.Model.Game;
import cs240.lib.common.Command;

/**
 * Created by David on 4/7/2018.
 */

public class FileFormatCommandDao {
    private int commandIndex;
    private final static String FILE_PATH = "File Format Persistent Storage/Commands";
    public FileFormatCommandDao() {
        commandIndex = 0;
        new File(FILE_PATH).mkdirs();
    }

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
        Command toAdd = null;
        Writer writer = null;
        try{
            toAdd = (Command) object;
            String newUser = FILE_PATH + "/" + commandIndex + ".txt";
            ++commandIndex;
            Gson gson = new Gson();
            String jsonString = gson.toJson(toAdd);
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newUser), "utf-8"));
            writer.write(jsonString);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: Game not stored correctly");
            return false;
        }
        return true;
    }

    public Object read(String toRead) {
        Command command = null;
        String commandIdentifyer = toRead;
        command = findCommand(commandIdentifyer);
        return command;
    }


    public Object[] readAll() {
        ArrayList<Command> commandInfo = new ArrayList<>();
        File userDirectory = new File(FILE_PATH);
        File[] games = userDirectory.listFiles();
        if (games != null) {
            for (File file : games) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(file));
                } catch (Exception e) {return null;}
                String         line = null;
                StringBuilder  stringBuilder = new StringBuilder();
                String         ls = System.getProperty("line.separator");

                try {
                    while((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                        stringBuilder.append(ls);
                    }

                    String fileString = stringBuilder.toString();
                    reader.close();
                    Gson gson = new Gson();
                    Command command = gson.fromJson(fileString, Command.class);
                    commandInfo.add(command);
                } catch(Exception e) {return null;}
            }
            return commandInfo.toArray();
        }
        else {
            return null;
        }
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
        File userDirectory = new File(FILE_PATH);
        File[] users = userDirectory.listFiles();
        if (users != null) {
            for (File file : users) {
                try {
                    file.delete();
                } catch(Exception e) {return;}
            }
            return;
        }
        else {
            return;
        }
    }

    public boolean clearCommandByGame(String gameName) {
        return false;
    }
}
