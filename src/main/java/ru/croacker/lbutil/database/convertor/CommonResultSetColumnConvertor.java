package ru.croacker.lbutil.database.convertor;

import ru.croacker.lbutil.database.metadata.MlColumn;

import java.sql.ResultSet;

/**
 *
 */
public class CommonResultSetColumnConvertor implements Convertor<ResultSet, MlColumn>{
  @Override
  public MlColumn toMetadata(ResultSet table) {
    MlColumn mlTable = new MlColumn();
    //TODO Написать заполнение полей объекта
    return mlTable;
  }

  @Override
  public ResultSet toTable(MlColumn metadata) {
    return null;
  }
}
