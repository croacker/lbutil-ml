package ru.croacker.lbutil.nui;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.croacker.lbutil.database.DbConnectionDto;
import ru.croacker.lbutil.nui.component.MainMenuBar;
import ru.croacker.lbutil.nui.component.connection.ConnectionPanel;
import ru.croacker.lbutil.nui.component.connection.ConnectionsListPanel;
import ru.croacker.lbutil.nui.component.liquibase.exp.ExportChangelogPanel;
import ru.croacker.lbutil.nui.component.liquibase.imp.ImportChangelogPanel;
import ru.croacker.lbutil.nui.component.toolbar.MainToolBar;
import ru.croacker.lbutil.service.LiquibaseService;
import ru.croacker.lbutil.service.PersistsService;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author a_gumenyuk
 */
@Component
@Slf4j
public class MainFrm extends JFrame {

  private JPanel jpContent;

  @Autowired
  private LiquibaseService liquibaseService;

  @Autowired
  private PersistsService persistService;

  @Autowired
  @Getter
  @Setter
  private MainMenuBar mainMenuBar;

  @Autowired
  @Getter
  @Setter
  private MainToolBar jtbMain;

  @Autowired
  @Getter
  @Setter
  private ConnectionsListPanel jpConnectionsListPanel;

  @Autowired
  @Getter
  @Setter
  private ConnectionPanel jpConnection;

  @Autowired
  @Getter
  @Setter
  private ImportChangelogPanel jpImport;

  @Autowired
  @Getter
  @Setter
  private ExportChangelogPanel jpExport;

  public MainFrm() {
  }

  @SuppressWarnings("unchecked")
  @PostConstruct
  public void initComponents() {
    setIconImage((new ImageIcon(getClass().getResource("/images/database.png"))).getImage());
    setTitle("UI для утилиты Liquibase");
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setJMenuBar(mainMenuBar);

    jpContent = new javax.swing.JPanel();

    jtbMain.addNewConnectionActionListener(getNewConnectionListener());
    jtbMain.addRemoveConnectionActionListener(getRemoveConnectionListener());
    jpConnectionsListPanel.addNewConnectionMenuListener(getNewConnectionListener());
    jpConnectionsListPanel.addRemoveConnectionMenuListener(getRemoveConnectionListener());
    jpConnectionsListPanel.addListSelectionListener(getConnectionSelectionListener());
    jpConnection.addTestConnectionListener(getTestConnectionActionListener());
    jpConnection.addSaveListener(getSaveActionListener());
    jpImport.addImportListener(getImportButtonListener());
    jpExport.addExportListener(getExportButtonListener());

    javax.swing.GroupLayout jpContentLayout = new javax.swing.GroupLayout(jpContent);
    jpContent.setLayout(jpContentLayout);
    jpContentLayout.setHorizontalGroup(
        jpContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContentLayout.createSequentialGroup()
                .addComponent(jpConnectionsListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpConnection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
    );
    jpContentLayout.setVerticalGroup(
        jpContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpConnectionsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpContentLayout.createSequentialGroup()
                .addComponent(jpConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 99, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtbMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtbMain, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    restoreConnections();
  }

  private ActionListener getTestConnectionActionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        testConnection();
      }
    };
  }

  private ActionListener getImportButtonListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        importChangelog();
      }
    };
  }

  private ActionListener getExportButtonListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        exportChangelog();
      }
    };
  }

  private ActionListener getSaveActionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        saveConfiguration();
      }
    };
  }

  private ListSelectionListener getConnectionSelectionListener(){
    return new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        aplyCurrentConnection();
      }
    };
  }

  private ActionListener getNewConnectionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        newConnection();
      }
    };
  }

  private ActionListener getRemoveConnectionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        removeConnection();
      }
    };
  }

  /**
   * Получить подключения из БД
   */
  private void restoreConnections(){
    jpConnectionsListPanel.setConections(persistService.getAll());
    if(jpConnectionsListPanel.getConnectionsCount() != 0){
      aplyCurrentConnection();
    }
  }

  private void newConnection() {
    jpConnectionsListPanel.clearSelection();
    jpConnection.setConnection(null);
  }

  private void removeConnection() {
    DbConnectionDto connectionDto = jpConnectionsListPanel.getSelected();
    if(connectionDto != null){
      persistService.remove(jpConnectionsListPanel.getSelected());
      jpConnectionsListPanel.removeConnection(connectionDto);
      jpConnection.setConnection(jpConnectionsListPanel.getSelected());
    }
  }

  private void testConnection() {
    JOptionPane.showMessageDialog(null,
        liquibaseService.testConnection(jpConnection.getConnection()));
  }

  private void importChangelog() {
    try {
      liquibaseService.aplyChangelog(jpConnection.getConnection(), jpImport.getFileName());
      JOptionPane.showMessageDialog(this, "Импорт успешно завершен!");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      JOptionPane.showMessageDialog(this, e.getMessage());
    }
  }

  private void exportChangelog() {
    try {
      liquibaseService.createChangelog(jpConnection.getConnection(), jpExport.getFileName());
      JOptionPane.showMessageDialog(this, "Экспорт успешно завершен!");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      JOptionPane.showMessageDialog(null, e.getMessage());
    }
  }

  private void saveConfiguration() {
    DbConnectionDto connectionDto = jpConnection.getConnection();
    boolean isNew = connectionDto.getId() == null;
    connectionDto = persistService.persists(jpConnection.getConnection());
    if(isNew){
      jpConnectionsListPanel.addConnection(connectionDto);
    }
  }

  private void aplyCurrentConnection(){
    jpConnection.setConnection(jpConnectionsListPanel.getSelected());
  }
}
