package arbeitsprobe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Arbeitsprobe Java Blubbsoft GmbH
 * Einlesen der Fragebögen
 * @author Markus Badzura
 * @version 1.0.001
 */
public class Arbeitsprobe_xml 
{
   private ArrayList fragen, kategorien;
   private int fragenummer;
   private String frage, kat_name;
   private boolean multi;
   private String needKeyword;
   private String[] arr_antwort, arr_keyword;
   private boolean success;
   /**
    * XML-Datei einlesen
    * @param xmlFile String Dateipfad
    * @return success Erfolgreich geöffnet
    * @author Markus Badzura
    * @since 1.0.001
    */
   public boolean readFile(String xmlFile)
   {
        success = true;
	SAXBuilder builder = new SAXBuilder();
        try 
        {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("frage"); 
            fragen = new ArrayList();
            for (int i = 0; i < list.size(); i++) 
            {    
                Element node = (Element) list.get(i);
                needKeyword="";
                kat_name = "";
                fragenummer = Integer.parseInt(node.getAttributeValue("nummer"));
                frage = node.getAttributeValue("inhalt");
                if ("no".equals(node.getAttributeValue("multi")))
                {
                    multi = false;
                }
                else
                {
                    multi = true;
                }
                if (!"".equals(node.getAttributeValue("needKeyword")))
                {
                    needKeyword = node.getAttributeValue("needKeyword");
                }
                kategorien = new ArrayList();
                if (!node.getChildren("kategorie").isEmpty())
                {
                    List kategorie = node.getChildren("kategorie"); 
                    for (int j = 0; j< kategorie.size(); j++)
                    {
                        Element node_kat = (Element) kategorie.get(j);
                        kat_name = node_kat.getAttributeValue("name");
                        List antwort = node_kat.getChildren("antwort");
                        arr_antwort = new String[antwort.size()];
                        arr_keyword = new String[antwort.size()];
                        for (int k = 0; k<antwort.size();k++)
                        {
                            Element node_ant = (Element) antwort.get(k);
                            if (!"".equals(node_ant.getAttributeValue("keyword")))
                            {
                                arr_keyword[k] = node_ant.getAttributeValue("keyword");
                            }
                            arr_antwort[k] = node_ant.getText();
                        }
                        kategorien.add(new Arbeitsprobe_kategorien(kat_name,arr_antwort, arr_keyword));
                    }
                }
                else
                {
                    List antwort = node.getChildren("antwort");
                    arr_antwort = new String[antwort.size()];
                    arr_keyword = new String[antwort.size()];               
                    for (int k = 0; k<antwort.size();k++)
                    {
                        Element node_ant = (Element) antwort.get(k);
                        if (!"".equals(node_ant.getAttributeValue("keyword")))
                        {
                            arr_keyword[k] = node_ant.getAttributeValue("keyword");
                        }
                        arr_antwort[k] = node_ant.getText();                  
                    }
                    kategorien.add(new Arbeitsprobe_kategorien(arr_antwort, arr_keyword));
                }
                Arbeitsprobe_fragen f = new Arbeitsprobe_fragen(fragenummer, frage, multi, needKeyword, kategorien);
                fragen.add(f);
            }
        } 
        catch (JDOMException | IOException e) 
        {
            success = false;
        }     
        return success;
   }
   /**
    * Übergabe der Frager
    * @return fragen ArrayList
    * @author Markus Badzura
    * @since 1.0.001
    */
   public ArrayList getFragen()
   {
       return fragen;
   }
}