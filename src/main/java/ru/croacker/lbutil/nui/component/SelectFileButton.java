package ru.croacker.lbutil.nui.component;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Кнопка выбора файла
 */
public class SelectFileButton extends JButton {

    private JTextComponent filenameField;

    private int dialogType;

    public SelectFileButton(JTextComponent filenameField, int dialogType) {
        this.filenameField = filenameField;
        this.dialogType = dialogType;
        initComponents();
    }

    private void initComponents() {
        setText("...");
        addActionListener(getChoosefileActionListener());
    }

    private ActionListener getChoosefileActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile();
            }
        };
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Укажите файл набора изменений");
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML(*.xml)", "xml"));
        fileChooser.setSelectedFile(new File(filenameField.getText()));
        int rVal = -1;
        if (dialogType == JFileChooser.OPEN_DIALOG) {
            rVal = fileChooser.showOpenDialog(getParent());
        } else if (dialogType == JFileChooser.SAVE_DIALOG) {
            rVal = fileChooser.showSaveDialog(getParent());
        }

        if (rVal == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath();
            fileName += (fileChooser.getSelectedFile().getAbsolutePath().endsWith(".xml") ? "" : ".xml");
            filenameField.setText(fileName);
        }
    }
}
