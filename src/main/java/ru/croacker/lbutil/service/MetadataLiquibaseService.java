package ru.croacker.lbutil.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.croacker.lbutil.database.metadata.MlAttr;
import ru.croacker.lbutil.database.metadata.MlDatabase;
import ru.croacker.lbutil.database.metadata.MlClass;

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

    for (MlClass mlTable : mlDatabase.getTables()) {
      Long tableId = idForTable(mlDatabase, mlTable);
      Element changeSet = document.createElement("changeSet");
      changeSet.setAttribute("id", "ts_" + String.valueOf(new Date().getTime()) + String.valueOf(tableId)); //todo: generate
      changeSet.setAttribute("author", "somebadu"); //todo: generate

      changeSet.appendChild(createInsertClass(document, mlTable, tableId));

      for (MlAttr mlAttr : mlTable.getColumns()) {
        changeSet.appendChild(createInsertAttr(document, mlAttr, tableId));
      }
      root.appendChild(changeSet);
    }

    return document;
  }

  private Long idForTable(MlDatabase mlDatabase, MlClass mlTable) {
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
  private Element createInsertClass(Document document, MlClass mlTable, Long tableId) {
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
   * @param mlAttr
   * @return
   */
  private Element createInsertAttr(Document document, MlAttr mlAttr, Long tableId) {

    Element insertAttr = document.createElement("insert");
    insertAttr.setAttribute("tableName", "MlAttr");

    Element columnData = document.createElement("column");
    columnData.setAttribute("name", "mlClass");
    columnData.setAttribute("value", Long.toString(tableId));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "tableFieldName");
    columnData.setAttribute("value", mlAttr.getTableFieldName());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "entityFieldName");
    columnData.setAttribute("value", mlAttr.getEntityFieldName());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "systemField");
    columnData.setAttribute("value", String.valueOf(mlAttr.getSystemField()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "primaryKey");
    columnData.setAttribute("value", String.valueOf(mlAttr.getPrimaryKey()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "autoIncrement");
    columnData.setAttribute("value", String.valueOf(mlAttr.getAutoIncrement()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "fieldType");
    columnData.setAttribute("value", mlAttr.getFieldType());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "linkAttr");
    columnData.setAttribute("value", String.valueOf(mlAttr.getLinkAttr()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "linkClass");
    columnData.setAttribute("value", String.valueOf(mlAttr.getLinkClass()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "linkFilter");
    columnData.setAttribute("value", mlAttr.getLinkFilter());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "inForm");
    columnData.setAttribute("value", String.valueOf(mlAttr.getInForm()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "inList");
    columnData.setAttribute("value", String.valueOf(mlAttr.getInList()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "useInSimpleSearch");
    columnData.setAttribute("value", String.valueOf(mlAttr.getUseInSimpleSearch()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "useInExtendedSearch");
    columnData.setAttribute("value", String.valueOf(mlAttr.getUseInExtendedSearch()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "fieldFormat");
    columnData.setAttribute("value", mlAttr.getFieldFormat());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "group");
    columnData.setAttribute("value", String.valueOf(mlAttr.getGroup()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "description");
    columnData.setAttribute("value", mlAttr.getDescription());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "viewPos");
    columnData.setAttribute("value", String.valueOf(mlAttr.getViewPos()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "newLine");
    columnData.setAttribute("value", String.valueOf(mlAttr.getNewLine()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "offset");
    columnData.setAttribute("value", String.valueOf(mlAttr.getOffset()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "totalLength");
    columnData.setAttribute("value", String.valueOf(mlAttr.getTotalLength()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "titleLength");
    columnData.setAttribute("value", String.valueOf(mlAttr.getTitleLength()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "tableHeight");
    columnData.setAttribute("value", String.valueOf(mlAttr.getTableHeight()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "mandatory");
    columnData.setAttribute("value", String.valueOf(mlAttr.getMandatory()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "replicationType");
    columnData.setAttribute("value", mlAttr.getReplicationType());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "defaultValue");
    columnData.setAttribute("value", mlAttr.getDefaultValue());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "lazy");
    columnData.setAttribute("value", String.valueOf(mlAttr.getLazy()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "lazy");
    columnData.setAttribute("value", String.valueOf(mlAttr.getView()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "templateView");
    columnData.setAttribute("value", mlAttr.getTemplateView());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "templateEdit");
    columnData.setAttribute("value", mlAttr.getTemplateEdit());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "templateCreate");
    columnData.setAttribute("value", mlAttr.getTemplateCreate());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "inputmask");
    columnData.setAttribute("value", mlAttr.getInputmask());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "defaultSqlValue");
    columnData.setAttribute("value", mlAttr.getDefaultSqlValue());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "virtual");
    columnData.setAttribute("value", String.valueOf(mlAttr.getVirtual()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "longLinkValue");
    columnData.setAttribute("value", mlAttr.getLongLinkValue());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "readOnly");
    columnData.setAttribute("value", String.valueOf(mlAttr.getReadOnly()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "ordered");
    columnData.setAttribute("value", String.valueOf(mlAttr.getOrdered()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "mlClass_order");
    columnData.setAttribute("value", String.valueOf(mlAttr.getMlClass_order()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowCreate");
    columnData.setAttribute("value", String.valueOf(mlAttr.getNotShowCreate()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowDelete");
    columnData.setAttribute("value", String.valueOf(mlAttr.getNotShowDelete()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowChoose");
    columnData.setAttribute("value", String.valueOf(mlAttr.getNotShowChoose()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowEdit");
    columnData.setAttribute("value", String.valueOf(mlAttr.getNotShowEdit()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowCreateInEdit");
    columnData.setAttribute("value", String.valueOf(mlAttr.getNotShowCreateInEdit()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowEditInEdit");
    columnData.setAttribute("value", String.valueOf(mlAttr.getNotShowEditInEdit()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowChooseInEdit");
    columnData.setAttribute("value", String.valueOf(mlAttr.getNotShowChooseInEdit()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "notShowDeleteInEdit");
    columnData.setAttribute("value", String.valueOf(mlAttr.getNotShowDeleteInEdit()));
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "manyToManyTableName");
    columnData.setAttribute("value", mlAttr.getManyToManyTableName());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "manyToManyFieldNameM");
    columnData.setAttribute("value", mlAttr.getManyToManyFieldNameM());
    insertAttr.appendChild(columnData);

    columnData = document.createElement("column");
    columnData.setAttribute("name", "manyToManyFieldNameN");
    columnData.setAttribute("value", mlAttr.getManyToManyFieldNameN());
    insertAttr.appendChild(columnData);

    return insertAttr;
  }

}
