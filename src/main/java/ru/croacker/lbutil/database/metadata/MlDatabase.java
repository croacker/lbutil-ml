package ru.croacker.lbutil.database.metadata;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class MlDatabase {

    @Getter
    private List<MlClass> tables = Lists.newArrayList();

    @Getter
    private List<MlAttrGroup> attrGroups = Lists.newArrayList();

    @Getter
    private List<MlAttrView> attrViews = Lists.newArrayList();

    @Getter
    private List<MlClassAccess> classAccesses = Lists.newArrayList();

    @Getter
    private List<MlContentBlock> contentBlocks = Lists.newArrayList();

    @Getter
    private List<MlEnum> enums = Lists.newArrayList();

    @Getter
    private List<MlFolder> folders = Lists.newArrayList();

    @Getter
    private List<MlFolderAccess> folderAccesses = Lists.newArrayList();

    @Getter
    private List<MlNavigationBlock> navigationBlocks = Lists.newArrayList();

    @Getter
    private List<MlObjectCreateBlock> objectCreateBlocks = Lists.newArrayList();

    @Getter
    private List<MlObjectListBlock> objectListBlocks= Lists.newArrayList();

    @Getter
    private List<MlObjectViewBlock> objectViewBlocks = Lists.newArrayList();

    @Getter
    private List<MlPage> pages = Lists.newArrayList();

    @Getter
    private List<MlPageAccess> pageAccesses = Lists.newArrayList();

    @Getter
    private List<MlPageBlockBase> pageBlockBases = Lists.newArrayList();

    @Getter
    private List<MlRole> roles = Lists.newArrayList();

    @Getter
    private List<MlScheduled> scheduleds = Lists.newArrayList();

    @Getter
    private List<MlSearchBlock> searchBlocks = Lists.newArrayList();

    @Getter
    private List<MlSecurityBlock> securityBlocks = Lists.newArrayList();

    @Getter
    private List<MlSecurityChangePasswordBlock> securityChangePasswordBlocks = Lists.newArrayList();

    @Getter
    private List<MlSecuritySettings> securitySettingses = Lists.newArrayList();

    @Getter
    private List<MlSelectObjectsBlock> selectObjectsBlocks = Lists.newArrayList();

    @Getter
    private List<MlSqlToCsvUtil> sqlToCsvUtils = Lists.newArrayList();

    @Getter
    private List<MlStaticPageBlock> staticPageBlocks = Lists.newArrayList();

    @Getter
    private List<MlUser> users = Lists.newArrayList();

    @Getter
    private List<MlUtil> utils = Lists.newArrayList();

    @Getter
    private List<MlUtilAccess> utilAccesses = Lists.newArrayList();

    @Getter
    private List<MlUtilsBlock> utilsBlocks = Lists.newArrayList();

    @Getter
    private Multimap<String, CommonMlUnit> mnUnits = ArrayListMultimap.create();

    @Getter
    private boolean isOriginal;

    public MlDatabase(boolean isOriginal) {
        this.isOriginal = isOriginal;
    }

    public void addTable(MlClass mlClass) {
        getTables().add(mlClass);
    }

}
