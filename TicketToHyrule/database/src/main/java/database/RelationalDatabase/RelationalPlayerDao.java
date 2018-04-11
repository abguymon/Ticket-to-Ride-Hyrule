package database.RelationalDatabase;

import java.sql.Connection;

/**
 * Created by David on 4/7/2018.
 */
//TODO: do we want this dao to be something else?
public class RelationalPlayerDao implements IRelationalDatabase{
    private Connection connection;

    public RelationalPlayerDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean create(Object object) {
        return false;
    }

    @Override
    public boolean insert(Object object) {
        return false;
    }

    @Override
    public Object read(String toRead) {
        return null;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }
}
