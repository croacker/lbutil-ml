package ru.croacker.lbutil.nui.component.liquibase.imp;

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
public class ImportChangelogPanel extends JPanel {

  private GroupLayout jpImportLayout;

  private JLabel jlImportFile;
  private JTextField jtfImportFile;
  private JButton jbSelectImportFile;
  private JButton jbImport;

  public ImportChangelogPanel(){
    initComponents();
  }

  @PostConstruct
  private void initComponents() {
    setBorder(javax.swing.BorderFactory.createEtchedBorder());
    setToolTipText("Импорт");

    jlImportFile = new JLabel("Файл импорта схемы БД:");
    jtfImportFile = new JTextField();
    jbSelectImportFile = getSelectFile(jtfImportFile);
    jbSelectImportFile.setToolTipText("Выбор файла содержащего наборы изменений");
    jbImport = new JButton("Импорт");
    jbImport.setToolTipText("Применить файл наборов изменений к БД");

    jpImportLayout = new javax.swing.GroupLayout(this);
    setLayout(jpImportLayout);

    jpImportLayout.setHorizontalGroup(
        jpImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpImportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlImportFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfImportFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbSelectImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jbImport, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
    );

    jpImportLayout.setVerticalGroup(
        jpImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpImportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlImportFile)
                    .addComponent(jtfImportFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSelectImportFile)
                    .addComponent(jbImport))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
  }

  public String getFileName(){
    return jtfImportFile.getText();
  }

  public void addImportListener(ActionListener actionListener){
    jbImport.addActionListener(actionListener);
  }

  private JButton getSelectFile(JTextComponent filenameVisualizer){
    return new SelectFileButton(filenameVisualizer, JFileChooser.OPEN_DIALOG);
  }

}
