package ru.croacker.lbutil.database.metadata;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MlDatabase {

    @Getter
    private List<MlClass> tables = new ArrayList<>();

    @Getter
    private List<MlAttrGroup> attrGroups = new ArrayList<>();

    @Getter
    private List<MlAttrView> attrViews = new ArrayList<>();

    @Getter
    private List<MlClassAccess> classAccesses = new ArrayList<>();

    @Getter
    private List<MlContentBlock> contentBlocks = new ArrayList<>();

    @Getter
    private List<MlEnum> enums = new ArrayList<>();

    @Getter
    private List<MlFolder> folders = new ArrayList<>();

    @Getter
    private List<MlFolderAccess> folderAccesses = new ArrayList<>();

    @Getter
    private List<MlNavigationBlock> navigationBlocks = new ArrayList<>();

    @Getter
    private List<MlObjectCreateBlock> objectCreateBlocks = new ArrayList<>();

    @Getter
    private List<MlObjectListBlock> objectListBlocks= new ArrayList<>();

    @Getter
    private List<MlObjectViewBlock> objectViewBlocks = new ArrayList<>();

    @Getter
    private List<MlPage> pages = new ArrayList<>();

    @Getter
    private List<MlPageAccess> pageAccesses = new ArrayList<>();

    @Getter
    private List<MlPageBlockBase> pageBlockBases = new ArrayList<>();

    @Getter
    private List<MlRole> roles = new ArrayList<>();

    @Getter
    private List<MlScheduled> scheduleds = new ArrayList<>();

    @Getter
    private List<MlSearchBlock> searchBlocks = new ArrayList<>();

    @Getter
    private List<MlSecurityBlock> securityBlocks = new ArrayList<>();

    @Getter
    private List<MlSecurityChangePasswordBlock> securityChangePasswordBlocks = new ArrayList<>();

    @Getter
    private List<MlSecuritySettings> securitySettingses = new ArrayList<>();

    @Getter
    private List<MlSelectObjectsBlock> selectObjectsBlocks = new ArrayList<>();

    @Getter
    private List<MlSqlToCsvUtil> sqlToCsvUtils = new ArrayList<>();

    @Getter
    private List<MlStaticPageBlock> staticPageBlocks = new ArrayList<>();

    @Getter
    private List<MlUser> users = new ArrayList<>();

    @Getter
    private List<MlUtil> utils = new ArrayList<>();

    @Getter
    private List<MlUtilAccess> utilAccesses = new ArrayList<>();

    @Getter
    private List<MlUtilsBlock> utilsBlocks = new ArrayList<>();

    @Getter
    private boolean isOriginal;

    public MlDatabase(boolean isOriginal) {
        this.isOriginal = isOriginal;
    }

    public void addTable(MlClass mlClass) {
        getTables().add(mlClass);
    }

}
