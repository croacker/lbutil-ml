package ru.croacker.lbutil.database.model;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

/**
 *
 */
public class JavaClassModel {

  private static final String FILE_EXTENSION = ".java";

  @Getter
  private List<JavaClassFieldModel> fields = Lists.newArrayList();

  @Getter
  private String javaClassName;

  @Getter
  private String packageName;

  public String getFileName(){
    return javaClassName + FILE_EXTENSION;
  }

  public JavaClassModel(String fullClassName){
    processName(fullClassName);
  }

  /**
   * Сформировать имя класса и имя пакета
   * @param fullClassName
   */
  private void processName(String fullClassName) {
    String[] parts = fullClassName.split("\\.");
    javaClassName = parts[parts.length - 1];

    parts[parts.length - 1] = null;
    Joiner stringJoiner = Joiner.on(".").skipNulls();
    packageName = stringJoiner.join(parts);
  }

  public void addField(String tabbleFieldName, String type){
    fields.add(new JavaClassFieldModel(tabbleFieldName, type));
  }

}