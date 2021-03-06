package ru.croacker.lbutil.nui.component.ml;

import org.springframework.stereotype.Component;
import ru.croacker.lbutil.nui.component.SelectFolderButton;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;

/**
 * Панель экспорта наборов изменений liquibase для записи данных о Ml классах и атрибутах
 */
@Component
public class ExportMlMetadataLiquibasePanel extends JPanel {

  private GroupLayout jpExportLayout;

  private JLabel jlExportFile;
  private JTextField jtfExportFile;
  private JButton jbSelectExportFile;
  private JButton jbExport;

  public ExportMlMetadataLiquibasePanel(){
  }

  @PostConstruct
  private void initComponents() {
    setBorder(javax.swing.BorderFactory.createEtchedBorder());
    setToolTipText("Экспорт Ml классов");

    jlExportFile = new JLabel("Каталог Ml-классов:");
    jtfExportFile = new JTextField();
    jbSelectExportFile = getSelectFolderButton(jtfExportFile);
    jbSelectExportFile.setToolTipText("Выбор каталога для экспорта файлов наборов изменений ML классов");
    jbExport = new JButton("Экспорт");
    jbExport.setToolTipText("Экспорт ML классов в файлы наборов изменений");

    jpExportLayout = new javax.swing.GroupLayout(this);
    setLayout(jpExportLayout);

    jpExportLayout.setHorizontalGroup(
        jpExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpExportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlExportFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfExportFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbSelectExportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jbExport, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
    );

    jpExportLayout.setVerticalGroup(
        jpExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpExportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlExportFile)
                    .addComponent(jtfExportFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSelectExportFile)
                    .addComponent(jbExport))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
  }

  public String getFileName(){
    return jtfExportFile.getText();
  }

  public void addExportListener(ActionListener actionListener){
    jbExport.addActionListener(actionListener);
  }

  private JButton getSelectFolderButton(JTextComponent filenameVisualizer){
    return new SelectFolderButton(filenameVisualizer);
  }

}

