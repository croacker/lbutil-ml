package ru.croacker.lbutil;

import lombok.Getter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 */
public class ContextLoader {

  private static ContextLoader instance;

  @Getter
  private ClassPathXmlApplicationContext context;

  public static ContextLoader getInstance(){
    if(instance == null){
      instance = new ContextLoader();
    }
    return instance;
  }

  public void load(){
    if(context == null){
      context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
  }

}
