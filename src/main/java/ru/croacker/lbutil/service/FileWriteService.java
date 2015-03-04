package ru.croacker.lbutil.service;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import ru.croacker.lbutil.database.model.JavaClassFieldModel;
import ru.croacker.lbutil.database.model.JavaClassModel;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
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
      StringBuilder fileContent = new StringBuilder();

      fileContent.append("package " + javaClassModel.getPackageName() + ";").append("\r\n")
      .append("\r\n")
      .append("import org.eclipse.persistence.internal.dynamic.DynamicPropertiesManager;").append("\r\n")
      .append("import ru.peak.ml.core.model.MlDynamicEntityImpl;\r\n")
      .append("\r\n")
      .append("public class " + javaClassModel.getJavaClassName() + " extends MlDynamicEntityImpl {").append("\r\n")
      .append("\r\n")
      .append("public static DynamicPropertiesManager DPM = new DynamicPropertiesManager();\r\n")
      .append("\r\n")
      .append("protected static class Properties {").append("\r\n");

      //Записываем внутренний класс с константами
      for(JavaClassFieldModel javaClassFieldModel: javaClassModel.getFields()){
        fileContent.append("public static final String "
            + javaClassFieldModel.getConstantName() + " = \"" + javaClassFieldModel.getTabbleFieldName() + "\";").append("\r\n");
      }
      fileContent.append("}").append("\r\n");

      //Записываем методамы доступа
      for(JavaClassFieldModel javaClassFieldModel: javaClassModel.getFields()){//TODO Убрать конкатенацию на аппенд
        fileContent.append("public " + javaClassFieldModel.getTypeName()
            + " get" + javaClassFieldModel.getAccessorName() + "(){\r\n"
            + " return get(Properties." + javaClassFieldModel.getConstantName() + ");\r\n"
            + "}\r\n");

        fileContent.append("public void" + " set" + javaClassFieldModel.getAccessorName() + "("
            + javaClassFieldModel.getTypeName() + " value){\r\n"
            + " set(Properties." + javaClassFieldModel.getConstantName()
            + ", value);\r\n"
            + "}\r\n");
      }
      fileContent.append("\r\n").append("}").append("\r\n");

      String fileName = FilenameUtils.concat(folderName, javaClassModel.getFileName());
      File file = new File(fileName);

      try {
        Files.write(fileContent, file, Charsets.UTF_8);
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    }
  }
}
