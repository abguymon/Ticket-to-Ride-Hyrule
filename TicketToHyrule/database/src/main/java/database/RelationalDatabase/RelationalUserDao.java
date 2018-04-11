package database.RelationalDatabase;

import java.sql.Connection;

import cs240.lib.Model.User;

/**
 * Created by David on 4/7/2018.
 */

public class RelationalUserDao {

    private Connection connection;

    public RelationalUserDao(Connection connection){
        this.connection = connection;
    }

    public boolean create() {
        boolean created = createUser();
        return created;
    }

    private boolean createUser() {

        return false;
    }


    public boolean insert(User user) {
        User toInsert = user;
        boolean inserted = insertUser(toInsert);
        return inserted;
    }

    private boolean insertUser(User toInsert) {
        //TODO: create toInsert in database, returning true if no errors
        return false;
    }




    public Object read(String toRead) {
        String username = toRead;
        User user = findUsername(username);
        return user;
    }

    private User findUsername(String username) {
        //TODO: read User from database using provided username
        return null;
    }

    public Object[] readAll() {

        return new Object[0];
    }




    public boolean update(User user) {
        User toUpdate = user;
        boolean updated = updateUser(toUpdate);
        return updated;
    }

    private boolean updateUser(User toUpdate) {
        //TODO: update toUpdate in database, returning true if no errors
        return false;
    }

    public boolean delete(User user) {
        User toDelete = user;
        boolean deleted = deleteUser(toDelete);
        return false;
    }

    private boolean deleteUser(User toDelete) {
        //TODO: delete toDelete in database, returning true if no errors
        return false;
    }


    public void clear() {

    }
}
