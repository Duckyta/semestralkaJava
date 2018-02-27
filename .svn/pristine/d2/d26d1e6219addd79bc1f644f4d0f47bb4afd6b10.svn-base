package abstr.Heap;

import abstr.zasobnik.IZasobnik;
import abstr.zasobnik.Zasobnik;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Stream;
import komparatory.KomparatorCislo;

/**
 *
 * @author DuckMcQuack
 * @param <E>
 */
public class AbstrHeapImpl<E extends Comparable<E>> implements AbstrHeap<E> {

    private E[] prioritniFronta;
    private int velikost;
    private Comparator c;

    public AbstrHeapImpl() {
        prioritniFronta = null;
        this.velikost = 0;
        this.c = new KomparatorCislo();
    }

    @Override
    public boolean jePrazdny() {
        return (velikost == 0);
    }

    @Override
    public E odeberMax() throws IllegalStateException {
        if (prioritniFronta != null) {
            E temp = prioritniFronta[0];
            if (velikost <= 1) {
                zrus();
            } else {
                prioritniFronta = Arrays.copyOfRange(prioritniFronta, 1, prioritniFronta.length);
                velikost--;
            }
            return temp;
        } else {
            throw new IllegalStateException("halda je prazdna");
        }
    }

    @Override
    public void reorganizuj(E[] pole, Comparator c) throws NullPointerException {
        this.c = c;
        if (c != null) {
            if (prioritniFronta == null) {
                velikost = pole.length;
                prioritniFronta = pole;
            }
            buildheap();
        } else {
            throw new NullPointerException("komparator prazdny");
        }
    }

    private void prohozeni(int prvni, int druhy) {
        E pom = prioritniFronta[druhy];
        prioritniFronta[druhy] = prioritniFronta[prvni];
        prioritniFronta[prvni] = pom;
    }

    @Override
    public Stream stream() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void vloz(E element) throws NullPointerException {
        if (element != null) {
            E[] pole = (E[]) Array.newInstance(element.getClass(), velikost + 1);
            if (prioritniFronta == null) {
                prioritniFronta = pole;
            }
            System.arraycopy(prioritniFronta, 0, pole, 0, prioritniFronta.length + 1);
            pole[prioritniFronta.length - 1] = element;
            prioritniFronta = pole;
            velikost++;
        } else {
            throw new NullPointerException("parametr prazdny");
        }
    }

    @Override
    public E zpristupniMax() throws IllegalStateException {
        if (prioritniFronta != null) {
            return prioritniFronta[0];
        } else {
            throw new IllegalStateException("halda prazdna");
        }

    }

    @Override
    public void zrus() {
        if (prioritniFronta != null) {
            for (int i = 0; i < prioritniFronta.length; i++) {
                prioritniFronta[i] = null;
            }
        }
        prioritniFronta = null;
        velikost = 0;
    }

    @Override
    public Object[] Vybuduj(Comparator c, E[] pole) throws NullPointerException {
        this.c = c;
        if (c != null && pole != null) {
            velikost = pole.length;
            prioritniFronta = pole;
            buildheap();
            return prioritniFronta;
        } else {
            throw new NullPointerException("komparator nebo pole prazdne");
        }
    }

    public void buildheap() {
        int n = velikost - 1;
        for (int i = n / 2; i >= 0; i--) {
            maxheap(i);
        }
    }

    public void maxheap(int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest;
        if ((left <= velikost - 1) && c.compare(prioritniFronta[left], prioritniFronta[i]) > 0) {
            largest = left;
        } else {
            largest = i;
        }

        if ((right <= velikost - 1) && c.compare(prioritniFronta[left], prioritniFronta[i]) > 0) {
            largest = right;
        }
        if (largest != i) {
            prohozeni(i, largest);
            maxheap(largest);
        }
    }

//    private void bubbleUp(int i) {
//        while (i > 1 && c.compare(prioritniFronta[i / 2], prioritniFronta[i]) == 1) {
//            prohozeni(i, i / 2);
//            i /= 2;
//        }
//    }
//    
//    private void bubbleDown(int i) {
//        while (i <= (velikost / 2)) {
//            int min = 2 * i;
//            
//            if (2 * i + 1 <=velikost && c.compare(prioritniFronta[2*i], prioritniFronta[2*i +1]) == 1) {
//                min = 2*i +1;
//            }
//            
//            if (c.compare(prioritniFronta[min], prioritniFronta[i]) == -1 ) {
//                prohozeni(i, min);
//            }
//            
//           i = min;
//        }
//    }
    public Iterator<E> iteratorSirka() {
        return new Iterator() {
            int poz = 0;
            E element = prioritniFronta[1];

            @Override
            public boolean hasNext() {
                return element != null;
            }

            @Override
            public E next() {
                E pom = element;
                element = prioritniFronta[++poz];
                return pom;
            }
        };
    }

    public Iterator<E> iteratorHloubka() {
        return new Iterator() {
            int poz = 0;
            IZasobnik<Prvek> zasobnik = new Zasobnik();

            {
                {
                    Prvek<E> prvek = new Prvek<>();
                    prvek.index = 0;
                    prvek.prvekData = prioritniFronta[0];
                    zasobnik.vloz(prvek);
                }
            }

            @Override
            public boolean hasNext() {
                return !(zasobnik.jePrazdny());
            }

            @Override
            public Object next() {
                poz = 0;
                Prvek pom = zasobnik.odeber();
                while (true) {
                    if (poz > 2) {
                        break;
                    }
                    if (pom.index * 2 + 2 <= velikost) {
                        Prvek<E> prvek = new Prvek<>();
                        prvek.index = pom.index * 2 + 2;
                        prvek.prvekData = prioritniFronta[pom.index - 2 + 2];
                        zasobnik.vloz(prvek);
                    }
                    if (pom.index * 2 + 1 <= velikost) {
                        Prvek<E> prvek2 = new Prvek<>();
                        prvek2.index = pom.index * 2 + 1;
                        if (pom.index * 2 + 1 >= velikost) {
                            break;
                        } else {
                            prvek2.prvekData = prioritniFronta[pom.index * 2 + 1];
                        }
                        zasobnik.vloz(prvek2);
                        break;
                    }
                    poz++;
                }
                return pom.prvekData;
            }
        };
    }

    @Override
    public int getVelikost() {
        return velikost;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i != velikost;
            }

            @Override
            public E next() {
                return prioritniFronta[i++];
            }
        };
    }

    class Prvek<E> implements Serializable {

        int index;
        E prvekData;
    }

}
