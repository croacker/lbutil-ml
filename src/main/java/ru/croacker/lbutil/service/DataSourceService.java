package ru.croacker.lbutil.service;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.DbConnectionDto;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

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
      dataSource.setJdbcUrl(connectionDto.getUrl());
      dataSource.setUser(connectionDto.getUser());
      dataSource.setPassword(connectionDto.getPassword());
    } catch (PropertyVetoException e) {
      log.error(e.getMessage(), e);
    }
    return dataSource;
  }

    public void destroyDataSource(DataSource dataSource){
        try {
            if(dataSource != null) {
                DataSources.destroy(dataSource);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

}
