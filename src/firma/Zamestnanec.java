package firma;

import enums.Pozice;
import java.io.Serializable;

/**
 *
 * @author DuckMcQuack
 */
public class Zamestnanec implements IZamestnanec, Serializable {

    private String jmeno;
    private String prijmeni;
    private int id;
    private int cisloKancelare;
    private Pozice pracovniPozice;
    private IZamestnanec vedouci;

    public Zamestnanec(String jmeno, String prijmeni, int id, int cisloKancelare,
            Pozice pracovniPozice) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.id = id;
        this.cisloKancelare = cisloKancelare;
        this.pracovniPozice = pracovniPozice;
        this.vedouci = vedouci;
    }    
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getJmeno() {
        return jmeno;
    }

    @Override
    public int getKancelar() {
        return cisloKancelare;
    }

    @Override
    public Pozice getPozice() {
        return pracovniPozice;
    }

    @Override
    public IZamestnanec getVedouci() {
        return vedouci;
    }

    @Override
    public String getPrijmeni() {
        return prijmeni;
    }

    @Override
    public String toString() {
        return jmeno + " " + prijmeni + ", id=" + id + ", cisloKancelare=" + cisloKancelare + ", pracovniPozice=" + pracovniPozice;
    }   
}
