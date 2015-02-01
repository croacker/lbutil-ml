package ru.croacker.lbutil.nui.component;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 */
@Component
public class ConnectionsPopupMenu extends JPopupMenu {

  @Getter
  private JMenuItem jmiNewConnection;

  @Getter
  private JMenuItem jmiRemoveConnection;

  public ConnectionsPopupMenu(){
  }

  @PostConstruct
  private void initComponents() {
    jmiNewConnection = new JMenuItem();
    jmiNewConnection.setText("Добавить");
    jmiNewConnection.setIcon(new ImageIcon(getClass().getResource("/images/addDatabase.png")));
    jmiNewConnection.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });
    add(jmiNewConnection);

    jmiRemoveConnection = new JMenuItem();
    jmiRemoveConnection.setText("Удалить");
    jmiRemoveConnection.setIcon(new ImageIcon(getClass().getResource("/images/removeDatabase.png")));
    add(jmiRemoveConnection);
  }

}
