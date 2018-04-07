package database.RelationalDatabase;

import cs240.lib.Model.User;

/**
 * Created by David on 4/7/2018.
 */

public class RelationalUserDao implements IRelationalDatabase {
    @Override
    public boolean create(Object object) {
        User toAdd = null;
        try{
            toAdd = (User) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as User");
            return false;
        }
        boolean created = createUser(toAdd);
        return created;
    }

    private boolean createUser(User toAdd) {
        //TODO: create toAdd in database, returning true if no errors
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
        //TODO: read User from database using provided username
        return null;
    }

    @Override
    public boolean update(Object object) {
        User toUpdate = null;
        try{
            toUpdate = (User) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as User");
            return false;
        }
        boolean updated = updateUser(toUpdate);
        return updated;
    }

    private boolean updateUser(User toUpdate) {
        //TODO: update toUpdate in database, returning true if no errors
        return false;
    }

    @Override
    public boolean delete(Object object) {
        User toDelete = null;
        try{
            toDelete = (User) object;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error casting object as User");
            return false;
        }
        boolean deleted = deleteUser(toDelete);
        return false;
    }

    private boolean deleteUser(User toDelete) {
        //TODO: delete toDelete in database, returning true if no errors
        return false;
    }
}
