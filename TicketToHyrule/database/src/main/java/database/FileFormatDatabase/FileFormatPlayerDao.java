package database.FileFormatDatabase;

import database.RelationalDatabase.IRelationalDatabase;

/**
 * Created by David on 4/7/2018.
 */

public class FileFormatPlayerDao implements IFileFormatDatabase{
    //TODO: do we want this dao to be something else?
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
