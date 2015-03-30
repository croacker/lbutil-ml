package ru.croacker.lbutil.service.ddl;

import ru.croacker.lbutil.database.metadata.MlDatabase;

import javax.sql.DataSource;

/**
 * Обработчик
 */
public interface DatabaseDdlProcessor {

    MlDatabase getMetadata(DataSource dataSource);

}
