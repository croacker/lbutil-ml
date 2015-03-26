package ru.croacker.lbutil.database.processor;

import ru.croacker.lbutil.database.metadata.MlClass;

import java.util.Map;

/**
 *
 */
public interface DatabaseProcessor {

  public Map<String, MlClass> readTables();

}
