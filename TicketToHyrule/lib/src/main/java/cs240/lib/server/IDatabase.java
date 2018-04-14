package cs240.lib.server;

import cs240.lib.Model.Game;
import cs240.lib.Model.LobbyGame;
import cs240.lib.Model.User;
import cs240.lib.common.Command;

/**
 * Created by David on 4/7/2018.
 */
public interface IDatabase {
    boolean create(User user);
    boolean create(Game game);
    boolean create(Command command);
    boolean create(LobbyGame lobbyGame);
    boolean insert(User user);
    boolean insert(Game game);
    boolean insert(Command command);
    boolean insert(LobbyGame lobbyGame);
    Object read(Object object, String toRead);
    Object[] readAll(User user);
    Object[] readAll(Game game);
    Object[] readAll(Command command);
    Object[] readAll(LobbyGame lobbyGame);
    boolean update(User user);
    boolean update(Game game);
    boolean update(Command command);
    boolean update(LobbyGame lobbyGame);
    boolean delete(User user);
    boolean delete(Game game);
    boolean delete(Command command);
    boolean delete(LobbyGame lobbyGame);
    boolean clearCommandByGame(String gameName);
    void clear(User user);
    void clear(Game game);
    void clear(Command command);
    void clear(LobbyGame lobbyGame);
    void clearAll();
}
