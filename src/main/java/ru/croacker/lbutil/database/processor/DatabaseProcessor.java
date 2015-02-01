package ru.croacker.lbutil.database.processor;

import ru.croacker.lbutil.database.metadata.MlTable;

import java.util.Map;

/**
 *
 */
public interface DatabaseProcessor {

  public Map<String, MlTable> readTables();

}
