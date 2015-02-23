package ru.croacker.lbutil.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 *
 */
@Slf4j
@Service
public class FileWriteService {

  public void writeXml(Document document, String fileName) {
    try {
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      Result output = new StreamResult(new File(fileName));
      Source input = new DOMSource(document);
      transformer.transform(input, output);
    } catch (TransformerConfigurationException e) {
      log.error(e.getMessage(), e);
    } catch (TransformerException e) {
      log.error(e.getMessage(), e);
    }
  }

}
