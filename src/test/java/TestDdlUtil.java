import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by user on 01.02.2015.
 */
public class TestDdlUtil {

  public static void main(String[] args) throws PropertyVetoException, SQLException {
    DataSource dataSource = getDS();
    Platform platform = PlatformFactory.createNewPlatformInstance(dataSource);
    platform.readModelFromDatabase("mlcms");
  }

  private static DataSource getDS() throws PropertyVetoException, SQLException {
    ComboPooledDataSource cpds = new ComboPooledDataSource();
    cpds.setDriverClass("org.postgresql.Driver");
    cpds.setJdbcUrl("jdbc:postgresql://localhost:5433/mlcms");
    cpds.setUser("postgres");
    cpds.setPassword("postgres");
    try {
      Connection testConnection = cpds.getConnection();
      Statement testStatement = testConnection.createStatement();
      ResultSet resultSet = testStatement.executeQuery("select * from databasechangelog");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return cpds;
  }

}
