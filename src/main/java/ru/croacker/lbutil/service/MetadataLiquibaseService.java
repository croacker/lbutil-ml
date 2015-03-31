package ru.croacker.lbutil.service;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.croacker.lbutil.database.metadata.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Сервис экспорта Ml-класов в liquibase
 */
@Service
@Slf4j
public class MetadataLiquibaseService {

    public Map<String, Document> formDocument(MlDatabase mlDatabase, String folder) {
        Map<String, Document> documents = Maps.newHashMap();

        folder = folder.trim();

        documents.put(FilenameUtils.concat(folder, "ml-class.xml"), getMlClassesDocument(mlDatabase));
        documents.put(FilenameUtils.concat(folder, formFileName("AttrGroup")), convertToDocument(mlDatabase.getAttrGroups()));
        documents.put(FilenameUtils.concat(folder, formFileName("AttrView")), convertToDocument(mlDatabase.getAttrViews()));
        documents.put(FilenameUtils.concat(folder, formFileName("ClassAccess")), convertToDocument(mlDatabase.getClassAccesses()));
        documents.put(FilenameUtils.concat(folder, formFileName("ContentBlock")), convertToDocument(mlDatabase.getContentBlocks()));
        documents.put(FilenameUtils.concat(folder, formFileName("Enum")), convertToDocument(mlDatabase.getEnums()));
        documents.put(FilenameUtils.concat(folder, formFileName("Folder")), convertToDocument(mlDatabase.getFolders()));
        documents.put(FilenameUtils.concat(folder, formFileName("FolderAccess")), convertToDocument(mlDatabase.getFolderAccesses()));
        documents.put(FilenameUtils.concat(folder, formFileName("NavigationBlock")), convertToDocument(mlDatabase.getNavigationBlocks()));
        documents.put(FilenameUtils.concat(folder, formFileName("ObjectCreateBlock")), convertToDocument(mlDatabase.getObjectCreateBlocks()));
        documents.put(FilenameUtils.concat(folder, formFileName("ObjectListBlock")), convertToDocument(mlDatabase.getObjectListBlocks()));
        documents.put(FilenameUtils.concat(folder, formFileName("ObjectViewBlock")), convertToDocument(mlDatabase.getObjectViewBlocks()));
        documents.put(FilenameUtils.concat(folder, formFileName("Page")), convertToDocument(mlDatabase.getPages()));
        documents.put(FilenameUtils.concat(folder, formFileName("PageAccess")), convertToDocument(mlDatabase.getPageAccesses()));
        documents.put(FilenameUtils.concat(folder, formFileName("PageBlockBase")), convertToDocument(mlDatabase.getPageBlockBases()));
        documents.put(FilenameUtils.concat(folder, formFileName("Role")), convertToDocument(mlDatabase.getRoles()));
        documents.put(FilenameUtils.concat(folder, formFileName("Scheduled")), convertToDocument(mlDatabase.getScheduleds()));
        documents.put(FilenameUtils.concat(folder, formFileName("SearchBlock")), convertToDocument(mlDatabase.getSearchBlocks()));
        documents.put(FilenameUtils.concat(folder, formFileName("SecurityBlock")), convertToDocument(mlDatabase.getSecurityBlocks()));
        documents.put(FilenameUtils.concat(folder, formFileName("SecurityChangePasswordBlock")), convertToDocument(mlDatabase.getSecurityChangePasswordBlocks()));
        documents.put(FilenameUtils.concat(folder, formFileName("SecuritySettings")), convertToDocument(mlDatabase.getSecuritySettingses()));
        documents.put(FilenameUtils.concat(folder, formFileName("SelectObjectsBlock")), convertToDocument(mlDatabase.getSelectObjectsBlocks()));
        documents.put(FilenameUtils.concat(folder, formFileName("SqlToCsvUtil")), convertToDocument(mlDatabase.getSqlToCsvUtils()));
        documents.put(FilenameUtils.concat(folder, formFileName("StaticPageBlock")), convertToDocument(mlDatabase.getStaticPageBlocks()));
        documents.put(FilenameUtils.concat(folder, formFileName("User")), convertToDocument(mlDatabase.getUsers()));
        documents.put(FilenameUtils.concat(folder, formFileName("Util")), convertToDocument(mlDatabase.getUtils()));
        documents.put(FilenameUtils.concat(folder, formFileName("UtilAccess")), convertToDocument(mlDatabase.getUtilAccesses()));
        documents.put(FilenameUtils.concat(folder, formFileName("UtilsBlock")), convertToDocument(mlDatabase.getUtilsBlocks()));

        //Связи MN
        for(String tableName: mlDatabase.getMnUnits().keySet()){
            String fileName = FilenameUtils.concat(folder, formFileName(tableName));
            Document document = convertToDocument(Lists.newArrayList(mlDatabase.getMnUnits().get(tableName)));
            documents.put(fileName, document);
        }

        return documents;
    }

