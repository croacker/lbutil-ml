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
 * Сервис экспорта Ml-класов в liquibase
 */
@Service
@Slf4j
public class MetadataLiquibaseService {

  public Document formDocument(MlDatabase mlDatabase) {
    Document document;
    try {
      document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    } catch (ParserConfigurationException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }

    Element root = document.createElementNS("http://www.liquibase.org/xml/ns/dbchangelog", "databaseChangeLog");
    root.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:schemaLocation", "http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd");
    document.appendChild(root);

    for (MlTable mlTable : mlDatabase.getTables()) {
      Long tableId = idForTable(mlDatabase, mlTable);
      Element changeSet = document.createElement("changeSet");
      changeSet.setAttribute("id", "ts_" + String.valueOf(new Date().getTime()) + String.valueOf(tableId)); //todo: generate
      changeSet.setAttribute("author", "somebadu"); //todo: generate

      changeSet.appendChild(createInsertClass(document, mlTable, tableId));

      for (MlColumn mlColumn : mlTable.getColumns()) {
        changeSet.appendChild(createInsertAttr(document, mlColumn, tableId));
      }
      root.appendChild(changeSet);
    }

    return document;
  }

  private Long idForTable(MlDatabase mlDatabase, MlTable mlTable) {
    Long id = mlTable.getId();
    if (id == null) {
      id = Long.valueOf(mlDatabase.getTables().indexOf(mlTable));
    }
    return id;
  }

