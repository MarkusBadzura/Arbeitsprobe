package arbeitsprobe;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Arbeitsprobe Java Blubbsoft GmbH
 * Grafische Benutzeroberfläche Fragebögen
 * @author Markus Badzura
 * @version 1.0.001
 */
public class Arbeitsprobe_gui extends JFrame implements ActionListener
{
    private static final Dimension SCREENSIZE = java.awt.Toolkit.getDefaultToolkit().getScreenSize ();  
    private Arbeitsprobe_xml ap_xml = new Arbeitsprobe_xml();
    private boolean success;
    private JSplitPane jsp;
    private JPanel jp_bottom, jp_head, jp_finish;
    private JScrollPane jsp_finish;
    private JLabel jl_frage, jl_katName;
    private JButton bt_next, bt_back;
    private Arbeitsprobe_fragen af = new Arbeitsprobe_fragen();
    private Arbeitsprobe_kategorien ak = new Arbeitsprobe_kategorien();
    private Arbeitsprobe_kategorien ak_te = new Arbeitsprobe_kategorien();
    private ArrayList fragen, kategorien, multiCheck, multiRadio, keywordAktuell
            ,kategorien_te;
    private int aktuelleFragenummer = -1;
    private String katName;
    private String[] katAntwort, katKeyword,katKeyword_te;
    private JCheckBox[] arr_Checkbox;
    private JRadioButton[] arr_Radiobutton;
    private boolean[] answer;
    private boolean finish;
    /**
     * Setzen des JFrame 
     * @author Markus Badzura
     * @since 1.0.001
     */
    public void Arbeitsprobe_gui()
    {
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {}
        this.setTitle("Arbeitsprobe Fragebögen v1.0");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                exit();
            }
        });          
        this.setSize(SCREENSIZE);
        this.setMinimumSize(SCREENSIZE);
        setVisible(true);
        selectFragebogen();
    }
     /**
     * Abfragedialog beim Beenden des Programmes, inclusive des Schließens
     * über ALT + F4 und dem Schließbutton über die Titelleiste.
     * @author Markus Badzura
     * @since 1.0.001
     */
    private void exit()
    {
        int result = JOptionPane.showConfirmDialog(null, "Möchten Sie wirklich beenden?",
                "Programm beenden", JOptionPane.YES_NO_OPTION);
        switch (result)
        {
            case JOptionPane.YES_OPTION:
                System.exit(0);
        }
    }       
    /**
     * Setzen des Fragepanels
     * @author Markus Badzura
     * @since 1.0.001
     */
    private void setFragepanel()
    {
        fragen = ap_xml.getFragen();
        keywordAktuell = new ArrayList();
        jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        jsp.setSize(this.getSize());
        this.add(jsp);
        jp_bottom = new JPanel();
        jp_bottom.setLayout(null);       
        jp_head = new JPanel();
        jp_head.setLayout(new FlowLayout());
        jsp.setLeftComponent(jp_head);
        jsp.setRightComponent(jp_bottom);
        jsp.setDividerLocation(0.8);
        jsp.setDividerSize(0);
        bt_back = new JButton("zurück");
        bt_next = new JButton("nächste Frage");
        bt_back.setVisible(false);
        bt_back.addActionListener(this);
        bt_next.addActionListener(this);
        bt_next.setBounds(SCREENSIZE.width-250,10,200,25);
        bt_back.setBounds(50,10,200,25);
        jp_bottom.add(bt_next);
        jp_bottom.add(bt_back);
        finish = false;
        frageAnzeigen();
    }
    /**
     * gegebene Antworten anzeigen
     * @author Markus Badzura
     * @version 1.0.001
     */
    private void zeigeAntworten()
    {
        jp_finish = new JPanel();
        jp_finish.setLayout(new FlowLayout());
        jsp_finish = new JScrollPane(jp_finish,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.removeAll();
        jsp.setLeftComponent(jp_finish);
        finish = true;
        for (int i = 0; i<fragen.size();i++)
        {
            aktuelleFragenummer = i;
            Arbeitsprobe_fragen tempFrage = (Arbeitsprobe_fragen) fragen.get(aktuelleFragenummer);   
            String kw = tempFrage.getNeedKeyword();            
            if ("".equals(kw))
            {
                frageAnzeigen();
            }
            else
            {
                if (keywordAktuell.isEmpty())
                {
                    i++;
                }
                else
                {
                    for (int k = 0; k<keywordAktuell.size(); k++)
                    {
                        if (keywordAktuell.get(k).toString().equals(kw))
                        {
                            frageAnzeigen();
                        }
                    }
                }
            }
        }
        this.setVisible(true);
        this.repaint();
        danke();
    }
    /**
     * Abschlussdialog
     * @author Markus Badzura
     * @since 1.0.001
     */
    private void danke()
    {
        int result = JOptionPane.showConfirmDialog(null, "Danke dass Sie sich die Zeit genommen haben.",
                "Fragebogen Ende", JOptionPane.CANCEL_OPTION);
        switch (result)
        {
            default:
                System.exit(0);
        }        
    }
    /**
     * Anzeige der Fragen
     * @author Markus Badzura
     * @since 1.0.001
     */
    private void frageAnzeigen()
    {
        jl_frage = new JLabel("",JLabel.CENTER);
        jl_frage.setPreferredSize(new Dimension(SCREENSIZE.width,25));
        jl_frage.setFont(jl_frage.getFont().deriveFont(20f));
        jl_frage.setFont(jl_frage.getFont().deriveFont(Font.BOLD));
        if (finish) 
        {
            jp_finish.add(jl_frage);
        }
        else 
        {
            jp_head.add(jl_frage);
        
            if (aktuelleFragenummer == -1) 
            {
                bt_next.setText("Umfrage starten");
                bt_back.setEnabled(false);
            }
            if (aktuelleFragenummer == 0) 
            {
                bt_back.setEnabled(false);
                bt_next.setText("Nächste Frage");
                bt_back.setVisible(true);
            }
            if (aktuelleFragenummer > 0) bt_back.setEnabled(true);
            if (aktuelleFragenummer ==  fragen.size()) 
            {
                zeigeAntworten();
            }
            else 
            {
                setEnabled(true);
            }
        }
        if (aktuelleFragenummer != -1)
        {
            af = (Arbeitsprobe_fragen) fragen.get(aktuelleFragenummer);
            jl_frage.setText(af.getFrage());
            antwortenAnzeigen(af.getMulti());
        }
    }
    /**
     * Anzeigen der Antwortmöglichkeiten
     * @param multi boolean Mehrfachantworten möglich
     * @author Markus Badzura
     * @version 1.0.001
     */
    private void antwortenAnzeigen(boolean multi)
    {
        kategorien = af.getKategorien();
        if (kategorien.size() == 1)
        {
            ak = (Arbeitsprobe_kategorien) kategorien.get(0);
            katAntwort = ak.getAntworten();
            katKeyword = ak.getKeyword(); 
            answer = ak.getSelected();
            ButtonGroup antwort = new ButtonGroup();
            arr_Checkbox = new JCheckBox[katAntwort.length];
            arr_Radiobutton = new JRadioButton[katAntwort.length];
            for (int i = 0;i<katAntwort.length; i++)
            {
                if (multi)
                {
                    JCheckBox x = new JCheckBox();
                    x.setSize(100,20);
                    x.setText(katAntwort[i]);
                    if (finish) jp_finish.add(x);
                    else jp_head.add(x);
                    arr_Checkbox[i] = x;
                }
                else
                {
                    JRadioButton x = new JRadioButton();
                    x.setSize(100,20);
                    x.addActionListener(this);
                    x.setText(katAntwort[i]);
                    antwort.add(x);
                    arr_Radiobutton[i] = x;
                    if (finish) jp_finish.add(x);
                    else jp_head.add(x);
                }
            }
            for (int i = 0;i<answer.length;i++)
            {
                if (multi)
                {
                    arr_Checkbox[i].setSelected(answer[i]);
                }
                else
                {
                    arr_Radiobutton[i].setSelected(answer[i]);
                }
            }
        }
        else
        {
            multiCheck = new ArrayList();
            multiRadio = new ArrayList();
            for (int k = 0; k<kategorien.size();k++)
            {
                ak = (Arbeitsprobe_kategorien) kategorien.get(k);
                katAntwort = ak.getAntworten();
                katKeyword = ak.getKeyword();            
                katName = ak.getName();                   
                jl_katName = new JLabel(katName,JLabel.CENTER);
                jl_katName.setPreferredSize(new Dimension(SCREENSIZE.width,25));
                if (finish) jp_finish.add(jl_katName);
                else jp_head.add(jl_katName);
                ButtonGroup antwort = new ButtonGroup();
                arr_Checkbox = new JCheckBox[katAntwort.length];
                arr_Radiobutton = new JRadioButton[katAntwort.length];
                answer = ak.getSelected();
                for (int i = 0;i<katAntwort.length; i++)
                {
                    if (multi)
                    {
                        JCheckBox x = new JCheckBox();
                        x.setSize(100,20);
                        x.setText(katAntwort[i]);
                        if (finish) jp_finish.add(x);
                        else jp_head.add(x);
                        arr_Checkbox[i] = x;
                    }
                    else
                    {
                        JRadioButton x = new JRadioButton();
                        x.setSize(100,20);
                        if (finish) jp_finish.add(x);
                        else jp_head.add(x);
                        x.setText(katAntwort[i]);
                        antwort.add(x);
                        arr_Radiobutton[i] = x;
                    }
                }    
                multiCheck.add(arr_Checkbox);
                multiRadio.add(arr_Radiobutton);
                for (int i = 0;i<answer.length;i++)
                {
                    if (multi)
                    {
                        arr_Checkbox[i].setSelected(answer[i]);
                    }
                    else
                    {
                        arr_Radiobutton[i].setSelected(answer[i]);
                    }
                }                
            }
        }
        if (finish) jp_finish.repaint();
        else jp_head.repaint();
    }
    /**
     * Setzen der Keyworder
     * @param keyword String Keyword
     * @author Markus Badzura
     * @since 1.0.001
     */
    private void addKeyword(String keyword)
    {
        boolean add = true;
        for (int i = 0; i<keywordAktuell.size();i++)
        {
            if (keyword.equals(keywordAktuell.get(i).toString()))
            {
                add = false;
            }
        }
        if (add)
        {
            keywordAktuell.add(keyword);
        }
    }
    /**
     * Prüfung, ob Antwort abgegeben wurde
     * @return pruefung boolean-Wert
     * @author Markus Badzura
     * @since 1.0.001
     */
    private boolean pruefeAntwort()
    {   
        boolean pruefung = true;
        ArrayList schalter = new ArrayList();
        String fehler = "";
        if (kategorien.size()==1)
        {
            int anzahl = 0;
            if(af.getMulti())
            {
                answer = new boolean[arr_Checkbox.length];
                for (int i = 0; i<arr_Checkbox.length;i++)
                {
                    if (arr_Checkbox[i].isSelected())
                    {
                        anzahl++;
                        answer[i] = true;
                        if (katKeyword[i] != null)
                        {
                            addKeyword(katKeyword[i]);
                            schalter.add(katKeyword[i]);
                        }                        
                    }
                    else
                    {
                        answer[i] = false;
                        if (katKeyword[i] != null)
                        {
                           for (int j = 0;j<keywordAktuell.size();j++) 
                           {
                               String temp = katKeyword[i];
                               if (temp.equals(keywordAktuell.get(j).toString()))
                               {
                                   boolean schalterTemp = true;
                                   for (int k = 0;k<schalter.size();k++)
                                   {
                                        if (temp.equals(schalter.get(k).toString()))
                                        {
                                            schalterTemp = false;
                                        }
                                   }
                                   if (schalterTemp)
                                   {
                                       keywordAktuell.remove(temp);
                                       clearKeyword(temp);
                                   }
                               }
                           }
                        }
                    }
                }
            ak.setSelected(answer);
            }
            else
            {
                for (int i = 0; i<arr_Radiobutton.length;i++)
                {
                    if (arr_Radiobutton[i].isSelected())
                    {
                        anzahl++;
                        answer[i] = true;
                        if (katKeyword[i] != null)
                        {
                            addKeyword(katKeyword[i]);
                        }        
                    }
                    else
                    {
                        answer[i] = false;
                        if (katKeyword[i] != null)
                        {
                           for (int j = 0;j<keywordAktuell.size();j++) 
                           {
                               String temp = katKeyword[i];
                               if (temp.equals(keywordAktuell.get(j).toString()))
                               {
                                   keywordAktuell.remove(temp);
                                   clearKeyword(temp);
                               }
                           }
                        }
                    }                    
                    
                }  
                ak.setSelected(answer);
            }
            if (anzahl==0)
            {
                fehler = "Sie haben keine Auswahl getroffen.";
                pruefung = false;
            }
        }
        else
        {
            JCheckBox[] tempCheck;
            JRadioButton[] tempRadio;
            for (int i = 0; i<kategorien.size();i++)
            {
                int anzahl = 0;
                ak = (Arbeitsprobe_kategorien) kategorien.get(i);
                tempCheck = (JCheckBox[]) multiCheck.get(i);
                tempRadio = (JRadioButton[]) multiRadio.get(i);
                if(af.getMulti())
                {
                    answer = new boolean[tempCheck.length];
                    for(int j = 0;j<tempCheck.length;j++)
                    {
                        if (tempCheck[j].isSelected())
                        {
                            anzahl++;
                            answer[j] = true;
                            if (katKeyword[j] != null)
                            {
                                addKeyword(katKeyword[j]);
                            }                            
                        }
                        else
                        {
                            answer[j] = false;
                            if (katKeyword[j] != null)
                            {
                                for (int k = 0;k<keywordAktuell.size();k++) 
                                {
                                    String temp = katKeyword[j];
                                    if (temp.equals(keywordAktuell.get(k).toString()))
                                    {
                                        keywordAktuell.remove(temp);
                                        clearKeyword(temp);
                                    }
                                }
                            }
                        }                                            
                    }                    
                    if (anzahl==0)
                    {
                        fehler = "Sie haben keine Auswahl getroffen.";
                        pruefung = false;
                    }
                    ak.setSelected(answer);
                }
                else
                {
                    answer = new boolean[tempRadio.length];
                    for(int j = 0;j<tempRadio.length;j++)
                    {
                        if (tempRadio[j].isSelected())
                        {
                            answer[j] = true;
                            anzahl++;
                            if (katKeyword[j] != null)
                            {
                                addKeyword(katKeyword[j]);
                            }                            
                        }
                        else
                        {
                            answer[j] = false;
                            if (katKeyword[j] != null)
                            {
                                for (int k = 0;k<keywordAktuell.size();k++) 
                                {
                                    String temp = katKeyword[j];
                                    if (temp.equals(keywordAktuell.get(k).toString()))
                                    {
                                        keywordAktuell.remove(temp);
                                        clearKeyword(temp);
                                    }
                                }
                            }
                        }                         
                    }                   
                    if (anzahl==0)
                    {
                        fehler = "Sie haben keine Auswahl getroffen.";
                        pruefung = false;
                    }
                }
                ak.setSelected(answer);
            } 
        }
        if (!pruefung)
        {
            JOptionPane.showConfirmDialog(null, fehler,
                "Fehlende Eingabe", JOptionPane.OK_OPTION);
        }
        return pruefung;
    }
    /**
     * Keyword löschen bei Antwortänderung
     * @param deletedKeyword String Keyword, welches gelöscht wurde
     */
    private void clearKeyword(String deletedKeyword)
    {
        if (!keywordAktuell.isEmpty())
        {        
            int z = aktuelleFragenummer;
            for (++z;z<fragen.size();z++)
            {
                Arbeitsprobe_fragen tempfrage; 
                tempfrage = (Arbeitsprobe_fragen) fragen.get(z);
                if (tempfrage.getNeedKeyword().equals(deletedKeyword))   
                {               
                    kategorien_te = tempfrage.getKategorien();
                    if (kategorien_te.size() == 1)
                    {
                        ak_te = (Arbeitsprobe_kategorien) kategorien_te.get(0);
                        katKeyword_te = ak_te.getKeyword(); 
                        for (int i = 0;i<katKeyword_te.length; i++)
                        {
                            if (tempfrage.getMulti())
                            {
                                if (katKeyword_te[i] != null )
                                {
                                    for (int y = 0; y<keywordAktuell.size();y++)
                                    {
                                        if (katKeyword_te[i].equals(keywordAktuell.get(y).toString()))
                                        {
                                            keywordAktuell.remove(katKeyword_te[i]);
                                        }
                                    }
                                }

                            }
                            else
                            {
                                if (katKeyword_te[i] != null )
                                {
                                    for (int y = 0; y<keywordAktuell.size();y++)
                                    {
                                        if (katKeyword_te[i].equals(keywordAktuell.get(y).toString()))
                                        {
                                            keywordAktuell.remove(katKeyword_te[i]);
                                        }
                                    }
                                }                    
                            }
                        }
                    }
                    else
                    {
                        for (int k = 0; k<kategorien_te.size();k++)
                        {
                            ak_te = (Arbeitsprobe_kategorien) kategorien_te.get(k);
                            katKeyword_te = ak_te.getKeyword();            
                            for (int i = 0;i<katKeyword_te.length; i++)
                            {
                                if (tempfrage.getMulti())
                                {
                                    if (katKeyword_te[i] != null )
                                    {
                                        for (int y = 1; y<keywordAktuell.size();y++)
                                        {
                                            if (katKeyword_te[i].equals(keywordAktuell.get(y).toString()))
                                            {
                                                keywordAktuell.remove(katKeyword_te[i]);
                                            }
                                        }
                                    }                    
                                }
                                else
                                {
                                    if (katKeyword_te[i] != null )
                                    {
                                        for (int y = 1; y<keywordAktuell.size();y++)
                                        {
                                            if (katKeyword_te[i].equals(keywordAktuell.get(y).toString()))
                                            {
                                                keywordAktuell.remove(katKeyword_te[i]);
                                            }
                                        }
                                    }                    
                                }
                            }    
                        }
                    }    
                }
            }
        }
    }
    /**
     * JFileChooser um XML-Datei zu finden und zu öffnen
     * @author Markus Badzura
     * @since 1.0.001
     */
    private void selectFragebogen()
    {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Fragebogendateien (*.xml)", "xml"));
        int auswahl = fc.showOpenDialog(null);
        if (auswahl == JFileChooser.APPROVE_OPTION) 
            success = ap_xml.readFile(fc.getSelectedFile().toString());
        else
        {
           int result = JOptionPane.showConfirmDialog(null, "Sie haben die Dateiauswahl abgebrochen. "
                   + "Wollen Sie erneut eine Datei auswählen?", "Abbruch", JOptionPane.YES_NO_OPTION); 
           dialogErgebnis(result);
        }
        fc.approveSelection();
        if (success)
        {
            setFragepanel();
        }
        else
        {
            int result = JOptionPane.showConfirmDialog(null, "Es trat ein Fehler beim Einlesen des Fragebogens auf?"
                    + " Wollen Sie es erneut versuchen?", "XML-Fehler", JOptionPane.YES_NO_OPTION);
            dialogErgebnis(result);
        }        
    }
    /**
     * JOptionPane Ergebnisaktion
     * @param result int Gedrückter Button JOptionPane
     * @author Markus Badzura
     * @since 1.0.001
     */
    private void dialogErgebnis(int result)
    {
        switch (result)
        {
        case JOptionPane.YES_OPTION:
            selectFragebogen();
            break;
        default:
            System.exit(0);
        }        
    }
    /**
     * ActionListener
     * @param e JButton Auslösender Button
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // Button Zurück
        if (e.getSource() == bt_back)
        {
            boolean back = false;
            Arbeitsprobe_fragen tempFrage;
            String kw;     
            if (pruefeAntwort())
            {       
                do
                {
                    if ((aktuelleFragenummer ) == 0) 
                    {
                        break;
                    }
                    --aktuelleFragenummer;
                    tempFrage = (Arbeitsprobe_fragen) fragen.get(aktuelleFragenummer);   
                    kw = tempFrage.getNeedKeyword();
                    if ("".equals(kw)) 
                    {
                        back = true;
                    }
                    else
                    {
                        for (int i = 0; i<keywordAktuell.size();i++)
                        {
                            String temp = keywordAktuell.get(i).toString();
                            if (temp.equals(kw))
                            {
                                back = true;
                            }
                        }
                    }
                }
                while(!back);
                jp_head.removeAll();
                jp_head.repaint();
                frageAnzeigen();                
            }                        
        }
        // Button Nächste Frage und Umfrage starten
        if (e.getSource() == bt_next)
        {
            if (aktuelleFragenummer != -1) 
            {   
                boolean next = false;
                Arbeitsprobe_fragen tempFrage;// = new Arbeitsprobe_fragen();
                String kw;
                if (pruefeAntwort())
                {       
                    do
                    {
                        if ((aktuelleFragenummer+1) == fragen.size()) 
                        {
                            ++aktuelleFragenummer;
                            break;
                        }
                        ++aktuelleFragenummer;
                        tempFrage = (Arbeitsprobe_fragen) fragen.get(aktuelleFragenummer);   
                        kw = tempFrage.getNeedKeyword();
                        if ("".equals(kw)) 
                        {
                            next = true;
                        }
                        else
                        {
                            if (keywordAktuell.isEmpty()) 
                                next = false;
                                for (int i = 0; i<keywordAktuell.size();i++)
                                {
                                    String temp = keywordAktuell.get(i).toString();
                                    if (temp.equals(kw))
                                    {
                                        next = true;
                                    }
                                }
                            }
                        }
                    while(!next);
                    jp_head.removeAll();
                    jp_head.repaint();
                    frageAnzeigen();                
                }                
            }
            else
            {
                ++aktuelleFragenummer;
                jp_head.removeAll();
                jp_head.repaint();
                frageAnzeigen();  
            }
        }
    }
}