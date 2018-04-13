package database.FileFormatDatabase;

import cs240.lib.Model.Game;
import cs240.lib.Model.User;
import cs240.lib.common.Command;
import cs240.lib.server.IDatabase;

/**
 * Created by David on 4/7/2018.
 */

public class FileFormatDatabase implements IDatabase {
    private FileFormatUserDao userDao;
    private FileFormatCommandDao commandDao;
    private FileFormatGameDao gameDao;

    public FileFormatDatabase() {
        userDao = new FileFormatUserDao();
        commandDao = new FileFormatCommandDao();
        gameDao = new FileFormatGameDao();
    }
    public boolean create(User user) {return userDao.create(user);}
    public boolean create(Game game) {return gameDao.create(game);}
    public boolean create(Command command) {return commandDao.create(command);}
    public boolean insert(User user) {return userDao.insert(user);}
    public boolean insert(Game game) {return gameDao.insert(game);}
    public boolean insert(Command command) {return commandDao.insert(command);}
    public Object read(Object object, String toRead) {
        if (object.getClass() == User.class) {
            userDao.read(toRead);
        }
        else if (object.getClass() == Game.class) {
            gameDao.read(toRead);
        }
        else if (object.getClass() == Command.class) {
            commandDao.read(toRead);
        }
        return false;
    }
    public Object[] readAll(User user) {return userDao.readAll();}
    public Object[] readAll(Game game) {return gameDao.readAll();}
    public Object[] readAll(Command command) {return commandDao.readAll();}
    public boolean update(User user) {return userDao.update(user);}
    public boolean update(Game game) {return gameDao.update(game);}
    public boolean update(Command command) {return commandDao.update(command);}
    public boolean delete(User user) {return userDao.delete(user);}
    public boolean delete(Game game) {return gameDao.delete(game);}
    public boolean delete(Command command) {return commandDao.delete(command);}
    public void clear(User user) {userDao.clear();}
    public void clear(Game game) {gameDao.clear();}
    public void clear(Command command) {commandDao.clear();}
    public void clearAll() {
        userDao.clear();
        gameDao.clear();
        commandDao.clear();
    }

}
