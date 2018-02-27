package abstr.Tree;

import abstr.AbstrDoubleList;
import abstr.Fronta;
import static enums.EnumPozice.POSLEDNI;
import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author DuckMcQuack.
 * @param <E>
 */
public class AbstrTree<E> implements IAbstrTree<E>, Iterable<E>, Serializable {

    private Uzel koren;
    private Uzel aktualni;
    int mohutnost;

    private class Uzel implements Serializable {

        private final E data;
        private final AbstrDoubleList<Uzel> synove;
        private Uzel otec;
        private int pocetSynu;

        public Uzel(E data) {
            this.data = data;
            synove = new AbstrDoubleList<>();
            pocetSynu = 0;
        }

        public void vlozSyna(E dataSyn) {
            synove.vloz(new Uzel(dataSyn), POSLEDNI);
            pocetSynu++;
        }

        public void vlozSyna(Uzel uzel) {
            synove.vloz(uzel, POSLEDNI);
            pocetSynu++;
        }

        public E odeberSyna(int pozice) {
            if (this.pocetSynu >= pozice) {
                int poz = 0;
                Uzel uzel = this.synove.zpristupniPrvni();
                while (poz != pozice) {
                    uzel = this.synove.zpristupniNaslednika();
                }
                pocetSynu--;
                return synove.odeberAktualni().data;
            }
            return null;
        }

        public Uzel zpristupniSyna(int pozice) {
            if (this.pocetSynu >= pozice) {
                int poz = 0;
                Uzel uzel = this.synove.zpristupniPrvni();
                while (poz != pozice) {
                    uzel = this.synove.zpristupniNaslednika();
                    poz++;
                }
                return synove.zpristupniAktualni();
            }
            return null;
        }

        public int getPocetSynu() {
            return pocetSynu;
        }

        @Override
        public String toString() {
            return "Prvek{"
                    + "data: " + data
                    + ", synove: " + synove
                    + ", otec: " + otec
                    + ", pocet synu: " + pocetSynu + "}";
        }
    }

    public AbstrTree() {
        this.mohutnost = 0;
    }

    @Override
    public void zrus() {
        koren = null;
        aktualni = null;
        mohutnost = 0;
    }

    @Override
    public boolean jePrazdny() {
        return (mohutnost == 0);
    }

    @Override
    public int mohutnost() {
        return mohutnost;
    }

    @Override
    public void vlozKoren(E element) throws NullPointerException, AbstrTreeException {
        if (mohutnost == 0) {
            koren = new Uzel(element);
            mohutnost++;
        }
    }

    @Override
    public void vlozList(E element) throws NullPointerException, AbstrTreeException {
        if (aktualni != null) {
            aktualni.vlozSyna(element);
            mohutnost++;
        }
    }

    @Override
    public E odeberKoren() throws AbstrTreeException {
        if (mohutnost == 1) {
            E dataVraceni = koren.data;
            mohutnost--;
            koren = null;
            return dataVraceni;
        } else {
            return null;
        }
    }

    @Override
    public E odeberList(int pozice) throws AbstrTreeException {
        if (aktualni != null) {
            Uzel uzel = aktualni.zpristupniSyna(pozice);
            if (uzel.pocetSynu == 0) {
                mohutnost--;
                return aktualni.odeberSyna(pozice);
            }
        }
        return null;
    }

    @Override
    public E zpristupniKoren() throws AbstrTreeException {
        if (koren != null) {
            aktualni = koren;
            return koren.data;
        }
        return null;
    }

    @Override
    public E zpristupniSyna(int pozice) throws AbstrTreeException {
        if (aktualni != null) {
            if (aktualni.zpristupniSyna(pozice) != null) {
                aktualni = aktualni.zpristupniSyna(pozice);
                return aktualni.data;
            }
        }
        return null;
    }

    public E zpristupniOtce() throws AbstrTreeException {
        if (aktualni != null && aktualni != koren) {
            aktualni = aktualni.otec;
            return aktualni.data;
        }
        return null;
    }

    @Override
    public E dejSyna(int pozice) throws AbstrTreeException {
        if (aktualni != null) {
            if (aktualni.zpristupniSyna(pozice) != null) {
                return aktualni.data;
            }
        }
        return null;
    }

    @Override
    public int dejPocetSynu() throws AbstrTreeException {
        if (aktualni != null) {
            return aktualni.pocetSynu;
        } else {
            throw new AbstrTreeException("nelze ziskat pocet synu");
        }
    }

    @Override
    public boolean jeSynList(int position) throws AbstrTreeException {
        if (aktualni != null) {
            Uzel syn = aktualni.zpristupniSyna(position);
            if (syn == null) {
                throw new AbstrTreeException("neni zadan syn");
            }
            return syn.pocetSynu == 0;
        } else {
            throw new AbstrTreeException("aktualni zkoumany prvek je prazdny");
        }
    }

    @Override
    public boolean jeList() throws AbstrTreeException {
        if (aktualni != null) {
            return aktualni.pocetSynu == 0;
        } else {
            throw new AbstrTreeException("aktualni zkoumany prvek je prazdny");
        }
    }

    @Override
    public Iterator<E> iterator() {
        Fronta<Uzel> fronta = new Fronta<>();
        if (koren != null) {
            fronta.pridej(koren);
        }
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return !fronta.jePrazdny();
            }

            @Override
            public E next() {
                Uzel pom = fronta.odeber();
                for (Uzel u : pom.synove) {
                    fronta.pridej(u);
                }
                return pom.data;
            }
        };
    }

}
