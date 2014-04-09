package CRUD;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Connect
{
    private DataSource dataSource;
    public Connect() throws NamingException
    {
        InitialContext ctx = new InitialContext();
        dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/forum");
    }

    public DataSource createConnection()
    {
        try
        {
            return dataSource;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

}
