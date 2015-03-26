package ru.croacker.lbutil.database.convertor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.metadata.MlAttr;
import ru.croacker.lbutil.database.metadata.MlUnit;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Универсальный конвертор для набора данных
 */
@Service
@Slf4j
public class CommonResultSetConvertor extends ResultSetConvertor{

    private static final class DataType{
        public static final String CHAR = "char";
        public static final String BIGINT = "bigint";
        public static final String BOOLEAN = "boolean";
    }

    public MlUnit toMetadata(ResultSet resultSet, MlUnit unit) {
        try {
            for(int i = 0; i < resultSet.getMetaData().getColumnCount(); i++){
                String columnName = resultSet.getMetaData().getColumnName(i);
                Object columnValue = resultSet.getMetaData().getColumnTypeName(i);
                unit.getColumnValues().put(columnName, columnValue);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return unit;
    }

    public ResultSet toTable(MlAttr metadata) {
        return null;
    }

    private Object getColumnValue(ResultSet resultSet, int i) throws SQLException {
        String type = resultSet.getMetaData().getColumnTypeName(i);
        switch (type){
            case DataType.CHAR:
                return getString(resultSet, resultSet.getMetaData().getColumnName(i));
            case DataType.BIGINT:
                return getLong(resultSet, resultSet.getMetaData().getColumnName(i));
            case DataType.BOOLEAN:
                return getBoolean(resultSet, resultSet.getMetaData().getColumnName(i));
            default:
                return getString(resultSet, resultSet.getMetaData().getColumnName(i));
        }
    }
}
