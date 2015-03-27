package ru.croacker.lbutil.database.convertor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.metadata.MlAttr;
import ru.croacker.lbutil.database.metadata.MlUnit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Универсальный конвертор для набора данных
 */
@Service
@Slf4j
public class CommonResultSetConvertor extends ResultSetConvertor{

    public <T extends MlUnit> T toMetadata(ResultSet resultSet, Class<T> clazz) {
        T unit = T.newInstance(clazz);
        try {
            for(int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++){
                String columnName = resultSet.getMetaData().getColumnName(i);
                Object columnValue = getColumnValue(resultSet, i);
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
        int type = resultSet.getMetaData().getColumnType(i);
        switch (type){
            case Types.CHAR: case Types.VARCHAR:
                return getString(resultSet, resultSet.getMetaData().getColumnName(i));
            case Types.BIGINT:
                return getLong(resultSet, resultSet.getMetaData().getColumnName(i));
            case Types.BOOLEAN: case Types.BIT:
                return getBoolean(resultSet, resultSet.getMetaData().getColumnName(i));
            case Types.TIMESTAMP:
                return getDate(resultSet, resultSet.getMetaData().getColumnName(i));
            default:
                return getString(resultSet, resultSet.getMetaData().getColumnName(i));
        }
    }
}
