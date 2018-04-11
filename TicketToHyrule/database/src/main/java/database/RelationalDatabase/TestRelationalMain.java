package database.RelationalDatabase;

import cs240.lib.Model.Game;
import cs240.lib.Model.User;
import database.FileFormatDatabase.FileFormatUserDao;

/**
 * Created by savannah.jane on 4/11/2018.
 */

public class TestRelationalMain {
    public static final void main(String[] args) {
        RelationalDatabase database = new RelationalDatabase();
        boolean test = database.create(new User("test", "test"));
        //userTest();
        //gameTest();
        //commandTest();

    }

    private static void commandTest() {

    }

    private static void gameTest() {

    }

    private static void userTest() {
        User user = new User("philipds", "Spiderman23");
        User userAlt = new User("philipds", "WallaceandGromit");
        User user2 = new User("savannahjane", "PiebaldPaint1");
        User user3 = new User("HyrumAlan", "chumbawumba");
        FileFormatUserDao dao = new FileFormatUserDao();
        dao.create(user);
        dao.create(user2);
        dao.create(user3);

        dao.update(userAlt);

        dao.delete(user3);

        User userRead = (User) dao.read("savannahjane");
        System.out.println(userRead.getUsername());
        System.out.println(userRead.getPassword());
    }
}
