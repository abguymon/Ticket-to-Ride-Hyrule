package database.RelationalDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by David on 4/10/2018.
 */

public class RelationalDatabase {
    private RelationalGameDao gameDao;
    private RelationalCommandDao commandDao;
    private RelationalUserDao userDao;

    final String URL = "jdbc:sqlite:TTH.db"; //TODO: make sure this is the right file
    private Connection connection = null;

    static{
        try{
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public RelationalDatabase(Connection connection){
        openConnection();
        gameDao = new RelationalGameDao(connection);
        commandDao = new RelationalCommandDao(connection);
        userDao = new RelationalUserDao(connection);
    }

    private void openConnection() {
        try{
            connection = DriverManager.getConnection(URL);
            connection.setAutoCommit(false);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(boolean commit){
        try{
            if (commit){
                connection.commit();
            }else {
                connection.rollback();
            }
            connection.close();
            connection = null;
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //for testing
    /*public static void main(String args[]){

    }*/

}
