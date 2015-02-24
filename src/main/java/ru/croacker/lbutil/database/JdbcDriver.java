package ru.croacker.lbutil.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by user on 16.03.14.
 * JDBC-drivers enum
 */
@AllArgsConstructor
public enum JdbcDriver {

  H2("org.h2.Driver", "jdbc:h2:<os_path_to_file>"),
  //TODO Подумать как присобачить к URL доп параметры, например для постгре unknownLength = int
  //Выглядит это так jdbc:postgresql://localhost:5433/mlcms_lo2?unknownLength=10485760
  POSTGRESQL("org.postgresql.Driver", "jdbc:postgresql://<host>:<port>/<database>?user=<username>&password=<pass>"),
  ORACLE("oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@<host>:<port>:<service>"),
  DERBY_REMOTE("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://<server>:<port>/<database>"),
  DERBY_EMBEDDED("org.apache.derby.jdbc.EmbeddedDriver", "jdbc:derby:<subsubprotocol>:<database>"),
  HSQL_REMOTE("org.hsql.jdbcDriver", "jdbc:hsqldb:file:<database>"),
  HSQL_EMBEDDED("org.hsql.jdbc.JDBCDriver", "jdbc:hsqldb:mem:<database>"),
  MYSQL("com.mysql.jdbc.Driver", "jdbc:mysql://<host>:<port>/<database>?user=<username>&password=<password>"),
  MSSQL_JTDS("net.sourceforge.jtds.jdbc.Driver", "jdbc:jtds:sqlserver://<host>:<port>/<database>"),
  MSSQL_MS("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://<host_or_servername>:<port>;databasename=<databse>"),
  SQLITE("org.sqlite.JDBC", "jdbc:sqlite:<database>"),
  SYSBASE_NATIVE("com.sybase.jdbc4.jdbc.SybConnectionPoolDataSource", "jdbc:jtds:sybase://<host>:<port>/<database>");

  @Getter
  private String driverName;
  @Getter
  private String urlTemplate;

  @Override
  public String toString() {
    return driverName;
  }

  public static boolean isTemplate(String text) {
    boolean result = false;
    for(JdbcDriver value: values()){
      if(text.equals(value.getUrlTemplate())){
        result = true;
        break;
      }
    }
    return result;
  }
}
