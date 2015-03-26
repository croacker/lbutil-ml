package ru.croacker.lbutil.database.metadata;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MlDatabase {

    @Getter
    private List<MlClass> mlClasses = new ArrayList<>();

    @Getter
    private List<MlClass> tables = new ArrayList<>();

    @Getter
    private boolean isOriginal;

    public MlDatabase(boolean isOriginal) {
        this.isOriginal = isOriginal;
    }

    public void addTable(MlClass mlTable) {
        getTables().add(mlTable);
    }

}
