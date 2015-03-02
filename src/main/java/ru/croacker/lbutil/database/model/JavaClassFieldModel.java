package ru.croacker.lbutil.database.model;

import com.google.common.base.CaseFormat;
import lombok.Getter;

/**
 * Модель поля класса
 */
public class JavaClassFieldModel {

  @Getter
  private String tabbleFieldName;
  @Getter
  private String constantName;
  @Getter
  private String typeName;
  //Имя для формирования акцессоров
  private String accessorName;

  public JavaClassFieldModel(String tabbleFieldName, String typeName){
    this.tabbleFieldName = tabbleFieldName;
    this.typeName = typeName;
    init();
  }

  private void init() {
    CaseFormat caseFormat = getCaseFormat();
    constantName = caseFormat.to(CaseFormat.UPPER_UNDERSCORE, tabbleFieldName);
    accessorName = caseFormat.to(CaseFormat.LOWER_CAMEL, tabbleFieldName);
  }

  private CaseFormat getCaseFormat(){
    CaseFormat caseFormat;
    String tmp = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tabbleFieldName);
    if(tmp.toLowerCase().equals(tabbleFieldName.toLowerCase())){
      caseFormat = CaseFormat.LOWER_CAMEL;
    }else {
      caseFormat = CaseFormat.LOWER_UNDERSCORE;
    }
    return caseFormat;
  }

}
