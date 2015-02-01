package ru.croacker.lbutil.database.metadata;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MlDatabase {

  private List<MlTable> tables = new ArrayList<>();

  public List<MlTable> getTables() {
    return tables;
  }
}
