package database.RelationalDatabase;

import cs240.lib.Model.Game;
import cs240.lib.Model.LobbyGame;
import cs240.lib.Model.User;
import database.FileFormatDatabase.FileFormatUserDao;

/**
 * Created by savannah.jane on 4/11/2018.
 */

public class TestRelationalMain {
    public static final void main(String[] args) {
        RelationalDatabase database = new RelationalDatabase();
        userTest(database);
        //gameTest();
        //commandTest();

    }

    private static void commandTest() {

    }

    private static void gameTest() {

    }

    private static void userTest(RelationalDatabase database) {
        User user = new User("philipds", "Spiderman23");
        User userAlt = new User("philipds", "WallaceandGromit");
        User user2 = new User("savannahjane", "PiebaldPaint1");
        User user3 = new User("HyrumAlan", "chumbawumba");

        //database.create(user);
        //database.insert(user);
        //database.insert(user2);
        //database.insert(user3);
        //Object[] readAll = database.readAll(user);
        //database.delete(user2);
        database.clear(user);

//        LobbyGame lg = new LobbyGame(0, 0, "gargash");
//        LobbyGame lg2 = new LobbyGame(0,0,"Thedosia");
//        database.insert(lg);
//        database.insert(lg2);

        //database.create(user2);
        //database.create(user3);

        //database.update(userAlt);

        //database.delete(user3);

        //User userRead = (User) database.read(new User(),"savannahjane");
        //System.out.println(userRead.getUsername());
        //System.out.println(userRead.getPassword());
        //database.closeConnection(false);
    }
}
