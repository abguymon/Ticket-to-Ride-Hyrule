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
import cs240.lib.Model.LobbyGame;

/**
 * Created by savannah.jane on 4/13/2018.
 */

public class FileFormatLobbyGameDao {
    private final static String FILE_PATH = "File Format Persistent Storage/Lobby Games";
    public FileFormatLobbyGameDao() {
        new File(FILE_PATH).mkdirs();
    }

    public boolean create(Object object) {
        LobbyGame toAdd = null;
        try{
            toAdd = (LobbyGame) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as Game");
            return false;
        }
        return true;
    }


    public boolean insert(Object object) {
        LobbyGame toAdd = null;
        Writer writer = null;
        try{
            toAdd = (LobbyGame) object;
            String newUser = FILE_PATH + "/" + toAdd.getGameName() + ".txt";
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
        LobbyGame readInGame = null;
        String gameName = toRead;
        readInGame = findGame(gameName);
        return readInGame;
    }


    public Object[] readAll() {
        ArrayList<LobbyGame> gameInfo = new ArrayList<>();
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
                    LobbyGame game = gson.fromJson(fileString, LobbyGame.class);
                    gameInfo.add(game);
                } catch(Exception e) {return null;}
            }
            return gameInfo.toArray();
        }
        else {
            return null;
        }
    }

    private LobbyGame findGame(String gameName) {
        String fileName = gameName + ".txt";
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
                        LobbyGame game = gson.fromJson(fileString, LobbyGame.class);
                        return game;
                    } catch(Exception e) {return null;}

                }
            }
        }
        else {
            return null;
        }
        return null;
    }


    public boolean update(Object object) {
        LobbyGame toUpdate = null;
        Writer writer = null;
        try{
            toUpdate = (LobbyGame) object;
            String targetUser = FILE_PATH + "/" + toUpdate.getGameName() + ".txt";
            Gson gson = new Gson();
            String jsonString = gson.toJson(toUpdate);
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetUser), "utf-8"));
            writer.write(jsonString);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: Game not updated correctly");
            return false;
        }
        return true;
    }

    public boolean delete(Object object) {
        LobbyGame toDelete = null;
        try{
            toDelete = (LobbyGame) object;
            String fileName = toDelete.getGameName() + ".txt";
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
            System.out.println("Error: Game not deleted correctly");
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
}
