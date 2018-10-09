package app.repositories;

import app.exceptions.DatabaseException;
import app.exceptions.DomainException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractSqlRepository {

    protected static String url = "jdbc:mysql://localhost/javalab01";
    protected static String user = "root";
    protected static String password = "root";
    protected Connection dbConnection;

    public AbstractSqlRepository() throws DomainException
    {
       try
        {
            dbConnection = DriverManager.getConnection(url,user,password);
        }
        catch (SQLException exception){
           throw new DatabaseException();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        dbConnection.close();
    }
}
