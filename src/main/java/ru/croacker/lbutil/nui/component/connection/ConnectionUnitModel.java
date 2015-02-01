package ru.croacker.lbutil.nui.component.connection;

import ru.croacker.lbutil.database.DbConnectionDto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель для отображения списка соединений
 */
public class ConnectionUnitModel extends DefaultListModel<DbConnectionDto> {

  private List<DbConnectionDto> connections = new ArrayList<>();

  public List<DbConnectionDto> getConnections() {
    return connections;
  }

  public void setConnections(List<DbConnectionDto> connections) {
    this.connections = connections;
  }

  @Override
  public int getSize() {
    return getConnections().size();
  }

  @Override
  public DbConnectionDto getElementAt(int index) {
    return getConnections().get(index);
  }

  public void addConnection(DbConnectionDto connection){
    getConnections().add(connection);
  }

  public void addConnections(List<DbConnectionDto> connectionDtos) {
    for(DbConnectionDto connectionDto: connectionDtos){
      addConnection(connectionDto);
    }
  }
}
