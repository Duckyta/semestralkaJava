package abstr.Heap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 *
 * @author DuckMcQuack
 * @param <E>
 */
public interface AbstrHeap<E extends Comparable<E>> {

    /**
     * Metoda zjistuje stav haldy.
     * @return
     */
    public boolean jePrazdny();

    /**
     * Metoda odebira z haldy datovy element podle nejvyssi priority a pote
       preskupi haldu podle priority zbylych elementu haldy.
     * @return 
     */
    public E odeberMax() throws IllegalStateException;

    /**
     * Metoda nastav do instance novy komparator a provede
       preskupen haldy tak, aby nova binarn struktura levostrann haldy byla organizovana
       tak, ze kazdy rodic bude mt vyss prioritu nez prpadne jeho dva potomci.
     * @param pole
     * @param c
     */
    public void reorganizuj(E[]pole,Comparator c) throws NullPointerException;

    /**
     * Vrac sekvenci v Stream s datovymi elementy haldy serazene podle orga-
       nizace levostrannem haldy na poli.
     * @return
     */
    public Stream stream();

    /**
     * Metoda vloz datovy element do haldy a zarad ho podle pravidel binarnho
       stromu na msto v poli tak, ze jeho rodic bude mt vyss prioritu a prpadne jeho
       potomci budou mt prioritu nizs.
     * @param element
     */
    public void vloz(E element) throws NullPointerException;

    /**
     * Metoda zprstupn datovy element s nejvyss prioritou, pricemz
       neprovad zadne preskupen organizace haldy.
     * @return 
     */
    public E zpristupniMax() throws IllegalStateException;

    /**
     * Metoda zrus obsah haldy bez nahrady.
     */
    public void zrus();
    
    /**
     * Vybuduje požadovanou prioritní frontu, vstupní parametr pole poboček
     * @param c
     * @param pole
     * @return
     */
    Object[] Vybuduj(Comparator c, E[] pole) throws NullPointerException;
    
    int getVelikost();
    
    Iterator<E> iterator();
}
