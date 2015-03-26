package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 */
public class MlAttr extends MlUnit {

  @Getter @Setter
  private String entityFieldName;
  @Getter @Setter
  private String tableFieldName;
  @Getter @Setter
  private Boolean systemField;
  @Getter @Setter
  private Boolean primaryKey;
  @Getter @Setter
  private Boolean autoIncrement;
  @Getter @Setter
  private String fieldType;
  @Getter @Setter
  private Long linkAttr;
  @Getter @Setter
  private Long mlClass;
  @Getter @Setter
  private Long linkClass;
  @Getter @Setter
  private String linkFilter;
  @Getter @Setter
  private Boolean inForm;
  @Getter @Setter
  private Boolean inList;
  @Getter @Setter
  private Boolean useInSimpleSearch;
  @Getter @Setter
  private Boolean useInExtendedSearch;
  @Getter @Setter
  private String fieldFormat;
  @Getter @Setter
  private Long group;
  @Getter @Setter
  private String description;
  @Getter @Setter
  private Long viewPos;
  @Getter @Setter
  private Boolean newLine;
  @Getter @Setter
  private Long offset;
  @Getter @Setter
  private Long totalLength;
  @Getter @Setter
  private Long titleLength;
  @Getter @Setter
  private Long tableHeight;
  @Getter @Setter
  private Boolean mandatory;
  @Getter @Setter
  private String replicationType;
  @Getter @Setter
  private String guid;
  @Getter @Setter
  private Date lastChange;
  @Getter @Setter
  private String defaultValue;
  @Getter @Setter
  private Boolean lazy;
  @Getter @Setter
  private Long view;
  @Getter @Setter
  private String templateView;
  @Getter @Setter
  private String templateEdit;
  @Getter @Setter
  private String templateCreate;
  @Getter @Setter
  private String inputmask;
  @Getter @Setter
  private String defaultSqlValue;
  @Getter @Setter
  private Boolean virtual;
  @Getter @Setter
  private String longLinkValue;
  @Getter @Setter
  private Boolean readOnly;
  @Getter @Setter
  private Boolean ordered;
  @Getter @Setter
  private Long mlClass_order;
  @Getter @Setter
  private Boolean notShowCreate;
  @Getter @Setter
  private Boolean notShowDelete;
  @Getter @Setter
  private Boolean notShowChoose;
  @Getter @Setter
  private Boolean notShowEdit;
  @Getter @Setter
  private Boolean notShowCreateInEdit;
  @Getter @Setter
  private Boolean notShowEditInEdit;
  @Getter @Setter
  private Boolean notShowChooseInEdit;
  @Getter @Setter
  private Boolean notShowDeleteInEdit;
  @Getter @Setter
  private String manyToManyTableName;
  @Getter @Setter
  private String manyToManyFieldNameM;
  @Getter @Setter
  private String manyToManyFieldNameN;

  //Строковое представление имени типа (String, Long и.т.д.)
  @Getter @Setter
  private String fieldTypeName;

}
