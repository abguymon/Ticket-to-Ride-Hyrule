package database.RelationalDatabase;

import cs240.lib.Model.Game;
import cs240.lib.Model.LobbyGame;
import cs240.lib.Model.User;
import cs240.lib.common.Command;
import database.FileFormatDatabase.FileFormatUserDao;

/**
 * Created by savannah.jane on 4/11/2018.
 */

public class TestRelationalMain {
    public static final void main(String[] args) {
        RelationalDatabase database = new RelationalDatabase();
        userTest(database);
        gameTest(database);
        lobbyGameTest(database);
        commandTest(database);

        //database.clearAll();

    }

    private static void lobbyGameTest(RelationalDatabase database) {
        database.clear(new LobbyGame(0, 0, "clear"));
        LobbyGame lg = new LobbyGame(0, 0, "gargash");
        LobbyGame lg2 = new LobbyGame(0,0,"Thedosia");
        LobbyGame lg3 = new LobbyGame(1, 0, "Thrid");
        database.insert(lg);
        database.insert(lg2);
        database.delete(lg2);
        database.insert(lg3);
        Object[] lobbyGames = database.readAll(new LobbyGame());
    }

    private static void commandTest(RelationalDatabase database) {
        database.clear(new Command("clear", new String[1], new Object[1]));
        String[] chattypes = {String.class.getName(), String.class.getName(), String.class.getName()};
        String user = "user";
        String hello = "hello";
        String game1 = "game1";
        Object[] chatParams = {user, hello, game1};
        Command chat = new Command("chat", chattypes, chatParams);

        String game2 = "game2";
        Object[] chat2Params = {user, hello, game2};
        Command chat2 = new Command("chat", chattypes, chat2Params);

        String[] loginTypes = {String.class.getName(), String.class.getName()};
        Object[] loginParams = {"user", "password"};
        Command login = new Command("login", loginTypes, loginParams);

        Command register = new Command("register", loginTypes, loginParams);

        database.insert(register);
        database.insert(login);
        database.insert(chat);
        database.insert(chat2);
        Object[] commands = database.readAll(new Command("readAll", new String[1], new Object[1]));
        database.clearCommandByGame("game1");

    }

    private static void gameTest(RelationalDatabase database) {
        database.clear(new Game("clear"));
        Game game1 = new Game("game1");
        Game game2 = new Game("game2");
        Game game3 = new Game("game3");

        database.insert(game1);
        database.insert(game2);
        database.insert(game3);
        database.delete(game2);
        Game shouldBeGame1 = (Game)database.read(new Game("read"), game1.getGameName());
    }

    private static void userTest(RelationalDatabase database) {
        database.clear(new User());
        User user = new User("philipds", "Spiderman23");
        User userAlt = new User("philipds", "WallaceandGromit");
        User user2 = new User("savannahjane", "PiebaldPaint1");
        User user3 = new User("HyrumAlan", "chumbawumba");

        //database.create(user);
        database.insert(user);
        database.insert(user2);
        database.insert(user3);
        Object[] readAll = database.readAll(user);
        database.delete(user2);
        //database.clear(user);



        //database.create(user2);
        //database.create(user3);

        database.update(userAlt);

        //database.delete(user3);

        User userRead = (User) database.read(new User(),"philipds");
        System.out.println(userRead.getUsername());
        System.out.println(userRead.getPassword());
        //database.closeConnection(false);
    }
}
