package ru.croacker.lbutil.service;

import ru.croacker.lbutil.database.metadata.MlDatabase;
import ru.croacker.lbutil.database.metadata.MlClass;

/**
 * Сервис экспорта Ml-классов в Java-классы
 */
public class MetadataJavaClassService {

  public void formClasses(MlDatabase mlDatabase){
    for (MlClass mlTable : mlDatabase.getTables()) {

    }
  }

}
