package ru.croacker.lbutil.nui.component.ml;

import org.springframework.stereotype.Component;
import ru.croacker.lbutil.nui.component.SelectFileButton;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;

/**
 * Панель экспорта Ml классов как java-классов
 */
@Component
public class ExportJavaClassesPanel  extends JPanel {

  private GroupLayout jpExportLayout;

  private JLabel jlExportFolder;
  private JTextField jtfExportFolder;
  private JButton jbSelectExportFolder;
  private JButton jbExport;

  public ExportJavaClassesPanel(){
  }

  @PostConstruct
  private void initComponents() {
    setBorder(javax.swing.BorderFactory.createEtchedBorder());
    setToolTipText("Экспорт Java-классов");

    jlExportFolder = new JLabel("Каталог Java-классов:");
    jtfExportFolder = new JTextField();
    jbSelectExportFolder = getSelectFileButton(jtfExportFolder);
    jbSelectExportFolder.setToolTipText("Выбор каталога для экспорта Java-классов");
    jbExport = new JButton("Экспорт");
    jbExport.setToolTipText("Экспорт Java-классов");

    jpExportLayout = new javax.swing.GroupLayout(this);
    setLayout(jpExportLayout);

    jpExportLayout.setHorizontalGroup(
        jpExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpExportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlExportFolder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfExportFolder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbSelectExportFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jbExport, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
    );

    jpExportLayout.setVerticalGroup(
        jpExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpExportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlExportFolder)
                    .addComponent(jtfExportFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSelectExportFolder)
                    .addComponent(jbExport))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
  }

  public String getFolderName(){
    return jtfExportFolder.getText();
  }

  public void addExportListener(ActionListener actionListener){
    jbExport.addActionListener(actionListener);
  }

  private JButton getSelectFileButton(JTextComponent filenameVisualizer){
    return new SelectFileButton(filenameVisualizer, JFileChooser.DIRECTORIES_ONLY);
  }

}

