package arbeitsprobe;

/**
 * Arbeitsprobe Java Blubbsoft GmbH
 * Klasse für Kategorie-Objekte
 * @author Markus Badzura
 * @since 1.0.001
 */
public class Arbeitsprobe_kategorien 
{
    private String name;
    private String[] antworten, keyword;
    private boolean[] selected;
    /**
     * Standardkonstruktor
     * @author Markus Badzura
     * @since 1.0.001
     */
    Arbeitsprobe_kategorien(){};
    /**
     * Konstruktor ohne XML-Kategorie
     * @param antworten String[] Antworten der Fragen
     * @param keyword  String[] Schlüsselwörter zu den Antworten
     * @author Markus Badzura
     * @since 1.0.001
     */
    Arbeitsprobe_kategorien(String[] antworten, String[] keyword)
    {
        this.name = null;
        this.antworten = antworten;
        this.keyword = keyword;
        setSelected();
    }
    /**
     * Konstruktor mit XML-Kategorie
     * @param name String Name der Kategorie
     * @param antworten String[] Antworten der Fragen
     * @param keyword STring[] Schlüsselwörter zu den Antworten
     * @author Markus Badzura
     * @since 1.0.001
     */
    Arbeitsprobe_kategorien(String name,String[] antworten, String[] keyword)
    {
        this.name = name;
        this.antworten = antworten;
        this.keyword = keyword;
        setSelected();
    }
    /**
     * Selected Array für Kategorie erstellen und mit False füllen
     * @author Markus Badzura
     * @since 1.0.001
     */
    private void setSelected()
    {
        selected = new boolean[antworten.length];
        for (int i = 0; i< antworten.length;i++)
        {
            selected[i] = false;
        }
    }
    /**
     * Übergabe des Selected-Arrays
     * @return selected boolean[]
     * @author Markus Badzura
     * @since 1.0.001
     */
    public boolean[] getSelected()
    {
        return selected;
    }
    /**
     * Übergabe des Kategorienamens
     * @return name String
     * @author Markus Badzura
     * @since 1.0.001
     */
    public String getName()
    {
        return name;
    }
    /**
     * Übergabe des Antworten-Arrays
     * @return antworten String[]
     * @author Markus Badzura
     * @since 1.0.001
     */
    public String[] getAntworten()
    {
        return antworten;
    }
    /**
     * Übergabe des Schlüsselwort-Arrays
     * @return keyword String[]
     * @author Markus Badzura
     * @since 1.0.001
     */
    public String[] getKeyword()
    {
        return keyword;
    }
    /**
     * Speichern der selectierten Antworten
     * @param selected boolean[] - Array 
     * @author Markus Badzura
     * @since 1.0.001
     */
    public void setSelected(boolean[] selected)
    {
        this.selected = selected;
    }
}
