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

      for (MlTable mlTable: mlDatabase.getTables()){
          Element changeSet = document.createElement("changeSet");
          changeSet.setAttribute("id", "ts_" + String.valueOf(new Date().getTime())); //todo: generate
          changeSet.setAttribute("author", "somebadu"); //todo: generate

          Element tableMetadata = document.createElement("");
          //TODO Добавляем запись в таблицу MlClass
          changeSet.appendChild(tableMetadata);


          for (MlColumn mlColumn: mlTable.getColumns()){
              Element columnMetadata = document.createElement("");
              //TODO Добавляем колонки
              changeSet.appendChild(columnMetadata);
          }



          root.appendChild(changeSet);
      }

  }

}
