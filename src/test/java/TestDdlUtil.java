import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.w3c.dom.Document;
import ru.croacker.lbutil.ContextLoader;
import ru.croacker.lbutil.database.metadata.MlDatabase;
import ru.croacker.lbutil.service.DdlService;
import ru.croacker.lbutil.service.FileWriteService;
import ru.croacker.lbutil.service.MetadataLiquibaseService;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 *
 */
public class TestDdlUtil {

  public static void main(String[] args) throws PropertyVetoException, SQLException {
    loadContext();

    DdlService ddlService = ContextLoader.getInstance().getContext().getBean(DdlService.class);
    MlDatabase mlDatabase = ddlService.getMlDatabaseModelFromMlClass(getDS());

    MetadataLiquibaseService metadataLiquibaseService = ContextLoader.getInstance().getContext().getBean(MetadataLiquibaseService.class);
    Document document = metadataLiquibaseService.formDocument(mlDatabase);

    FileWriteService fileWriteService = ContextLoader.getInstance().getContext().getBean(FileWriteService.class);
    fileWriteService.writeXml(document, "c:/tmp/ml-metadata-file.xml");
  }

  private static DataSource getDS() throws PropertyVetoException, SQLException {
    ComboPooledDataSource cpds = new ComboPooledDataSource();
    cpds.setDriverClass("org.postgresql.Driver");
    cpds.setJdbcUrl("jdbc:postgresql://localhost:5433/mlcms");
    cpds.setUser("postgres");
    cpds.setPassword("postgres");
//    try {
//      Connection testConnection = cpds.getConnection();
//      Statement testStatement = testConnection.createStatement();
//      ResultSet resultSet = testStatement.executeQuery("select * from databasechangelog");
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
    return cpds;
  }

  private static void loadContext() {
    ContextLoader.getInstance().load();
  }

}
