package pobocka;

import abstr.Tree.AbstrTree;
import abstr.Tree.AbstrTreeException;
import firma.IZamestnanec;
import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DuckMcQuack
 */
public class Pobocka implements IPobocka, Serializable, Comparable {

    private AbstrTree<IZamestnanec> strom;
    private Adresa adresa;
    private String nazev;
    private int pocetZamestnancu;

    public Pobocka(Adresa adresa, String nazev, int pocetZamestnancu) {
        this.adresa = adresa;
        this.nazev = nazev;
        this.pocetZamestnancu = pocetZamestnancu;
        strom = new AbstrTree<>();
    }

    @Override
    public int getPocetZamestnancu() {
        return pocetZamestnancu;
    }

    @Override
    public Adresa getAdresa() {
        return adresa;
    }

    @Override
    public String getNazev() {
        return nazev;
    }

    @Override
    public void vlozVedoucihoPobocky(IZamestnanec zamestnanec) {
        try {
            if (strom.mohutnost() < 1) {
                strom.vlozKoren(zamestnanec);
                pocetZamestnancu++;
            }
        } catch (AbstrTreeException ex) {
            Logger.getLogger(Pobocka.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void vlozVedoucihoOddeleni(IZamestnanec zamestnanec) {
        try {
            strom.zpristupniKoren();
            strom.vlozList(zamestnanec);
        } catch (AbstrTreeException ex) {
            Logger.getLogger(Pobocka.class.getName()).log(Level.SEVERE, null, ex);
        }

        pocetZamestnancu++;
    }

    @Override
    public void vloz(IZamestnanec vedouciOddeleni, IZamestnanec zamestnanec) {

        try {
            strom.zpristupniKoren();
            int pocet = strom.dejPocetSynu();
            int index = 0;
            for (int i = 0; i < pocet; i++) {
                if (vedouciOddeleni.equals(strom.zpristupniSyna(i))) {
                    strom.vlozList(zamestnanec);
                    pocetZamestnancu++;
                    break;
                }
                index++;
            }
        } catch (AbstrTreeException ex) {
            Logger.getLogger(Pobocka.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public IZamestnanec odeber(IZamestnanec zamestnanec) {
        try {
            IZamestnanec temp;
            if (strom.mohutnost() == 1) {
                temp = strom.odeberKoren();
            } else {
                temp = strom.odeberList(0);
            }
            if (temp != null) {
                pocetZamestnancu--;
                return temp;
            }

        } catch (AbstrTreeException ex) {
            Logger.getLogger(Pobocka.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IZamestnanec zpristupni(int pozice) {
        try {
            if (pozice >= 0) {
                return strom.zpristupniSyna(pozice);
            }
        } catch (AbstrTreeException ex) {
            Logger.getLogger(Pobocka.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IZamestnanec zpristupniVedoucihoOddeleni(int oddeleni) {
        try {
            if (oddeleni >= 0) {
                strom.zpristupniKoren();
                return strom.zpristupniSyna(oddeleni);
            }

        } catch (AbstrTreeException ex) {
            Logger.getLogger(Pobocka.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IZamestnanec zpristupniVedoucihoPobocky() {
        try {
            return strom.zpristupniKoren();
        } catch (AbstrTreeException ex) {
            Logger.getLogger(Pobocka.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IZamestnanec[] zpristupniSeznamVedoucichOddeleni() {
        try {
            if (strom.zpristupniKoren() != null) {
                IZamestnanec zam = strom.zpristupniKoren();
                int pocetSynu = strom.dejPocetSynu();
                IZamestnanec[] pole = new IZamestnanec[pocetSynu];
                for (int i = 0; i < pocetSynu; i++) {
                    pole[i] = strom.dejSyna(i);
                }
                return pole;
            }

        } catch (AbstrTreeException ex) {
            Logger.getLogger(Pobocka.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IZamestnanec[] zpristupniSeznamZamestnancuOddeleni(IZamestnanec vedouciOddeleni) {
        try {
            strom.zpristupniKoren();
            int pocet = strom.dejPocetSynu();
            int index = 0;
            for (int i = 0; i < pocet; i++) {
                if (vedouciOddeleni.equals(strom.zpristupniSyna(i))) {
                    break;
                }
                index++;
            }

            strom.zpristupniKoren();
            IZamestnanec zam = strom.zpristupniSyna(index);
            int pocetSynu = strom.dejPocetSynu();
            IZamestnanec[] zamestnanci = new IZamestnanec[pocetSynu];
            for (int i = 0; i < pocetSynu; i++) {
                zamestnanci[i] = strom.dejSyna(i);
            }
            return zamestnanci;

        } catch (AbstrTreeException ex) {
            Logger.getLogger(Pobocka.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Iterator<IZamestnanec> iterator() {
        return strom.iterator();
    }

    @Override
    public String toString() {
        return nazev + "{" + "adresa=" + adresa + ", pocetZamestnancu=" + pocetZamestnancu + '}';
    }

    @Override
    public int compareTo(Object t) {
        return this.compareTo(t);
    }

}
