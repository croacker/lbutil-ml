package ru.croacker.lbutil.nui.component;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Кнопка выбора файла
 */
public class SelectFileButton extends JButton {

  private JTextComponent filenameField;

  public SelectFileButton(JTextComponent filenameField){
    this.filenameField = filenameField;
    initComponents();
  }

  private void initComponents() {
    setText("...");
    addActionListener(getChoosefileActionListener());
  }

  private ActionListener getChoosefileActionListener(){
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        chooseFile();
      }
    };
  }

  private void chooseFile(){
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Укажите файл набора изменений");
    fileChooser.setFileFilter(new FileNameExtensionFilter("XML(*.xml)", "xml"));
    int rVal = fileChooser.showSaveDialog(getParent());
    if (rVal == JFileChooser.APPROVE_OPTION) {
      String fileName = fileChooser.getSelectedFile().getAbsolutePath()
          + (fileChooser.getSelectedFile().getAbsolutePath().endsWith(".xml") ? "" : ".xml");
      filenameField.setText(fileName);
    }
  }

}
