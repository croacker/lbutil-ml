package ru.croacker.lbutil.service;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.diff.output.DiffOutputControl;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.integration.commandline.CommandLineUtils;
import liquibase.resource.FileSystemResourceAccessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.DbConnectionDto;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Сервис взаимодействия с liquibase
 */
@Service
@Slf4j
public class LiquibaseService {

  public static final String EMPTY_CONTEXT = "";

  public String testConnection(DbConnectionDto connection) {
    String resultMessage;
    try {
      getDatabase(connection);
      resultMessage = "Соединение успешно установлено!";
    } catch (DatabaseException e) {
      resultMessage = e.getMessage();
      log.error(e.getMessage(), e);
    }
    return resultMessage;
  }

  /**
   * Применить набор изменений к существующей БД
   * @throws LiquibaseException
   * @throws IOException
   * @throws ParserConfigurationException
   */
  public void aplyChangelog(DbConnectionDto connection, String changelogFile) throws LiquibaseException, IOException, ParserConfigurationException {
    Liquibase liquibase = new Liquibase(changelogFile, new FileSystemResourceAccessor(), getDatabase(connection));
    liquibase.update(EMPTY_CONTEXT);
  }

  /**
   * Сформировать файл наборов изменений на основании существующей БД
   * @throws LiquibaseException
   * @throws IOException
   * @throws ParserConfigurationException
   */
  public void createChangelog(DbConnectionDto connection, String changelogFile) throws LiquibaseException, IOException, ParserConfigurationException {
    CommandLineUtils.doGenerateChangeLog(changelogFile, getDatabase(connection), null, null,
        null, "samebadu", null, null, new DiffOutputControl());
  }

  private Database getDatabase(DbConnectionDto connection) throws DatabaseException {
    return CommandLineUtils.createDatabaseObject(
        getClass().getClassLoader(),
        connection.getUrl(),
        connection.getUser(),
        connection.getPassword(),
        connection.getJdbcDriver(), null, null, false, false, null, null, null, null);
  }

}
