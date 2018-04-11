package cs240.lib.server;

/**
 * Created by David on 4/7/2018.
 */
//TODO: how to put these classes in a jar file?
public interface IDatabase {
    boolean create(Object object);
    boolean insert(Object object);
    Object read(String toRead);
    boolean update(Object object);
    boolean delete(Object object);
}
