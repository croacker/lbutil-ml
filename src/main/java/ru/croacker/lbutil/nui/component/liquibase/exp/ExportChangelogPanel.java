package ru.croacker.lbutil.nui.component.liquibase.exp;

import org.springframework.stereotype.Component;
import ru.croacker.lbutil.nui.component.SelectFileButton;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;

/**
 *
 */
@Component
public class ExportChangelogPanel extends JPanel {

  private GroupLayout jpExportLayout;

  private JLabel jlExportFile;
  private JTextField jtfExportFile;
  private JButton jbSelectExportFile;
  private JButton jbExport;

  public ExportChangelogPanel(){
  }

  @PostConstruct
  private void initComponents() {
    setBorder(javax.swing.BorderFactory.createEtchedBorder());
    setToolTipText("Экспорт");

    jlExportFile = new JLabel("Файл экспорта схемы БД:");
    jtfExportFile = new JTextField();
    jbSelectExportFile = getSelectFileButton(jtfExportFile);
    jbSelectExportFile.setToolTipText("Выбор файла для экспорта наборов изменений");
    jbExport = new JButton("Экспорт");
    jbExport.setToolTipText("Экспорт схемы БД в файл наборов изменений");

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

  private JButton getSelectFileButton(JTextComponent filenameVisualizer){
    return new SelectFileButton(filenameVisualizer, JFileChooser.SAVE_DIALOG);
  }

}
