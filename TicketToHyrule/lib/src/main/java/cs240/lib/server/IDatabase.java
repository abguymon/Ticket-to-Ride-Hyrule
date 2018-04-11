package cs240.lib.server;

/**
 * Created by David on 4/7/2018.
 */
public interface IDatabase {
    boolean create(Object object);
    boolean insert(Object object);
    Object read(String toRead);
    Object[] readAll(String toRead);
    boolean update(Object object);
    boolean delete(Object object);
    void clear();
}
