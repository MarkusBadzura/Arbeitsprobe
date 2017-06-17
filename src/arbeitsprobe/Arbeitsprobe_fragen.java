package arbeitsprobe;

import java.util.ArrayList;

/**
 * Arbeitsprobe Java Blubbsoft GmbH
 * Klasse für die Frageobjekte
 * @author Markus Badzura
 * @since 1.0.001
 */
public class Arbeitsprobe_fragen 
{
    private int fragenummer;
    private String frage, needKeyword;
    private boolean multi;
    private ArrayList kategorien;
    /**
     * Standardkonstruktor
     * @author Markus Badzura
     * @since 1.0.001
     */
    Arbeitsprobe_fragen(){}
    /**
     * Konstruktor für Frageobjekte
     * @param fragenummer int Fragenummer der Frage
     * @param frage String Fragetext
     * @param multi boolean Mehrfachantwort möglich
     * @param needKeyword String benötigtes Schlüsselwort
     * @param kategorien ArrayList mit Kategorie-Objekten
     * @author Markus Badzura
     * @since 1.0.001
     */
    Arbeitsprobe_fragen(int fragenummer, String frage, boolean multi, String needKeyword,
             ArrayList kategorien)
    {
        this.fragenummer = fragenummer;
        this.frage = frage;
        this.multi = multi;
        this.needKeyword = needKeyword;
        this.kategorien = kategorien;
    }
    /**
     * Fragenummer übergeben
     * @return fragennummer int
     * @author Markus Badzura
     * @since 1.0.001
     */
    public int getFragenummer()
    {
        return fragenummer;
    }
    /**
     * Fragetext übergeben
     * @return frage String
     * @author Markus Badzura
     * @since 1.0.001
     */
    public String getFrage()
    {
        return frage;
    }
    /**
     * Mehrfachantwort möglich übergeben
     * @return multi boolean
     * @author Markus Badzura
     * @since 1.0.001
     */
    public boolean getMulti()
    {
        return multi;
    }
    /**
     * Benötigtes Schlüsselwort übergeben
     * @return needKeyword String
     * @author Markus Badzura
     * @since 1.0.001
     */
    public String getNeedKeyword()
    {
        return needKeyword;
    }
    /**
     * Kategorien übergeben
     * @return kategorien ArrayList
     * @author Markus Badzura
     * @since 1.0.001
     */
    public ArrayList getKategorien()
    {
        return kategorien;
    }
}