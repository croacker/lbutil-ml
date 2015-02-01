package ru.croacker.lbutil.nui.component.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.croacker.lbutil.database.DbConnectionDto;
import ru.croacker.lbutil.database.JdbcDriver;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Параметры текущего соединения
 */
@Component
public class ConnectionPanel extends JPanel {

  private JLabel jlName;
  private JTextField jtfName;
  @Autowired
  private JdbDriverCombobox jcbJdbcDriver;
  private JLabel jlJdbcDriver;
  private JLabel jlUrl;
  private JTextField jtfUrl;
  private JLabel jlUser;
  private JTextField jtfUser;
  private JLabel jlPassword;
  private JTextField jtfPassword;
  private JButton jbTestConnection;
  private JButton jbSave;
  private DbConnectionDto currentConnection;

  public ConnectionPanel(){
  }

  @PostConstruct
  private void initComponents() {
    setToolTipText("Параметры соединения");
    setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jlName = new JLabel("Наименование:");
    jtfName = new JTextField();

    jlJdbcDriver = new JLabel("JDBC-драйвер:");
    jcbJdbcDriver.addItemListener(getJdbcDriverChangeListener());

    jlUrl = new JLabel("URL:");
    jtfUrl = new JTextField();

    jlUser = new JLabel("Пользователь:");
    jtfUser = new JTextField();

    jlPassword = new JLabel("Пароль:");
    jtfPassword = new JTextField();

    jbTestConnection = new JButton("Проверить");
    jbTestConnection.setToolTipText("Проверить соединение с БД");
    jbTestConnection.setIcon(new ImageIcon(getClass().getResource("/images/check.png")));

    jbSave = new JButton("Сохранить");
    jbSave.setToolTipText("Сохранить конфигурацию соединения с БД");

    GroupLayout jpConnectionLayout = new GroupLayout(this);
    setLayout(jpConnectionLayout);
    jpConnectionLayout.setHorizontalGroup(
        jpConnectionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpConnectionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpConnectionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jpConnectionLayout.createSequentialGroup()
                            .addComponent(jlName)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtfName, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jpConnectionLayout.createSequentialGroup()
                            .addComponent(jlJdbcDriver)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jcbJdbcDriver, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jtfUrl)
                        .addGroup(jpConnectionLayout.createSequentialGroup()
                            .addComponent(jlUrl)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jpConnectionLayout.createSequentialGroup()
                                .addComponent(jlUser)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfUser)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlPassword)
                                .addComponent(jtfPassword)
                        )
                        .addGroup(jpConnectionLayout.createSequentialGroup()
                            .addGap(0, 50, Short.MAX_VALUE)
                            .addComponent(jbTestConnection, GroupLayout.DEFAULT_SIZE, 92, 92)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbSave))
                )
                .addContainerGap())
    );
    jpConnectionLayout.setVerticalGroup(
        jpConnectionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpConnectionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpConnectionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jlName)
                    .addComponent(jtfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpConnectionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jlJdbcDriver)
                    .addComponent(jcbJdbcDriver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlUrl)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpConnectionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, jpConnectionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlUser))
                    .addGroup(GroupLayout.Alignment.TRAILING, jpConnectionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlPassword)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpConnectionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)//
                    .addComponent(jbTestConnection)
                    .addComponent(jbSave))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

  }

  public void addTestConnectionListener(ActionListener actionListener) {
    jbTestConnection.addActionListener(actionListener);
  }

  public void addSaveListener(ActionListener actionListener) {
    jbSave.addActionListener(actionListener);
  }

  private ItemListener getJdbcDriverChangeListener() {
    return new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        setUrlTemplate();
      }
    };
  }

  private void setUrlTemplate() {
    if (StringUtils.isEmpty(jtfUrl.getText())
        || JdbcDriver.isTemplate(jtfUrl.getText())) {
      Object selectedItem = jcbJdbcDriver.getSelectedItem();
      jtfUrl.setText(((JdbcDriver) selectedItem).getUrlTemplate());
    }
  }


  public DbConnectionDto getConnection() {
    if (currentConnection == null) {
      currentConnection = new DbConnectionDto();
    }
    currentConnection.setName(jtfName.getText())
        .setJdbcDriver(jcbJdbcDriver.getDriverName())
        .setUrl(jtfUrl.getText())
        .setUser(jtfUser.getText())
        .setPassword(jtfPassword.getText());

    return currentConnection;
  }

  public void setConnection(DbConnectionDto connection) {
    currentConnection = connection;
    if (currentConnection == null) {
      jtfName.setText("");
      jcbJdbcDriver.setSelectedIndex(0);
      jtfUrl.setText("");
      jtfUser.setText("");
      jtfPassword.setText("");
    } else {
      jtfName.setText(connection.getName());
      jcbJdbcDriver.setDriver(connection.getJdbcDriver());
      jtfUrl.setText(connection.getUrl());
      jtfUser.setText(connection.getUser());
      jtfPassword.setText(connection.getPassword());
    }
  }
}
