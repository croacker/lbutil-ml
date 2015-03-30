package ru.croacker.lbutil.nui.component;

import com.alee.extended.filechooser.WebDirectoryChooser;
import com.alee.utils.swing.DialogOptions;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Кнопка выбора каталога
 */
public class SelectFolderButton extends JButton {

    private JTextComponent filenameField;

    public SelectFolderButton(JTextComponent filenameField){
        this.filenameField = filenameField;
        initComponents();
    }

    private void initComponents() {
        setText("...");
        addActionListener(getChoosefolderActionListener());
    }

    private ActionListener getChoosefolderActionListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile();
            }
        };
    }

    private void chooseFile(){
        WebDirectoryChooser directoryChooser = new WebDirectoryChooser( null, "Выбор каталога" );
        directoryChooser.setSelectedDirectory(new File(filenameField.getText()));
        directoryChooser.setVisible ( true );
        if ( directoryChooser.getResult () == DialogOptions.OK_OPTION )
        {
            File file = directoryChooser.getSelectedDirectory ();
            filenameField.setText(file.getPath());
        }
    }
}
