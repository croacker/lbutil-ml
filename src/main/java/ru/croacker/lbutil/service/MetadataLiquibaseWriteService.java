package ru.croacker.lbutil.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.croacker.lbutil.database.metadata.MlColumn;
import ru.croacker.lbutil.database.metadata.MlDatabase;
import ru.croacker.lbutil.database.metadata.MlTable;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Date;

/**
 *
 */
@Service
@Slf4j
public class MetadataLiquibaseWriteService {

  public void write(MlDatabase mlDatabase, String fileName) throws ParserConfigurationException {

    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    Element root = document.createElementNS("http://www.liquibase.org/xml/ns/dbchangelog", "databaseChangeLog");
    root.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:schemaLocation", "http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd");

    //TODO Возможно стоит добавлять переменные ID для каждой из таблиц,

    for (MlTable mlTable : mlDatabase.getTables()) {
      Element changeSet = document.createElement("changeSet");
      changeSet.setAttribute("id", "ts_" + String.valueOf(new Date().getTime())); //todo: generate
      changeSet.setAttribute("author", "somebadu"); //todo: generate

      changeSet.appendChild(createInsertClass(document, mlTable));

      for (MlColumn mlColumn : mlTable.getColumns()) {
        Element columnMetadata = document.createElement("");
        //TODO Добавляем колонки
        changeSet.appendChild(columnMetadata);
      }


      root.appendChild(changeSet);
    }

  }

  /**
   * Создать элемент добавления записи в таблицу классов
   * @param document
   * @param mlTable
   * @return
   */
  private Element createInsertClass(Document document, MlTable mlTable){
    Element insertClass = document.createElement("insert");
    insertClass.setAttribute("tableName", "MlClass");

    Element columnData = document.createElement("column");
    columnData.setAttribute("name", "id");
    columnData.setAttribute("value", String.valueOf(mlTable.getId()));//TODO Добавить заполнение ID
    insertClass.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "tableName");
    columnData.setAttribute("value", mlTable.getTableName());
    insertClass.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "entityName");
    columnData.setAttribute("value", mlTable.getEntityName());
    insertClass.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "isSystem");
    columnData.setAttribute("value", Boolean.toString(mlTable.getIsSystem()));
    insertClass.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "hasHistory");
    columnData.setAttribute("value", Boolean.toString(mlTable.getHasHistory()));
    insertClass.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "isAbstract");
    columnData.setAttribute("value", Boolean.toString(mlTable.getIsAbstract()));
    insertClass.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "isCacheable");
    columnData.setAttribute("value", Boolean.toString(mlTable.getIsCacheable()));
    insertClass.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "description");
    columnData.setAttribute("value", mlTable.getDescription());
    insertClass.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "description");
    columnData.setAttribute("value", mlTable.getDescription());
    insertClass.appendChild(columnData);

    return insertClass;
  }

  /**
   * Создать элемент добавления записи в таблицу атрибутов
   * @param document
   * @param mlColumn
   * @return
   */
  private Element createInsertAttr(Document document, MlColumn mlColumn, int tableId){
    Element insertAttr = document.createElement("insert");
    insertAttr.setAttribute("tableName", "MlAttr");

    return insertAttr;
  }

}