    private String formFileName(String simpleName) {
        return "ml-" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, simpleName).replace("_", "-") + ".xml";
    }

    /**
     * Файл с Ml классами
     * @param mlDatabase
     * @return
     */
    private Document getMlClassesDocument(MlDatabase mlDatabase) {
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

        for (MlClass mlClass : mlDatabase.getTables()) {
            Long tableId = idForTable(mlDatabase, mlClass);
            Element changeSet = document.createElement("changeSet");
            changeSet.setAttribute("id", "ts_" + String.valueOf(new Date().getTime()) + String.valueOf(tableId));
            changeSet.setAttribute("author", "somebadu");

            changeSet.appendChild(createInsertClass(document, mlClass, tableId));

            for (MlAttr mlAttr : mlClass.getColumns()) {
                changeSet.appendChild(createInsertAttr(document, mlAttr, tableId));
            }
            root.appendChild(changeSet);
        }
        return document;
    }

    /**
     * Конвертировать в XML документ
     * @param units
     * @return
     */
    private Document convertToDocument(List<? extends MlUnit> units){
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

        for(MlUnit unit: units){
            Element changeSet = document.createElement("changeSet");
            changeSet.setAttribute("id", "ts_" + String.valueOf(new Date().getTime()) + String.valueOf(unit.getId()));
            changeSet.setAttribute("author", "somebadu");
            changeSet.appendChild(createInsertUnit(document, unit));

            root.appendChild(changeSet);
        }
        return document;
    }

    private Long idForTable(MlDatabase mlDatabase, MlUnit mlClass) {
        Long id = mlClass.getId();
        if (id == null) {
            id = Long.valueOf(mlDatabase.getTables().indexOf(mlClass));
        }
        return id;
    }

    /**
     * Создать элемент добавления записи в таблицу классов
     *
     * @param document
     * @param mlClass
     * @return
     */
    private Element createInsertClass(Document document, MlClass mlClass, Long tableId) {
        Element insertClass = document.createElement("insert");
        insertClass.setAttribute("tableName", "MlClass");

        Element columnData = document.createElement("column");
        columnData.setAttribute("name", "id");
        columnData.setAttribute("value", String.valueOf(tableId));//TODO Добавить заполнение ID
        insertClass.appendChild(columnData);

        columnData = document.createElement("column");
        columnData.setAttribute("name", "tableName");
        columnData.setAttribute("value", mlClass.getTableName());
        insertClass.appendChild(columnData);

        columnData = document.createElement("column");
        columnData.setAttribute("name", "entityName");
        columnData.setAttribute("value", mlClass.getEntityName());
        insertClass.appendChild(columnData);

        columnData = document.createElement("column");
        columnData.setAttribute("name", "isSystem");
        columnData.setAttribute("value", String.valueOf(mlClass.getIsSystem()));
        insertClass.appendChild(columnData);

        columnData = document.createElement("column");
        columnData.setAttribute("name", "javaClass");
        columnData.setAttribute("value", String.valueOf(mlClass.getJavaClass()));
        insertClass.appendChild(columnData);

        columnData = document.createElement("column");
        columnData.setAttribute("name", "hasHistory");
        columnData.setAttribute("value", String.valueOf(mlClass.getHasHistory()));
        insertClass.appendChild(columnData);

        columnData = document.createElement("column");
        columnData.setAttribute("name", "description");
        columnData.setAttribute("value", mlClass.getDescription());
        insertClass.appendChild(columnData);

        columnData = document.createElement("column");
        columnData.setAttribute("name", "parent");
        columnData.setAttribute("value", String.valueOf(mlClass.getParent()));
        insertClass.appendChild(columnData);

        columnData = document.createElement("column");
        columnData.setAttribute("name", "isAbstract");
        columnData.setAttribute("value", String.valueOf(mlClass.getIsAbstract()));
        insertClass.appendChild(columnData);

        columnData = document.createElement("column");
        columnData.setAttribute("name", "isCacheable");
        columnData.setAttribute("value", String.valueOf(mlClass.getIsCacheable()));
        insertClass.appendChild(columnData);

        columnData = document.createElement("column");
        columnData.setAttribute("name", "titleFormat");
        columnData.setAttribute("value", mlClass.getTitleFormat());
        insertClass.appendChild(columnData);

        columnData = document.createElement("column");
        columnData.setAttribute("name", "handlerClassName");
        columnData.setAttribute("value", String.valueOf(mlClass.getHandlerClassName()));
        insertClass.appendChild(columnData);

        columnData = document.createElement("column");
        columnData.setAttribute("name", "replicationHandlerClassName");
        columnData.setAttribute("value", String.valueOf(mlClass.getReplicationHandlerClassName()));
        insertClass.appendChild(columnData);

        columnData = document.createElement("column");
        columnData.setAttribute("name", "replicationHandlerClassName");
        columnData.setAttribute("value", String.valueOf(mlClass.getReplicationHandlerClassName()));
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
        columnData.setAttribute("name", "id");
        columnData.setAttribute("value", String.valueOf(mlAttr.getId()));
        insertAttr.appendChild(columnData);

        columnData = document.createElement("column");
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
        columnData.setAttribute("name", "view");
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

    /**
     * Создать элемент добавления записи в некоторую таблицу
     * @param document
     * @param mlUnit
     * @return
     */
    private Element createInsertUnit(Document document, MlUnit mlUnit){
        Element insertUnit = document.createElement("insert");
//        insertUnit.setAttribute("tableName", mlUnit.getClass().getSimpleName());
        insertUnit.setAttribute("tableName", mlUnit.getMlClassTableName());
        for(String columnName: mlUnit.getColumnValues().keySet()){
            Object columnValue = mlUnit.getColumnValues().get(columnName);

            Element columnData = document.createElement("column");
            columnData.setAttribute("name", columnName);
            columnData.setAttribute("value", String.valueOf(columnValue));
            insertUnit.appendChild(columnData);
        }
        return insertUnit;
    }
}
