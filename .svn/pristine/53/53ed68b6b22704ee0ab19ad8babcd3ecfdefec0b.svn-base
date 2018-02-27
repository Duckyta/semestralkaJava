package firma;

import abstr.Table.AbstrTable;
import pobocka.IPobocka;
import abstr.Table.AbstrTableException;
import abstr.Table.IAbstrTable;
import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DuckMcQuack
 */
public class Firma implements IFirma, Serializable {

    private IAbstrTable<String, IPobocka> pobocky;
    private String nazevFirmy;

    public Firma(String nazevFirmy) {
        this.nazevFirmy = nazevFirmy;
        pobocky = new AbstrTable<>();
    }

    @Override
    public String getNazevFirmy() {
        return nazevFirmy;
    }   

    @Override
    public IPobocka najdi(String nazev) {
        IPobocka pob = null;
        try {
            pob = pobocky.najdi(nazev);
        } catch (AbstrTableException ex) {
            Logger.getLogger(Firma.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pob;
    }

    @Override
    public IPobocka odeber(String nazev) {
        try {
            return pobocky.odeber(nazev);
        } catch (AbstrTableException ex) {
            Logger.getLogger(Firma.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void vloz(String nazev, IPobocka pobocka) {
        try {
            pobocky.vloz(pobocka.getNazev(), pobocka);
        } catch (AbstrTableException ex) {
            Logger.getLogger(Firma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void zrus()
    {
        pobocky.zrus();
    }

    @Override
    public Iterator<IPobocka> iterator() {
        return pobocky.iterator();
    }
    
    
    @Override
    public int getPocet()
    {
        return pobocky.getPocetZaznamu();
    }
    

}
