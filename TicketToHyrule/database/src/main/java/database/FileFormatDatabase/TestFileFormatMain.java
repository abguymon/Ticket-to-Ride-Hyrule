package database.FileFormatDatabase;

import cs240.lib.Model.Game;
import cs240.lib.Model.User;
import cs240.lib.Model.colors.PlayerColor;
import cs240.lib.Model.gameParts.Player;

/**
 * Created by savannah.jane on 4/11/2018.
 */

public class TestFileFormatMain {
    public static final void main(String[] args) {
        User user = new User("philipds", "Spiderman23");
        User userAlt = new User("philipds", "WallaceandGromit");
        User user2 = new User("savannahjane", "PiebaldPaint1");
        User user3 = new User("HyrumAlan", "chumbawumba");
        FileFormatUserDao dao = new FileFormatUserDao();
        dao.insert(user);
        dao.insert(user2);
        dao.insert(user3);

        dao.update(userAlt);

        //dao.delete(user3);

        User userRead = (User) dao.read("savannahjane");
        System.out.println(userRead.getUsername());
        System.out.println(userRead.getPassword());

        Object[] users = dao.readAll();
        for (int i = 0; i < users.length; ++i) {
            User loopUser = (User) users[i];
            System.out.println(loopUser.getUsername());
            System.out.println(loopUser.getPassword());
        }

        FileFormatGameDao gameDao = new FileFormatGameDao();
        Game game1 = new Game("split");
        Game game2 = new Game("banana");

        gameDao.insert(game1);
        gameDao.insert(game2);

        gameDao.delete(game1);

        Game readGame = (Game) gameDao.read("banana");
        System.out.println(readGame.getGameName());

        FileFormatDatabase db = new FileFormatDatabase();
        db.clearAll();
        db.insert(game1);
        db.insert(user);
        game1.addPlayer(new Player(PlayerColor.RED, "FiliKiliDorryNorryOrryBifferBofferBombur"));
        db.update(game1);
    }
}

