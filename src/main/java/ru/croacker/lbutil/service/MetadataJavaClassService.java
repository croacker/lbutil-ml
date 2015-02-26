package ru.croacker.lbutil.service;

import ru.croacker.lbutil.database.metadata.MlDatabase;
import ru.croacker.lbutil.database.metadata.MlTable;

/**
 * Сервис экспорта Ml-классов в Java-классы
 */
public class MetadataJavaClassService {

  public void formClasses(MlDatabase mlDatabase){
    for (MlTable mlTable : mlDatabase.getTables()) {

    }
  }

}
