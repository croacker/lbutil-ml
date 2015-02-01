package ru.croacker.lbutil.nui.component.toolbar;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.ActionListener;

/**
 *
 */
@Component
public class MainToolBar extends JToolBar {

  @Getter
  private JButton jbNewConnection;
  @Getter
  private JButton jbRemoveConnection;

  public MainToolBar(){
  }

  @PostConstruct
  private void initComponents() {
    setRollover(true);
    setFloatable(false);

    jbNewConnection = new JButton(new ImageIcon(getClass().getResource("/images/addDatabase.png")));
    jbNewConnection.setFocusable(false);
    add(jbNewConnection);

    jbRemoveConnection = new JButton(new ImageIcon(getClass().getResource("/images/removeDatabase.png")));
    jbRemoveConnection.setFocusable(false);
    add(jbRemoveConnection);
  }

  public void addNewConnectionActionListener(ActionListener listener){
    jbNewConnection.addActionListener(listener);
  }

  public void addRemoveConnectionActionListener(ActionListener listener){
    jbRemoveConnection.addActionListener(listener);
  }

}