  /**
   * Создать элемент добавления записи в таблицу классов
   *
   * @param document
   * @param mlTable
   * @return
   */
  private Element createInsertClass(Document document, MlTable mlTable, Long tableId) {
    Element insertClass = document.createElement("insert");
    insertClass.setAttribute("tableName", "MlClass");

    Element columnData = document.createElement("column");
    columnData.setAttribute("name", "id");
    columnData.setAttribute("value", String.valueOf(tableId));//TODO Добавить заполнение ID
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
    columnData.setAttribute("value", String.valueOf(mlTable.getIsSystem()));
    insertClass.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "hasHistory");
    columnData.setAttribute("value", String.valueOf(mlTable.getHasHistory()));
    insertClass.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "isAbstract");
    columnData.setAttribute("value", String.valueOf(mlTable.getIsAbstract()));
    insertClass.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "isCacheable");
    columnData.setAttribute("value", String.valueOf(mlTable.getIsCacheable()));
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
   *
   * @param document
   * @param mlColumn
   * @return
   */
  private Element createInsertAttr(Document document, MlColumn mlColumn, Long tableId) {

    Element insertAttr = document.createElement("insert");
    insertAttr.setAttribute("tableName", "MlAttr");

    Element columnData = document.createElement("column");
    columnData.setAttribute("name", "mlClass");
    columnData.setAttribute("value", Long.toString(tableId));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "tableFieldName");
    columnData.setAttribute("value", mlColumn.getTableFieldName());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "entityFieldName");
    columnData.setAttribute("value", mlColumn.getEntityFieldName());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "systemField");
    columnData.setAttribute("value", String.valueOf(mlColumn.getSystemField()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "primaryKey");
    columnData.setAttribute("value", String.valueOf(mlColumn.getPrimaryKey()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "autoIncrement");
    columnData.setAttribute("value", String.valueOf(mlColumn.getAutoIncrement()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "fieldType");
    columnData.setAttribute("value", mlColumn.getFieldType());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "linkAttr");
    columnData.setAttribute("value", String.valueOf(mlColumn.getLinkAttr()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "linkClass");
    columnData.setAttribute("value", String.valueOf(mlColumn.getLinkClass()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "linkFilter");
    columnData.setAttribute("value", mlColumn.getLinkFilter());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "inForm");
    columnData.setAttribute("value", String.valueOf(mlColumn.getInForm()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "inList");
    columnData.setAttribute("value", String.valueOf(mlColumn.getInList()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "useInSimpleSearch");
    columnData.setAttribute("value", String.valueOf(mlColumn.getUseInSimpleSearch()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "useInExtendedSearch");
    columnData.setAttribute("value", String.valueOf(mlColumn.getUseInExtendedSearch()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "fieldFormat");
    columnData.setAttribute("value", mlColumn.getFieldFormat());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "group");
    columnData.setAttribute("value", String.valueOf(mlColumn.getGroup()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "description");
    columnData.setAttribute("value", mlColumn.getDescription());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "viewPos");
    columnData.setAttribute("value", String.valueOf(mlColumn.getViewPos()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "newLine");
    columnData.setAttribute("value", String.valueOf(mlColumn.getNewLine()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "offset");
    columnData.setAttribute("value", String.valueOf(mlColumn.getOffset()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "totalLength");
    columnData.setAttribute("value", String.valueOf(mlColumn.getTotalLength()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "titleLength");
    columnData.setAttribute("value", String.valueOf(mlColumn.getTitleLength()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "tableHeight");
    columnData.setAttribute("value", String.valueOf(mlColumn.getTableHeight()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "mandatory");
    columnData.setAttribute("value", String.valueOf(mlColumn.getMandatory()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "replicationType");
    columnData.setAttribute("value", mlColumn.getReplicationType());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "defaultValue");
    columnData.setAttribute("value", mlColumn.getDefaultValue());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "lazy");
    columnData.setAttribute("value", String.valueOf(mlColumn.getLazy()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "lazy");
    columnData.setAttribute("value", String.valueOf(mlColumn.getView()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "templateView");
    columnData.setAttribute("value", mlColumn.getTemplateView());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "templateEdit");
    columnData.setAttribute("value", mlColumn.getTemplateEdit());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "templateCreate");
    columnData.setAttribute("value", mlColumn.getTemplateCreate());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "inputmask");
    columnData.setAttribute("value", mlColumn.getInputmask());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "defaultSqlValue");
    columnData.setAttribute("value", mlColumn.getDefaultSqlValue());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "virtual");
    columnData.setAttribute("value", String.valueOf(mlColumn.getVirtual()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "longLinkValue");
    columnData.setAttribute("value", mlColumn.getLongLinkValue());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "readOnly");
    columnData.setAttribute("value", String.valueOf(mlColumn.getReadOnly()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "ordered");
    columnData.setAttribute("value", String.valueOf(mlColumn.getOrdered()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "mlClass_order");
    columnData.setAttribute("value", String.valueOf(mlColumn.getMlClass_order()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowCreate");
    columnData.setAttribute("value", String.valueOf(mlColumn.getNotShowCreate()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowDelete");
    columnData.setAttribute("value", String.valueOf(mlColumn.getNotShowDelete()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowChoose");
    columnData.setAttribute("value", String.valueOf(mlColumn.getNotShowChoose()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowEdit");
    columnData.setAttribute("value", String.valueOf(mlColumn.getNotShowEdit()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowCreateInEdit");
    columnData.setAttribute("value", String.valueOf(mlColumn.getNotShowCreateInEdit()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowEditInEdit");
    columnData.setAttribute("value", String.valueOf(mlColumn.getNotShowEditInEdit()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowChooseInEdit");
    columnData.setAttribute("value", String.valueOf(mlColumn.getNotShowChooseInEdit()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowDeleteInEdit");
    columnData.setAttribute("value", String.valueOf(mlColumn.getNotShowDeleteInEdit()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "manyToManyTableName");
    columnData.setAttribute("value", mlColumn.getManyToManyTableName());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "manyToManyFieldNameM");
    columnData.setAttribute("value", mlColumn.getManyToManyFieldNameM());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "manyToManyFieldNameN");
    columnData.setAttribute("value", mlColumn.getManyToManyFieldNameN());
    insertAttr.appendChild(columnData);

    return insertAttr;
  }

}
