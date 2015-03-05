package ru.croacker.lbutil;

import com.alee.laf.WebLookAndFeel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.croacker.lbutil.nui.MainFrm;

import javax.swing.*;

/**
 * Created by user on 15.03.14.
 */
@Slf4j
public class LbUtilApp implements Runnable {

  @Autowired
  MainFrm frmMain;

  public void run() {
    frmMain.setLocationRelativeTo(null);
    frmMain.setVisible(true);
  }

  public static void main(String[] args) {
    initLookAndFeel();
    loadContext();
    Runnable application = ContextLoader.getInstance().getContext().getBean(LbUtilApp.class);
    application.run();
  }

  private static void loadContext() {
    ContextLoader.getInstance().load();
  }

  private static void initLookAndFeel() {
//    try {
//      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//    } catch (ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException e) {
//      log.error(e.getMessage(), e);
//    }
      WebLookAndFeel.install();
  }

  public void close() {
    System.exit(0);
  }
}
