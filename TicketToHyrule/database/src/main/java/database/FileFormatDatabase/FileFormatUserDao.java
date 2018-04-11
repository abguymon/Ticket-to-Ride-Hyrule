package database.FileFormatDatabase;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import cs240.lib.Model.User;
import database.RelationalDatabase.IRelationalDatabase;

/**
 * Created by David on 4/7/2018.
 */

public class FileFormatUserDao implements IFileFormatDatabase {
    private final static String FILE_PATH = "File Format Persistent Storage/Users";
    public FileFormatUserDao() {
        new File(FILE_PATH).mkdirs();
    }
    @Override
    public boolean create(Object object) {
        User toAdd = null;
        Writer writer = null;
        try{
            toAdd = (User) object;
            String newUser = FILE_PATH + "/" + toAdd.getUsername() + ".txt";
            Gson gson = new Gson();
            String jsonString = gson.toJson(toAdd);
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newUser), "utf-8"));
            writer.write(jsonString);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: User not stored correctly");
            return false;
        }
        return true;
    }

    @Override
    public boolean insert(Object object) {
        return false;
    }

    @Override
    public Object read(String toRead) {
        User user = null;
        String username = toRead;
        user = findUsername(username);
        return user;
    }

    private User findUsername(String username) {
        String fileName = username + ".txt";
        //String targetUser = FILE_PATH + "/" + toDelete.getUsername() + ".txt";
        File userDirectory = new File(FILE_PATH);
        File[] users = userDirectory.listFiles();
        if (users != null) {
            for (File file : users) {
                if (file.getName().equals(fileName)) {
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
                        User user = gson.fromJson(fileString, User.class);
                        return user;
                    } catch(Exception e) {return null;}

                }
            }
        }
        else {
            return null;
        }
        return null;
    }

    @Override
    public boolean update(Object object) {
        User toUpdate = null;
        Writer writer = null;
        try{
            toUpdate = (User) object;
            String targetUser = FILE_PATH + "/" + toUpdate.getUsername() + ".txt";
            Gson gson = new Gson();
            String jsonString = gson.toJson(toUpdate);
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetUser), "utf-8"));
            writer.write(jsonString);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: User not stored correctly");
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Object object) {
        User toDelete = null;
        try{
            toDelete = (User) object;
            String fileName = toDelete.getUsername() + ".txt";
            //String targetUser = FILE_PATH + "/" + toDelete.getUsername() + ".txt";
            File userDirectory = new File(FILE_PATH);
            File[] users = userDirectory.listFiles();
            if (users != null) {
                for (File file : users) {
                    if (file.getName().equals(fileName)) {
                        if (file.delete()) {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
            }
            else {
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: User not stored correctly");
            return false;
        }
        return true;
    }
}
