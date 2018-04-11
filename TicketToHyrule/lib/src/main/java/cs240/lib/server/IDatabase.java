package cs240.lib.server;

/**
 * Created by David on 4/7/2018.
 */
public interface IDatabase {
    boolean create(Object object);
    boolean insert(Object object);
    Object read(Object object, String toRead);
    Object[] readAll(Object object);
    boolean update(Object object);
    boolean delete(Object object);
    void clear(Object object);
}
