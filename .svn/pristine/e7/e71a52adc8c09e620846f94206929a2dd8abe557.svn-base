package abstr;

import enums.EnumPozice;
import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author DuckMcQuack
 * @param <T>
 */
public class AbstrDoubleList<T> implements IAbstrDoubleList<T>, Iterable<T>, Serializable {

    private class Prvek implements Serializable {

        private T hodnota;
        private Prvek predchudce;
        private Prvek naslednik;

        public Prvek(T data) {
            this.hodnota = data;
        }
    }

    private Prvek aktualni = null;
    private Prvek prvni = null;
    private Prvek posledni = null;

    @Override
    public void zrus() {
        this.prvni = null;
        this.aktualni = null;
        this.posledni = null;
    }

    @Override
    public boolean jePrazdny() {
        return (this.prvni == null);
    }

    @Override
    public void vlozPrvni(T data) {
        Prvek prvek = new Prvek(data);
        aktualni = prvek;
        if (jePrazdny()) {
            prvni = prvek;
            posledni = prvek;
        } else {
            prvek.naslednik = prvni;
            prvni.predchudce = prvek;
            prvni = prvek;
        }
    }

    @Override
    public void vlozPosledni(T data) {
        Prvek prvek = new Prvek(data);
        aktualni = prvek;
        if (jePrazdny()) {
            prvni = prvek;
            posledni = prvek;
        } else {
            prvek.predchudce = posledni;
            posledni.naslednik = prvek;
            posledni = prvek;
        }
    }

    @Override
    public void vlozNaslednika(T data) {
        if (aktualni != null) {
            if (aktualni.naslednik != null) {
                Prvek prvek = new Prvek(data);
                prvek.predchudce = aktualni;
                prvek.naslednik = aktualni.naslednik;
                aktualni.naslednik.predchudce = prvek;
                aktualni.naslednik = prvek;
                aktualni = prvek;
            } else {
                vlozPosledni(data);
            }
        } else {
            vlozPrvni(data);
        }
    }

    @Override
    public void vlozPredchudce(T data) {
        if (aktualni != null) {
            if (aktualni.predchudce != null) {
                Prvek prvek = new Prvek(data);
                prvek.predchudce = aktualni.predchudce;
                prvek.naslednik = aktualni;
                aktualni.predchudce.naslednik = prvek;
                aktualni.predchudce = prvek;
                aktualni = prvek;
            } else {
                vlozPrvni(data);
            }
        } else {
            vlozPrvni(data);
        }
    }

    @Override
    public void vloz(T data, EnumPozice pozice) {
        switch (pozice) {
            case PRVNI:
                vlozPrvni(data);
                break;
            case POSLEDNI:
                vlozPosledni(data);
                break;            
            case NASLEDNIK:
                vlozNaslednika(data);
                break;
            case PREDCHUDCE:
                vlozPredchudce(data);
                break;
                
        }
    }

    @Override
    public T zpristupniAktualni() {
        T vysledek = null;
        if (aktualni != null) {
            vysledek = aktualni.hodnota;
        }
        return vysledek;
    }

    @Override
    public T zpristupniPrvni() {
        T vysledek = null;
        if (prvni != null) {
            vysledek = prvni.hodnota;
            aktualni = prvni;
        }
        return vysledek;
    }

    @Override
    public T zpristupniPosledni() {
        T vysledek = null;
        if (posledni != null) {
            vysledek = posledni.hodnota;
            aktualni = posledni;
        }
        return vysledek;
    }

    @Override
    public T zpristupniNaslednika() {
        T vysledek = null;
        if (aktualni.naslednik != null) {
            vysledek = aktualni.naslednik.hodnota;
            aktualni = aktualni.naslednik;
        }
        return vysledek;
    }

    @Override
    public T zpristupniPredchudce() {
        T vysledek = null;
        if (aktualni.predchudce != null) {
            vysledek = aktualni.predchudce.hodnota;
            aktualni = aktualni.naslednik;
        }
        return vysledek;
    }

    @Override
    public T odeberAktualni() {
        T vysledek = null;
        if (aktualni != null) {
            vysledek = aktualni.hodnota;
            if (aktualni.predchudce != null && aktualni.naslednik != null) {
                aktualni.predchudce.naslednik = aktualni.naslednik;
                aktualni.naslednik.predchudce = aktualni.predchudce;
                aktualni = aktualni.naslednik;
            } else if (aktualni.predchudce == null && aktualni.naslednik == null) {
                aktualni = null;
                prvni = null;
                posledni = null;

            } else if (aktualni.predchudce == null && aktualni.naslednik != null) {
                aktualni.naslednik.predchudce = null;
                aktualni = aktualni.naslednik;
                prvni = aktualni;
            } else if (aktualni.predchudce != null & aktualni.naslednik == null) {
                aktualni.predchudce.naslednik = null;
                aktualni = aktualni.predchudce;
                posledni = aktualni;
            }
        }
        return vysledek;
    }

    @Override
    public T odeberPrvni() {
        T vysledek = null;
        if (!jePrazdny()) {
            vysledek = prvni.hodnota;
            if (prvni.predchudce == null && prvni.naslednik == null) {
                aktualni = null;
                prvni = null;
                posledni = null;
            } else if (prvni.predchudce == null && prvni.naslednik != null) {
                prvni.naslednik.predchudce = null;
                prvni = prvni.naslednik;
                aktualni = prvni;
            }
        }
        return vysledek;
    }

    @Override
    public T odeberPosledni() {
        T vysledek = null;
        if (posledni != null) {
            vysledek = posledni.hodnota;
            if (posledni.predchudce == null && posledni.naslednik == null) {
                aktualni = null;
                prvni = null;
                posledni = null;
            } else if (posledni.predchudce != null && posledni.naslednik == null) {
                posledni.predchudce.naslednik = null;
                posledni = posledni.predchudce;
                aktualni = posledni;
            }
        }
        return vysledek;
    }

    @Override
    public T odeberNaslednika() {
        T vysledek = null;
        if (zpristupniNaslednika() != null) {
            vysledek = odeberAktualni();
        }
        return vysledek;
    }

    @Override
    public T odeberPredchudce() {
        T vysledek = null;
        if (zpristupniPredchudce() != null) {
            vysledek = odeberAktualni();
        }
        return vysledek;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator() {
            private Prvek akt = prvni;

            @Override
            public boolean hasNext() {
                return (akt != null);
            }

            @Override
            public T next() {
                T hod = akt.hodnota;
                akt = akt.naslednik;
                return hod;
            }
        };
    }
}
