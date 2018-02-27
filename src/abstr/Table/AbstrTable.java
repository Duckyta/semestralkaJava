package abstr.Table;

import abstr.AbstrDoubleList;
import abstr.IAbstrDoubleList;
import enums.EnumPozice;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author DuckMcQuack.
 * @param <K>
 * @param <V>
 */
public class AbstrTable<K extends Comparable<K>, V> implements IAbstrTable<K, V> {

    private class Zaznam<K extends Comparable<K>, V> implements Comparable<Zaznam> {

        private K key;
        private V value;

        public Zaznam(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public int compareTo(Zaznam o) {
            return o.getKey().compareTo(this.key);
        }

    }

    IAbstrDoubleList<Zaznam> hodnoty;
    private int pocetZaznamu;

    public AbstrTable() {
        hodnoty = new AbstrDoubleList<>();
        pocetZaznamu = 0;
    }

    public int getPocet() {
        Iterator it = hodnoty.iterator();
        int pocet = 0;
        while (it.hasNext()) {
            pocet++;
            it.next();
        }
        return pocet;
    }

    @Override
    public void zrus() {
        hodnoty = new AbstrDoubleList<>();
    }

    @Override
    public boolean jePrazdny() {
        return hodnoty.jePrazdny();
    }

    @Override
    public V najdi(K key) throws AbstrTableException {
        if (key != null) {
            if (hodnoty.jePrazdny()) {
                throw new NullPointerException("Seznam je prazdny!");
            }
            Iterator<Zaznam> it = hodnoty.iterator();
            Zaznam pom;
            while (it.hasNext()) {
                pom = it.next();
                if (pom.getKey().compareTo(key) == 0) {
                    return (V) pom.getValue();
                }
            }

        }
        throw new NullPointerException("Klic je null!");
    }

    @Override
    public void vloz(Comparable key, Object value) throws AbstrTableException {
        boolean kontrola = false;

        Iterator<Zaznam> it = hodnoty.iterator();

        while (it.hasNext()) {
            Zaznam z = it.next();
            if (key.compareTo(z.key) == 0) {
                kontrola = true;
                break;
            }
        }

        if (kontrola) {
            throw new AbstrTableException("Existuje prvek se stejnym klicem");
        } else {
            Zaznam zaznam = new Zaznam(key, value);
            hodnoty.vloz(zaznam, EnumPozice.POSLEDNI);
            pocetZaznamu++;
        }
    }

    @Override
    public V odeber(K key) throws AbstrTableException {
        if (key != null) {
            Iterator<Zaznam> it = hodnoty.iterator();
            Zaznam z;
            while (it.hasNext()) {
                z = it.next();
                if (z.getKey().compareTo(key) == 0) {
                    pocetZaznamu--;
                    return (V) hodnoty.odeberAktualni().getValue();
                }
            }
        }
        return null;
    }

    @Override
    public int getPocetZaznamu() {
        return pocetZaznamu;
    }

    @Override
    public Iterator iterator() {
        return new AbstrTableIterator();
    }

    private class AbstrTableIterator implements Iterator<V> {

        Zaznam pom = hodnoty.zpristupniPrvni();

        @Override
        public boolean hasNext() {
            return pom != null;
        }

        @Override
        public V next() {
            V temp = null;
            if (hasNext()) {
                temp = (V) pom.getValue();
                pom = hodnoty.zpristupniNaslednika();
            } else {
                throw new NoSuchElementException();
            }
            return temp;
        }

    }

}
