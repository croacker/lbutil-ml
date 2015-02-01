package ru.croacker.lbutil.service;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.DbConnectionDto;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 */
@Service
@Slf4j
public class DataSourceService implements IConnectionsService {

  public DataSource getDataSource(DbConnectionDto connectionDto) {
    ComboPooledDataSource dataSource = null;
    try {
      dataSource = new ComboPooledDataSource();
      dataSource.setDriverClass(connectionDto.getJdbcDriver());
      dataSource.setJdbcUrl("jdbc:postgresql://localhost:5433/mlcms");
      dataSource.setUser("postgres");
      dataSource.setPassword("postgres");
    } catch (PropertyVetoException e) {
      log.error(e.getMessage(), e);
    }
    return dataSource;
  }

}
