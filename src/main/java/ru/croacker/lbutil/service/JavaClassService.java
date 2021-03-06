package ru.croacker.lbutil.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.metadata.MlAttr;
import ru.croacker.lbutil.database.metadata.MlDatabase;
import ru.croacker.lbutil.database.metadata.MlClass;
import ru.croacker.lbutil.database.model.JavaClassModel;

import java.util.List;

/**
 * Сервис формирования Java-классов сущностей
 */
@Slf4j
@Service
public class JavaClassService {

  private static final String DEFAULT_PACKAGE_NAME = "com.mycompany.myapp";

  public List<JavaClassModel> formClasses(MlDatabase mlDatabase){
    List<JavaClassModel> classes = Lists.newArrayList();
    for (MlClass mlClass :mlDatabase.getTables()){
      String fullClassName;
      if(mlDatabase.isOriginal()){
        fullClassName = mlClass.getJavaClass();
      }else {
        fullClassName = DEFAULT_PACKAGE_NAME + "." + mlClass.getEntityName();
      }

      if(!StringUtils.isEmpty(fullClassName)) {
        JavaClassModel javaClassModel = new JavaClassModel(fullClassName);
        for (MlAttr mlAttr : mlClass.getColumns()) {
          javaClassModel.addField(mlAttr.getEntityFieldName(), mlAttr.getFieldTypeName());
        }
        classes.add(javaClassModel);
      }
    }
    return classes;
  }

//  private String getFieldType(MlColumn mlColumn){
//
//  }

}
