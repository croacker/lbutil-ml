package ru.croacker.lbutil.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import ru.croacker.lbutil.database.model.JavaClassFieldModel;
import ru.croacker.lbutil.database.model.JavaClassModel;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

/**
 *
 */
@Slf4j
@Service
public class FileWriteService {

  /**
   * Записать файл наборов изменений liquibse для записи данных в таблицу MlClass
   * @param document
   * @param fileName
   */
  public void writeXml(Document document, String fileName) {
    try {
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      Result output = new StreamResult(new File(fileName));
      Source input = new DOMSource(document);
      transformer.transform(input, output);
    } catch (TransformerConfigurationException e) {
      log.error(e.getMessage(), e);
    } catch (TransformerException e) {
      log.error(e.getMessage(), e);
    }
  }

  /**
   * Записать файлы java-классов в каталог
   * @param classes
   * @param folderName
   */
  public void writeClasses(List<JavaClassModel> classes, String folderName) {
    for (JavaClassModel javaClassModel: classes){
      javaClassModel.getPackageName();
      javaClassModel.getJavaClassName();

      //Записываем внутренний класс с константами
      for(JavaClassFieldModel javaClassFieldModel: javaClassModel.getFields()){

      }

      //Записываем внутренний класс с методами доступа
      for(JavaClassFieldModel javaClassFieldModel: javaClassModel.getFields()){

      }
    }
  }
}
