package ru.croacker.lbutil.nui.component.connection;

import org.springframework.stereotype.Component;
import ru.croacker.lbutil.database.DbConnectionDto;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 *
 */
@Component
public class ConnectionsList extends JList<DbConnectionDto>{

  public ConnectionsList(){
    super(new DefaultListModel());
  }

  @PostConstruct
  private void initComponents() {
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    setVisibleRowCount(-1);
  }

}
